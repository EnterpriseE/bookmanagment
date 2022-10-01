import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {  allUser,UserDele } from "../../apiRoutes";


function User({user, setCurrentUser,setUserOpenModal}) {

    const [users, setAllUser] = useState()
    useEffect(()=>{
        const f = async()=>{

            await axios.get(allUser, {
              // user:data
            }).then(response=>{
                setAllUser(response.data.data);
                console.log(response.data)
            
            })
            .catch(err=>{console.log(err)});
    
            
          }
    
          f()
    },[])

    const openModal = (user) =>{
        setUserOpenModal(true)
        setCurrentUser(user)
    }

    const del = (e,users) =>{
        e.preventDefault(); 
        if (window.confirm("Are you sure?")){
            

            const c = async()=>{
                // member.isDelete = 1
                users.isDelete = 1
                console.log(users)
                const response = await axios.post(UserDele, {
                  user1:user,
                  user2:users,
                //   isDelete:1
                });
              }
          
              c()
          
              
              alert("Your file is being uploaded!")
              window.location.reload(false);
          }
    }

  return (
    <div style={{overflowX:"hidden"}}>
                 <h2 className="text-center">User List</h2>
                
                 <br></br>
                 <div className = "row">
                        <table style = {{maxWidth:"90%",marginLeft:"10vh"}} className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> users Name</th>
                                    <th> rank</th>
                                    {/* <th> Employee Last Name</th> */}
                                    {/* <th> Employee Email Id</th> */}
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    users&&users.map(users => 
                                        
                                        <tr >
                                             <td> { users.username} </td>   
                                             <td> Level { users.rk}  Admin</td>  
                                             {/* <td> {employee.lastName}</td> */}
                                             {/* <td> {employee.emailId}</td> */}
                                             <td>
                                                 <button  className="btn btn-info" onClick={() => openModal(users)}>Update </button>
                                                 {/* <button style={{marginLeft: "10px"}} className="btn btn-danger">Delete </button> */}
                                                 <button style={{marginLeft: "10px"}} className="btn btn-danger" onClick={(e) => del(e,users)}>Delete </button>
                                             </td>
                                        </tr>
                                    )
                                }
                            </tbody>
                        </table>

                 </div>

            </div>
  )
}

export default User