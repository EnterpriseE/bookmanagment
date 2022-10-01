import React, { useEffect, useState } from 'react'
import "./Modal.css";
import { updateMember } from "../../apiRoutes";
import axios from 'axios';

function Modal({ user,currentMember, setOpenModal }) {
  console.log(user)
  const [balance, setBalance] = useState(currentMember.balance);
  const [username, setUsername] = useState(currentMember.username);


  const sub = ()=>{
    currentMember.balance = balance;
    currentMember.username = username;
    const c = async()=>{
      const response = await axios.post(updateMember, {
        user:user,
        member:currentMember,
        isDelete:0
      });
    }

    c()

    
    alert("Your file is being uploaded!")
    window.location.reload(false);
    setOpenModal(false);
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
              setOpenModal(false);
            }}
          >
            X
          </button>
        </div>



        <div className = "container">
                        <div className = "row">
                            <div >
                                <h3 className="text-center">Update Users</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> username: </label>
                                            <input   className="form-control" 
                                                onChange={event => setUsername(event.target.value)}
                                                value={username}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> balance: </label>
                                            <input  className="form-control" 
                                                onChange={event => setBalance(event.target.value)}
                                                value={balance}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> id: </label>
                                            <input disabled name={currentMember&&currentMember.id} className="form-control" 
                                                value={currentMember&&currentMember.id}/>
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
                                            setOpenModal(false);
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

export default Modal;