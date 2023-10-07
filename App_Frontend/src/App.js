import { BrowserRouter, Routes, Route } from "react-router-dom"
import Home from "./Home"
import LoginPage from "./LoginPage"
function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/LoginPage/:username" element={<LoginPage />} />
      </Routes>
    </BrowserRouter>
  )
}
export default App 