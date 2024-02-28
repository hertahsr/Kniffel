import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
        <MyButton />
    </>
  )
}

async function getNeuenSpieler() {
    const response = await fetch("http://localhost:8080/spieler", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify("Spieler 1")
    })
    return response.json()
}

async function getNeuesSpiel() {
    const spieler = []
    spieler.push(await getNeuenSpieler())
    const response = await fetch("http://localhost:8080/kniffel/neuesSpielStarten", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(spieler)
    })
    console.log(response.json())
}

function MyButton() {
    return (
        <button onClick={getNeuesSpiel}>Spiel starten</button>
    );
}

export default App
