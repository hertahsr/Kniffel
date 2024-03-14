import Wuerfel from "./Wuerfel.tsx";
import {Container, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Grid from '@mui/material/Unstable_Grid2';
import {useLocation} from "react-router-dom";
import {post} from "../api/Api.ts";
import useIdStore from "../Store.ts";
import {useState} from "react";

function Kniffel() {
    const {state} = useLocation()
    const [kniffel, setKniffel] = useState(state.kniffel)

    function changeKniffel(changedKniffel: Kniffel) {
        setKniffel(changedKniffel)
    }

    return (
        <>
            <Container maxWidth="sm">
                <Grid container spacing={2}>
                    <Grid xs={5}>
                        <BlockComponent handleChange={changeKniffel} block={kniffel.teilnehmer[0].block}/>
                    </Grid>
                    <Grid xs={5}>
                        <BlockComponent handleChange={changeKniffel} block={kniffel.teilnehmer[0].block}/>
                    </Grid>
                    <Grid xs>
                        <Wuerfel/>
                    </Grid>
                </Grid>
            </Container>
        </>
    )
}

function BlockComponent(props: { handleChange: (kniffel: Kniffel) => void, block: Block; }) {
    const idStore = useIdStore()
    const block = props.block

    async function enterScore(category: string) {
        const kniffel = await post<string, Kniffel>("http://localhost:8080/kniffel/" + idStore.id, category.toUpperCase())
        props.handleChange(kniffel)
    }

    const blockElement =
        <>
            <Table sx={{minWidth: 150}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        <TableCell>{"Spieler " + 1}</TableCell>
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