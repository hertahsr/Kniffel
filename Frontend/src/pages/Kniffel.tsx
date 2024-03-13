import Wuerfel from "./Wuerfel.tsx";
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";
import Grid from '@mui/material/Unstable_Grid2';
import {useLocation} from "react-router-dom";

function Kniffel() {
    const {state} = useLocation()
    const kniffel = state.kniffel
    return (
        <>
            <Grid container spacing={2}>
                <Grid xs={3}>
                    <BlockComponent block={kniffel.teilnehmer[0].block}/>
                </Grid>
                <Grid xs={3}>
                    <BlockComponent block={kniffel.teilnehmer[0].block}/>
                </Grid>
                <Grid xs={6}>
                    <Wuerfel/>
                </Grid>
            </Grid>
        </>
    )
}

function BlockComponent(props: { block: Block; }) {
    const block = props.block
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
                            <TableCell align="right">{category[1]}</TableCell>
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