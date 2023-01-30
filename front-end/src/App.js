// import logo from './logo.svg';
import './App.css';
// import Navbar from './components/navbar/Navbar.js';

import { Routes, Route } from 'react-router-dom';
import FormAnswer from './pages/formAnswer/FormAnswer';
import Login from './pages/login/Login';
import Home from './pages/home/Home';

function App() {
  return (
      <Routes>
        <Route path="/formulario" element={<FormAnswer />} exact/>
        <Route path="/login" element={<Login />} exact/>
        <Route path="/home" element={<Home />} exact/>
      </Routes>
  );
}

export default App;
