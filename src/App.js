import logo from './logo.svg';
import './App.css';
import './skin.css';

import Register from './page/register/Register';
import Login from './page/login/Login';
import Footer from "./components/footer/Footer"
// import Chat from './page/Chat/Chat'

import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";
import MainPage from './page/mainPage/MainPage';



function App() {
  return (
    <Router>
      <Routes>
        
        <Route path="/register" element = {<Register />}></Route>
        <Route path="/login" element = {<Login />}> </Route>
        <Route path="/" element = {<Login />}> </Route>
        <Route path="/MainPage" element = {<MainPage />}> </Route>
        
        {/* <Route path="/messenger">
          {!user ? <Redirect to="/" /> : <Messenger />}
        </Route>
        <Route path="/profile/:username">
          <Profile />
        </Route> */}

        
      </Routes>
      {/* <Footer /> */}
    </Router>
  );
}

export default App;
