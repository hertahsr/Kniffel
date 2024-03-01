function Kniffel(kniffel: any) {
    return (
        <>
            <div>
                <Block spieler={kniffel.spieler}/>
            </div>
        </>
    )
}

function Block(spieler: any) {
    const items = [1, 2, 3, 4, 5, 6]
    const block = items.map(item =>
        <tr>
            <td key={item}>{item}</td>
            <td>0</td>
        </tr>
    )
    return (
        <>
            <table>
                <thead>
                <tr>
                    <th colSpan={2}>Spieler 1</th>
                </tr>
                </thead>
                <tbody>
                {block}
                </tbody>
            </table>

        </>
    )
}

export default Kniffel