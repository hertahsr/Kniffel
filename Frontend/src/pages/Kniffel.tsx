import Wuerfel from "./Wuerfel.tsx";
import {
    Button, ClickAwayListener,
    Container,
    Dialog, DialogActions,
    DialogContent, DialogContentText, Fab, Paper, styled, Table,
    TableBody,
    TableCell, TableContainer,
    TableHead,
    TableRow, TextField, Tooltip, tooltipClasses, TooltipProps
} from "@mui/material";
import Grid from '@mui/material/Unstable_Grid2';
import {useLocation, useNavigate} from "react-router-dom";
import {get, post, put} from "../api/Api.ts";
import useIdStore from "../Store.ts";
import React, {useState} from "react";
import Typography from "@mui/material/Typography";
import InfoIcon from "@mui/icons-material/Info";

function Kniffel() {
    const {state} = useLocation()
    const navigate = useNavigate();
    const [kniffel, setKniffel] = useState<Kniffel>(state.kniffel)

    function changeKniffel(changedKniffel: Kniffel) {
        setKniffel(changedKniffel)
    }

    return (
        <>
            <Container maxWidth="100%">
                <Grid container spacing={2}>
                    {kniffel.teilnehmer.map(spieler => (
                        <Grid xs={2}>
                            <Paper elevation={3} className={"block"}
                                   style={{
                                       outlineStyle: spieler.spielerId == kniffel.teilnehmer[kniffel.aktiverSpielerIndex].spielerId ? "solid" : "none",
                                       outlineColor: "black"
                                   }}>
                                <BlockComponent handleChange={changeKniffel} spieler={spieler} kniffel={kniffel}/>
                            </Paper>
                        </Grid>

                    ))}
                    <Grid xs>
                        <Wuerfel handleChange={changeKniffel} kniffel={kniffel}/>
                    </Grid>
                </Grid>
            </Container>
            <Dialog open={kniffel.gewinnerListe.length > 0}>
                <DialogContent>
                    <DialogContentText>
                        {"Gewinner: " + kniffel.gewinnerListe.map(spieler => spieler.name + " ")}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => navigate("/")}>Neues Spiel</Button>
                </DialogActions>
            </Dialog>
        </>
    )
}

