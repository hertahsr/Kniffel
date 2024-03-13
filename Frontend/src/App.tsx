import {BrowserRouter, Routes, Route} from 'react-router-dom'
import './App.css'
import Home from "./pages/Home.tsx";
import Kniffel from "./pages/Kniffel.tsx";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route index element={<Home/>}/>
                <Route path="kniffel" element={<Kniffel/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default App
