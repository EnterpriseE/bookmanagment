import React, { useState, useEffect } from "react";
import axios from "axios";
import logo from '../../logo.svg';

import "./login.css";
// import { loginCall } from "../../apiCalls";
// import { AuthContext } from "../../context/AuthContext";
// import { CircularProgress } from "@mui/material";
// import { Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import { loginRoute } from "../../apiRoutes";
import { useNavigate,Link } from "react-router-dom";




//登陆页面，点击提价表单，跑handleClick方法
export default function Login() {
  // const email = useRef();
  // const password = useRef();
  // const { isFetching, dispatch } = useContext(AuthContext);
  const navigate = useNavigate();
  const toastOptions = {
    position: "bottom-right",
    autoClose: 8000,
    pauseOnHover: true,
    draggable: true,
    theme: "dark",
  };

  const [values, setValues] = useState({
    username: "",
  
    // password: "",
  });

  const validateForm = ()=>{


    const { username } = values;
    if (username === "") {
      toast.error("Email and Password is required.", toastOptions);
      return false;
    } 
    return true;


  }
  const handleChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (validateForm()){
      const { username, password } = values;
      const response = await axios.post(loginRoute, {
        username:username,
        // password:password,
      });

      // var u = {
      //   username:response.data.user.username,
      //   brief:response.data.user.brief,
      //   uuid:response.data.user.uuid,
      //   email:response.data.user.email,
      //   image_url:response.data.user.image_url,

      // }
      

      if (response.data.user) {
        localStorage.setItem(
          "user",
          JSON.stringify(response.data.user)
        );
        navigate("/MainPage");

      }else {
        toast.error(response.data.message, toastOptions);
      }


    }

    
  };

  

  return (
    <div>
       <div className="login">
      <div className="loginWrapper">
        <div className="loginLeft">
          {/* <h3 className="loginLogo">Liberté<br/>Égalité<br/> Fraternité</h3> */}


          <div className="login-header">
                <img src={logo} className="login-logo" alt="logo" />
                
              </div>
          
        </div>
        <div className="loginRight">
          <form className="loginBox" onSubmit={handleSubmit}>
            <input
              placeholder="Username"
              name = "username"
              // type="username"
              required
              className="loginInput"
              onChange={(e) => handleChange(e)}
              // ref={email}
            />
            {/* <input
              placeholder="Password"
              name = "password"
              type="password"
              required
              minLength="1"
              className="loginInput"
              onChange={(e) => handleChange(e)}
              // ref={password}
            /> */}
            <button className="loginButton" type="submit">
              Log In
            </button>

            <Link to="/register" >
              <button className="loginRegisterButton">
              Create a New Account
                
              </button>
            </Link>
            
          </form>
        </div>
      </div>
    </div>
      <ToastContainer />
    </div>
   
  );
}
