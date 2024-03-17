import Wuerfel from "./Wuerfel.tsx";
import {Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Grid from '@mui/material/Unstable_Grid2';
import {useLocation} from "react-router-dom";
import {post} from "../api/Api.ts";
import useIdStore from "../Store.ts";
import {useState} from "react";

function Kniffel() {
    const {state} = useLocation()
    const [kniffel, setKniffel] = useState<Kniffel>(state.kniffel)

    function changeKniffel(changedKniffel: Kniffel) {
        setKniffel(changedKniffel)
    }

    return (
        <>
            <Container maxWidth="sm">
                <Grid container spacing={2}>
                    {kniffel.teilnehmer.map(spieler => (
                        <Grid xs={5}>
                            <BlockComponent handleChange={changeKniffel} spieler={spieler}/>
                        </Grid>
                    ))}
                    <Grid xs>
                        <Wuerfel/>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}

function BlockComponent(props: { handleChange: (kniffel: Kniffel) => void, spieler: Spieler; }) {
    const idStore = useIdStore()
    const block = props.spieler.block

    async function enterScore(category: string) {
        let kniffel;
        try {
            kniffel = await post<string, Kniffel>("http://localhost:8080/kniffel/" + idStore.id, category.toUpperCase())
            props.handleChange(kniffel)
        } catch (error) {
            if (error instanceof Error) {
                console.log(error.message)
            }
        }
    }

    const blockElement =
        <>
            <Table sx={{minWidth: 150}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>{props.spieler.name}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {Object.entries(block).map((category) => (
                        <TableRow
                            key={category[0]}
                            sx={{'&:last-child td, &:last-child th': {border: 0}}}
                        >
                            <TableCell component="th" scope="row">
                                {category[0]}
                            </TableCell>
                            <TableCell onClick={() => enterScore(category[0])} align="right">{category[1]}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </>
    return (
        <TableContainer>
            {blockElement}
        </TableContainer>
    )
}

export default Kniffel