function BlockComponent(props: { handleChange: (kniffel: Kniffel) => void, spieler: Spieler, kniffel: Kniffel }) {
    const idStore = useIdStore()
    const block = props.spieler.block
    const [openDialog, setOpenDialog] = useState(false)
    const [spielerName, setSpielerName] = useState(props.spieler.name)
    const [category, setCategory] = useState("")

    async function changeName(newName: string) {
        setSpielerName(newName)
        const kniffel = await put<string, Kniffel>("http://localhost:8080/kniffel/" + idStore.id + "spieler/" + props.spieler.spielerId + "/name", newName)
        props.handleChange(kniffel)
    }

    async function checkScore(category: string) {
        const score = await get<number>("http://localhost:8080/kniffel/" + idStore.id + "/scores/" + category.toUpperCase())
        if (score == 0) {
            setCategory(category)
            setOpenDialog(true)
        } else await enterScore(category)
    }

    async function enterScore(category: string) {
        try {
            const kniffel = await post<string, Kniffel>("http://localhost:8080/kniffel/" + idStore.id + "/scores", category.toUpperCase())
            props.handleChange(kniffel)
        } catch (error) {
            if (error instanceof Error) {
                console.log(error.message)
            }
        }
    }

    const [open, setOpen] = useState(false);

    const handleTooltipClose = () => {
        setOpen(false);
    };

    const handleTooltipOpen = () => {
        setOpen(true);
    };

    const HtmlTooltip = styled(({className, ...props}: TooltipProps) => (
        <Tooltip {...props} classes={{popper: className}}/>
    ))(({theme}) => ({
        [`& .${tooltipClasses.tooltip}`]: {
            backgroundColor: '#f5f5f9',
            color: 'rgba(0, 0, 0, 0.87)',
            maxWidth: 220,
            fontSize: theme.typography.pxToRem(12),
            border: '1px solid #dadde9',
        },
    }));

    const blockElement =
        <>
            <Table sx={{minWidth: 100}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell colSpan={2}><b>{isActivePlayer() ? "Aktiver Spieler " : ""}</b>
                            <TextField
                                label={"Spielername"} value={spielerName}
                                onChange={event => changeName(event.target.value)}
                                sx={{mt: 2}}/>
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {Object.entries(block).map((category) => (
                        <>
                            <TableRow
                                key={category[0]}
                                sx={{'&:last-child td, &:last-child th': {border: 0}}}
                            >
                                <TableCell component="th" scope="row" style={{padding: 0}}>
                                    <img src={'./src/assets/' + category[0] + '.png'} className={"icon"}
                                         style={{marginRight: 2}}/>
                                    {category[0].charAt(0).toUpperCase() + category[0].slice(1).split(/(?=[A-Z])/).join(" ")}
                                </TableCell>
                                <TableCell onClick={isActivePlayer() ? () => checkScore(category[0]) : undefined}
                                           align="right">{category[1]}</TableCell>
                            </TableRow>
                        </>
                    ))}
                </TableBody>
            </Table>
            <Dialog open={openDialog}>
                <DialogContent>
                    <DialogContentText>
                        {"Möchten Sie wirklich 0 eintragen?"}
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={async () => {
                        await enterScore(category)
                        setCategory("")
                        setOpenDialog(false)
                    }}>Ja</Button>
                    <Button onClick={() => setOpenDialog(false)}>Nein</Button>
                </DialogActions>
            </Dialog>
            <ClickAwayListener onClickAway={handleTooltipClose}>
                <div>
                    <HtmlTooltip
                        PopperProps={{
                            disablePortal: true,
                        }}
                        onClose={handleTooltipClose}
                        open={open}
                        disableFocusListener
                        disableHoverListener
                        disableTouchListener
                        title={
                            <React.Fragment>
                                <Typography color="inherit">Spiel spielen</Typography>
                                <br/>
                                {"Auf der linken Seite befinden sich die Spielerblöcke, die jedem Spieler persönlich zugeordnet sind. Der Block des Spielers, der an der Reihe ist, wird hervorgehoben. Jeder Spieler würfelt, indem er auf den Knopf \"Übrige Würfe: \" mit der Anzahl der übrigen Würfe klickt. Nach dem ersten Wurf in jeder Runde kann der Spieler beliebige Würfel fixieren bzw. freigeben, indem er sie anklickt. Fixierten Würfel werden gerahmt dargestellt und werden beim nächsten Wurf nicht mit gewürfelt. Spätestens nach dem dritten Wurf in der Runde muss der aktive Spieler sein Ergebnis in das entsprechende Feld seines Spielblocks eintragen, indem er auf die ausgewählte Kategorie klickt. Damit endet der Zug des Spielers. Wenn das Spiel zu Ende ist, wird der oder die Gewinner in einem Pop-up-Fenster bekannt gegeben."}
                                <br/>
                                <br/>
                                <a href={"https://www.schmidtspiele.de/files/Produkte/5/51203%20-%20Kniffel/51203_Kniffel_DE.pdf"}
                                   target={"_blank"}>Spielregeln</a>
                            </React.Fragment>}
                    >
                        <Fab aria-label="info" style={{
                            position: "absolute",
                            bottom: 20,
                            right: 20,
                        }}
                             onClick={handleTooltipOpen}>
                            <InfoIcon/>
                        </Fab>
                    </HtmlTooltip>
                </div>
            </ClickAwayListener>
        </>
    return (
        <TableContainer>
            {blockElement}
        </TableContainer>
    )

    function isActivePlayer() {
        return props.spieler.spielerId == props.kniffel.teilnehmer[props.kniffel.aktiverSpielerIndex].spielerId
    }
}

export default Kniffel