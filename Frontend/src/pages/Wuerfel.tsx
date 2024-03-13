import {useRef} from "react";
import {post} from "../api/Api.ts";
import useIdStore from "../Store.ts";

function Wuerfel() {
    const idStore = useIdStore()
    const diceOneRef = useRef(null)
    const diceTwoRef = useRef(null)
    const diceThreeRef = useRef(null)
    const diceFourRef = useRef(null)
    const diceFiveRef = useRef(null)

    async function rollDice() {
        const result = await post<number, number[]>("http://localhost:8080/wuerfe", idStore.id)
        diceOneRef.current.className = "dice dice-one show-" + result[0]
        diceTwoRef.current.className = "dice dice-two show-" + result[1]
        diceThreeRef.current.className = "dice dice-three show-" + result[2]
        diceFourRef.current.className = "dice dice-four show-" + result[3]
        diceFiveRef.current.className = "dice dice-five show-" + result[4]
    }

    return (
        <>
            <div className="game">
                <div className="w-container">
                    <div ref={diceOneRef} className="dice dice-one">
                        <EinWuerfel/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceTwoRef} className="dice dice-two">
                        <EinWuerfel/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceThreeRef} className="dice dice-three">
                        <EinWuerfel/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceFourRef} className="dice dice-four">
                        <EinWuerfel/>
                    </div>
                </div>
                <div className="w-container">
                    <div ref={diceFiveRef} className="dice dice-five">
                        <EinWuerfel/>
                    </div>
                </div>
                <div id='roll' className='roll-button'>
                    <button onClick={rollDice}>Roll dice!</button>
                </div>
            </div>
        </>
    )
}

function EinWuerfel() {
    return (
        <>
            <div className='side one'>
                <div className="dot one-1"></div>
            </div>
            <div className='side two'>
                <div className="dot two-1"></div>
                <div className="dot two-2"></div>
            </div>
            <div className='side three'>
                <div className="dot three-1"></div>
                <div className="dot three-2"></div>
                <div className="dot three-3"></div>
            </div>
            <div className='side four'>
                <div className="dot four-1"></div>
                <div className="dot four-2"></div>
                <div className="dot four-3"></div>
                <div className="dot four-4"></div>
            </div>
            <div className='side five'>
                <div className="dot five-1"></div>
                <div className="dot five-2"></div>
                <div className="dot five-3"></div>
                <div className="dot five-4"></div>
                <div className="dot five-5"></div>
            </div>
            <div className='side six'>
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