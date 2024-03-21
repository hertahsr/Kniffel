import {useRef} from "react";
import {post, put} from "../api/Api.ts";
import useIdStore from "../Store.ts";
import Kniffel from "./Kniffel.tsx";

function Wuerfel(props: { handleChange: (kniffel: Kniffel) => void, kniffel: Kniffel }) {
    const idStore = useIdStore()
    const diceOneRef = useRef(null)
    const diceTwoRef = useRef(null)
    const diceThreeRef = useRef(null)
    const diceFourRef = useRef(null)
    const diceFiveRef = useRef(null)

    async function rollDice() {
        const kniffel: Kniffel = await post<number, Kniffel>("http://localhost:8080/wuerfe", idStore.id)
        const result = kniffel.wuerfel
        diceOneRef.current.className = "dice dice-one show-" + result[0]
        diceTwoRef.current.className = "dice dice-two show-" + result[1]
        diceThreeRef.current.className = "dice dice-three show-" + result[2]
        diceFourRef.current.className = "dice dice-four show-" + result[3]
        diceFiveRef.current.className = "dice dice-five show-" + result[4]
        props.handleChange(kniffel)
    }

    async function fix(wuerfelIndex: number) {
        const kniffel: Kniffel = await put<number, Kniffel>("http://localhost:8080/kniffel/" + idStore.id + "/wuerfel", wuerfelIndex)
        props.handleChange(kniffel)
    }


    return (
        <>
            <div className="game">
                <div className="w-container">
                    <div ref={diceOneRef} className="dice dice-one" onClick={async () => await fix(1)}>
                        <EinWuerfel
                            outlineStyle={props.kniffel.freieWuerfel.find(wuerfel => wuerfel == 1) ? "none" : "solid"}/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceTwoRef} className="dice dice-two" onClick={async () => await fix(2)}>
                        <EinWuerfel
                            outlineStyle={props.kniffel.freieWuerfel.find(wuerfel => wuerfel == 2) ? "none" : "solid"}/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceThreeRef} className="dice dice-three" onClick={async () => await fix(3)}>
                        <EinWuerfel
                            outlineStyle={props.kniffel.freieWuerfel.find(wuerfel => wuerfel == 3) ? "none" : "solid"}/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceFourRef} className="dice dice-four" onClick={async () => await fix(4)}>
                        <EinWuerfel
                            outlineStyle={props.kniffel.freieWuerfel.find(wuerfel => wuerfel == 4) ? "none" : "solid"}/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceFiveRef} className="dice dice-five" onClick={async () => await fix(5)}>
                        <EinWuerfel
                            outlineStyle={props.kniffel.freieWuerfel.find(wuerfel => wuerfel == 5) ? "none" : "solid"}/>
                    </div>
                </div>
                <div id='roll' className='roll-button'>
                    <button onClick={rollDice}>{"Übrige Würfe: " + props.kniffel.uebrigeWuerfe}</button>
                </div>
            </div>
        </>
    )
}

function EinWuerfel(props: { outlineStyle: string }) {
    return (
        <>
            <div className='side one' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot one-1"></div>
            </div>
            <div className='side two' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot two-1"></div>
                <div className="dot two-2"></div>
            </div>
            <div className='side three' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot three-1"></div>
                <div className="dot three-2"></div>
                <div className="dot three-3"></div>
            </div>
            <div className='side four' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot four-1"></div>
                <div className="dot four-2"></div>
                <div className="dot four-3"></div>
                <div className="dot four-4"></div>
            </div>
            <div className='side five' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot five-1"></div>
                <div className="dot five-2"></div>
                <div className="dot five-3"></div>
                <div className="dot five-4"></div>
                <div className="dot five-5"></div>
            </div>
            <div className='side six' style={{outlineStyle: props.outlineStyle}}>
                <div className="dot six-1"></div>
                <div className="dot six-2"></div>
                <div className="dot six-3"></div>
                <div className="dot six-4"></div>
                <div className="dot six-5"></div>
                <div className="dot six-6"></div>
            </div>
        </>
    )
}

export default Wuerfel