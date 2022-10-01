import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {  allTypes,typeDele  } from "../../apiRoutes";


function Type({types, setAllTypes,setIsAdd,setCurrentType,setTypeOpenModal}) {

    
    useEffect(()=>{
        const f = async()=>{

            await axios.get(allTypes, {
              // user:data
            }).then(response=>{
                setAllTypes(response.data.data);
                console.log(response.data)
            
            })
            .catch(err=>{console.log(err)});
    
            
          }
    
          f()
    },[])

    const openModal = (type) =>{
        setTypeOpenModal(true)
        console.log(type)
        setCurrentType(type)
    }

    const setAdd = ()=>{
        setTypeOpenModal(true)
        setIsAdd(true)
    }

    const del = (e,type) =>{
        if (window.confirm("Are you sure?")){
            e.preventDefault(); 

            console.log(type)
            const r = {
                // id:currentBook.id,
                id:type.id,
                // name:books.name,
                // brief:books.brief,
                // price:books.price,
                // author:books.author,
                isDelete:1,
                
                // pic:null,
                // link:null,
                // typeResults:null
          
              }
            //   console.log(typeResults)
              const f = async() =>{
                await axios.post(typeDele,r)
              }
              f()

              alert("Your file is being uploaded!")
              window.location.reload(false);
          }
    }



  return (
    <div style={{overflowX:"hidden"}}>
                 <h2 className="text-center">Type List</h2>
                 <button style = {{marginLeft:"10vh"}} className="btn btn-info" onClick={() => setAdd()}>Add </button>
                 <br></br>
                 <div className = "row">
                        <table style = {{maxWidth:"90%",marginLeft:"10vh"}}className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th>Type id</th>
                                    <th> types Name</th>
                                    <th>parent Id</th>
                                    {/* <th> Employee Last Name</th> */}
                                    {/* <th> Employee Email Id</th> */}
                                    <th> Actions</th>

                                </tr>
                            </thead>
                            <tbody>
                                {
                                    types&&types.map(type => 
                                        <tr >
                                            <td> { type.id} </td>  
                                             <td> { type.type_name} </td>   
                                             <td> { type.parentId} </td> 
                                             {/* <td> {employee.lastName}</td> */}
                                             {/* <td> {employee.emailId}</td> */}
                                             <td>
                                                 <button  className="btn btn-info" onClick={() => openModal(type)}>Update </button>
                                                 {/* <button style={{marginLeft: "10px"}} className="btn btn-danger">Delete </button> */}
                                                 {/* <button style={{marginLeft: "10px"}} className="btn btn-danger" >Delete </button> */}
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

export default Type