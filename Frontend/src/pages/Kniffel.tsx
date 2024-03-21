import Wuerfel from "./Wuerfel.tsx";
import {
    Button,
    Container,
    Dialog, DialogActions,
    DialogContent, DialogContentText, Paper, Table,
    TableBody,
    TableCell, TableContainer,
    TableHead,
    TableRow, TextField
} from "@mui/material";
import Grid from '@mui/material/Unstable_Grid2';
import {useLocation, useNavigate} from "react-router-dom";
import {get, post, put} from "../api/Api.ts";
import useIdStore from "../Store.ts";
import {useState} from "react";

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
                                   style={{outlineStyle: spieler.spielerId == kniffel.teilnehmer[kniffel.aktiverSpielerIndex].spielerId ? "solid" : "none",}}>
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

    const blockElement =
        <>
            <Table sx={{minWidth: 100}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell colSpan={2}><b>{isActivePlayer() ? "Aktiver Spieler " : ""}</b><TextField
                            label={"Spielername"} value={spielerName}
                            onChange={event => changeName(event.target.value)}/></TableCell>
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