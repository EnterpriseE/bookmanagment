import React, { useEffect, useState } from 'react'
import "./Modal.css";
import { updateUser } from "../../apiRoutes";
import axios from 'axios';

function UserModal({ user,currentUser, setUserOpenModal }) {
  console.log(user)
  const [rk, setRank] = useState(currentUser.rk);
  const [username, setUsername] = useState(currentUser.username);
  const [email, setEmail] = useState(currentUser.email);
  const [brief, setBrief] = useState(currentUser.brief);


  const sub = ()=>{
    currentUser.rk = rk;
    currentUser.username = username;
    currentUser.email = email;
    currentUser.brief = brief
    const c = async()=>{
      const response = await axios.post(updateUser, {
        user1:user,
        user2:currentUser
      });
    }

    c()

    
    alert("Your file is being uploaded!")
    window.location.reload(false);
    setUserOpenModal(false);
  }

  // const setMemberBalance() =>{

  // }

  // const update = ()=>{
    
    
  // }
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        <div className="titleCloseBtn">
          <button
            onClick={() => {
                setUserOpenModal(false);
            }}
          >
            X
          </button>
        </div>



        <div className = "container">
                        <div className = "row">
                            <div >
                                <h3 className="text-center">Update User</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> username: </label>
                                            <input   className="form-control" 
                                                onChange={event => setUsername(event.target.value)}
                                                value={username}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> rank: </label>
                                            <input  className="form-control" 
                                                onChange={event => setRank(event.target.value)}
                                                value={rk}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> email: </label>
                                            <input  className="form-control" 
                                                onChange={event => setEmail(event.target.value)}
                                                value={email}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> brief: </label>
                                            <input  className="form-control" 
                                                onChange={event => setBrief(event.target.value)}
                                                value={brief}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> id: </label>
                                            <input disabled name={currentUser&&currentUser.id} className="form-control" 
                                                value={currentUser&&currentUser.id}/>
                                        </div>

                                        {/* <div className = "form-group">
                                            <label> username: </label>
                                            <input  name={currentMember&&currentMember.username} className="form-control" 
                                                value={currentMember&&currentMember.username}/>
                                        </div> */}
                                        {/* <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div> */}

                                        <button className="btn btn-success" onClick={sub}>Save</button>

                                        <button className="btn btn-danger" onClick={() => {
                                            setUserOpenModal(false);
                                          }} style={{marginLeft: "10px"}}>Cancel</button>

                                        {/* <button
                                          onClick={() => {
                                            setOpenModal(false);
                                          }}
                                          id="cancelBtn"
                                        >
                                          Cancel
                                        </button>
                                        <button onClick={sub}>Continue</button> */}
                                    </form>
                                </div>
                            </div>
                        </div>

                   </div>



        <div className="footer">
          
        </div>
      </div>
    </div>
  );
}

export default UserModal;