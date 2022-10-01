import axios from "axios";
import React, { useState, useEffect } from "react";
import "./register.css";
import { useNavigate,Link } from "react-router-dom";
// import {  Link } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { registerRoute } from "../../apiRoutes";
import logo from '../../logo.svg';


//注册页
export default function Register() {
  const navigate = useNavigate();

  const [values, setValues] = useState({
    username: "",
    // email: "",
    // password: "",
    // confirmPassword: "",
  });

  const [arrivalUser, setArrivalUser] = useState();

  // useEffect(()=>{
  //   console.log(socket.current)
  //   socket.current.on("recieve-register", (data) => {
  //     // console.log(data)
  //     setArrivalUser(data);
  //   });

  // },[])

  const toastOptions = {
    position: "bottom-right",
    autoClose: 8000,
    pauseOnHover: true,
    draggable: true,
    theme: "dark",
  };


  const handleValidation = () => {
    const {  username } = values;
    // if (password !== confirmPassword) {
    //   toast.error(
    //     "Password and confirm password should be same.",
    //     toastOptions
    //   );
    //   return false;
    // } 
    
    if (username.length <= 1) {
      toast.error(
        "Username should be greater than 1 characters.",
        toastOptions
      );
      return false;
    }
    
    // else if (password.length <= 1) {
    //   toast.error(
    //     "Password should be equal or greater than 1 characters.",
    //     toastOptions
    //   );
    //   return false;
    // } else if (email === "") {
    //   toast.error("Email is required.", toastOptions);
    //   return false;
    // }

    return true;
  };


  const handleChange = (event) => {
    setValues({ ...values, [event.target.name]: event.target.value });
  };

  // const navigate = useNavigate()
  const handleClick = async (e) => {
    e.preventDefault();

    if(handleValidation(e)){

      const {  username } = values;
      // const user = {
      //   username = 
      // }

      const { data } = await axios.post(registerRoute, {
        username:username,
        // email:email,
        // password:password,
        brief:"nothing",
        imageUrl:"",
      });

      console.log(data)

      
     
    }
   
  };


 

  return (
    
    <div>
       <div className="register">
        <div className="registerWrapper">
          <div className="registerLeft">
              <div className="login-header">
                <img src={logo} className="login-logo" alt="logo" />
                
              </div>
          
        
            
          </div>
          <div className="registerRight">
            <form className="registerBox" onSubmit={handleClick}>
              <input
                placeholder="Username"
                required
                // ref={username}
                name="username"
                onChange={(e) => handleChange(e)}
                className="registerInput"
              />
              
              <button className="registerButton" type="submit">
                Sign Up
              </button>


              <Link to="/login" >
                <button className="registerRegisterButton" >Log into Account</button>
              </Link>
              
            </form>
          </div>
        </div>
        <ToastContainer />
      </div>
    </div>
     
    
    // </ToastContainer>
    
  );
}
