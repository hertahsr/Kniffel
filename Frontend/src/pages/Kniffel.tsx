function Kniffel(kniffel: any) {
    return (
        <>
            Block
            <table>
                <thead>
                <tr>
                    <th>Spieler 1</th>
                </tr>
                </thead>
                <tbody>
                    <Block spieler={kniffel.spieler} />
                </tbody>
            </table>
        </>
    )
}

function Block(spieler: any) {
    const items = [1, 2, 3, 4, 5, 6]
    const block = items.map(item =>
        <tr>
            <td key={item}>{item}</td><td>0</td>
        </tr>
    )
    return (
        <>
            {block}
        </>
    )
}

export default Kniffel