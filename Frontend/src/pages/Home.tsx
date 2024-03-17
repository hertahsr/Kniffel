import Grid from "@mui/material/Unstable_Grid2";
import {Button, Card, CardContent, CardHeader, Container, List, ListItem, ListItemText, TextField} from "@mui/material";
import {useNavigate} from "react-router-dom";
import useIdStore from "../Store.ts";
import {useState} from "react";

function Home() {
    const navigate = useNavigate();
    const idStore = useIdStore()
    const [spieler, setSpieler] = useState<Spieler[]>([])
    const [newSpielerName, setNewSpielerName] = useState("")
    return (
        <Container maxWidth="sm">
            <Grid container spacing={2}>
                <Grid xs={12}>
                    <h1>Kniffel</h1>
                </Grid>
                <Grid xs={12}>
                    {spieler.map(spieler => (
                        <Card sx={{maxWidth: 345, m: 2}}
                              key={spieler.spielerId}
                        >
                            <CardContent>
                                {"Spielername: " + spieler.name}
                            </CardContent>
                        </Card>
                    ))}
                </Grid>
                <Grid xs={12}>
                    <TextField id="outlined-basic" label="Spielername" variant="outlined" value={newSpielerName}
                               onChange={event => setNewSpielerName(event.target.value)}/>
                    <Button variant="outlined" onClick={async () => {
                        spieler.push(await getNeuenSpieler(newSpielerName))
                        setNewSpielerName("")
                    }}>+</Button>
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
