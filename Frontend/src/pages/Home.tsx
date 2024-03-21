import Grid from "@mui/material/Unstable_Grid2";
import {
    Button,
    Chip, ClickAwayListener, colors,
    Container, Fab, styled,
    TextField, Tooltip, tooltipClasses, TooltipProps
} from "@mui/material";
import {useNavigate} from "react-router-dom";
import useIdStore from "../Store.ts";
import React, {useState} from "react";
import AddIcon from '@mui/icons-material/Add';
import PersonIcon from '@mui/icons-material/Person';
import InfoIcon from '@mui/icons-material/Info';
import Typography from '@mui/material/Typography';

function Home() {
    const navigate = useNavigate();
    const idStore = useIdStore()
    const [spieler, setSpieler] = useState<Spieler[]>([])
    const [newSpielerName, setNewSpielerName] = useState("")
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
    return (
        <>
            <Container maxWidth="sm">
                <Grid container spacing={2}>
                    <Grid xs={12}>
                        <h1 style={{color: "#213547"}}>Kniffel</h1>
                    </Grid>
                    <Grid xs={12}>
                        {spieler.map(spieler => (
                            <Chip icon={<PersonIcon fontSize={"large"}/>} label={"Spielername: " + spieler.name}
                                  variant="outlined" key={spieler.spielerId} sx={{m: 1}}/>
                        ))}
                    </Grid>
                    <Grid xs={12}>
                        <TextField id="outlined-basic" label="Spielername" variant="outlined" value={newSpielerName}
                                   onChange={event => setNewSpielerName(event.target.value)}/>
                        <Button variant="outlined" onClick={async () => {
                            spieler.push(await getNeuenSpieler(newSpielerName))
                            setNewSpielerName("")
                        }} style={{height: 56, marginLeft: 4}} disabled={!newSpielerName}><AddIcon/></Button>
                    </Grid>
                    <Grid xs={12}>
                        <Button variant="outlined" onClick={async () => {
                            const kniffel = await getNeuesSpiel(spieler)
                            idStore.id = kniffel.id
                            navigate("/Kniffel", {state: {kniffel: kniffel}})
                        }} disabled={spieler.length == 0}>Spiel starten</Button>
                    </Grid>
                </Grid>

            </Container>
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
                        size="small"
                        title={
                            <React.Fragment>
                                <Typography color="inherit">Spiel starten</Typography>
                                <br/>
                                {"Vor Spielbeginn tragen alle Spieler ihren Namen nacheinander in das Textfeld ein, um sich im Spiel zu registrieren. Mit dem +-Button neben dem Textfeld werden Spieler dem Spiel hinzugefügt. Ein Spieler kann allein spielen, jedoch darf das Namensfeld nicht leer bleiben. Sobald sich alle Spieler eingetragen haben, kann das Spiel durch Klicken auf den Knopf \"SPIEL STARTEN\" gestartet werden."}
                            </React.Fragment>}
                    >
                        <Fab aria-label="info" style={{
                            position: "absolute",
                            top: 410,
                            left: 860,
                        }}
                             onClick={handleTooltipOpen}>
                            <InfoIcon/>
                        </Fab>
                    </HtmlTooltip>
                </div>
            </ClickAwayListener>
        </>
    )
}

async function getNeuenSpieler(name: string) {
    const response = await fetch("http://localhost:8080/spieler", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: name
    })
    return response.json()
}

async function getNeuesSpiel(spieler: Spieler[]) {
    const response = await fetch("http://localhost:8080/kniffel/neuesSpielStarten", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(spieler)
    })
    return await response.json() as Promise<Kniffel>
}

export default Home
