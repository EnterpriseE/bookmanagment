import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {  allMember,updateMember,MemberDele } from "../../apiRoutes";

function Member({ user,setModalOpen,setCurrentMember }) {

    const [members, setAllMember] = useState()
    useEffect(()=>{
        const f = async()=>{

            await axios.get(allMember, {
              // user:data
            }).then(response=>{
              setAllMember(response.data.data);
              console.log(response.data)
            
            })
            .catch(err=>{console.log(err)});
    
            
          }
    
          f()
    },[])


    const openModal = (member) =>{
        setModalOpen(true)
        setCurrentMember(member)
    }

    const del = (e,member) =>{
        e.preventDefault(); 
        if (window.confirm("Are you sure?")){
            

            const c = async()=>{
                // member.isDelete = 1
                member.isDelete = 1
                console.log(member)
                const response = await axios.post(MemberDele, {
                  user:user,
                  member:member,
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
                 <h2 className="text-center">members List</h2>
                
                 <br></br>
                 <div className = "row">
                        <table style = {{maxWidth:"90%",marginLeft:"10vh"}}className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> members Name</th>
                                    <th>member email</th>
                                    <th>member balance</th>
                                    <th>member avatar</th>
                                    {/* <th> Employee Last Name</th> */}
                                    {/* <th> Employee Email Id</th> */}
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    members && members.map(member => 
                                        <tr >
                                             <td> { member.username} </td>   
                                             <td> { member.email} </td>
                                             <td> { member.balance} </td>
                                             <td> { member.avatar} </td>

                                             {/* <td> {employee.lastName}</td> */}
                                             {/* <td> {employee.emailId}</td> */}
                                             <td>
                                                 <button  className="btn btn-info" onClick={() => openModal(member)}>Update </button>
                                                 {/* <button style={{marginLeft: "10px"}} className="btn btn-danger">Delete </button> */}
                                                 <button style={{marginLeft: "10px"} } className="btn btn-danger" onClick={(e) => del(e,member)}>Delete </button>
                                                 
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

export default Member