interface Kniffel {
    id: number
    teilnehmer: Spieler[]
    aktiverSpielerIndex: number
    runde: number
    uebrigeWuerfe: number
    wuerfel: number[]
    freieWuerfel: number[]
}