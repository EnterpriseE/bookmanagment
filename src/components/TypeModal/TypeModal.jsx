import React, { useEffect, useState } from 'react'
import "./Modal.css";
import { updateType,addType } from "../../apiRoutes";
import axios from 'axios';

function TypeModal({ isAdd,user,currentType, setTypeOpenModal,setIsAdd }) {
  console.log(isAdd)
  // const [rk, setRank] = useState(currentType.rk);
  const [id, setTypeId] = useState(currentType.id);

  const [name, setTypename] = useState(currentType.name);
  const [parentId, setParentId] = useState(currentType.parentId);
  // const [email, setEmail] = useState(currentType.email);
  // const [brief, setBrief] = useState(currentType.brief);


  const sub = ()=>{
    // const type = {
    //   "id":id,
    //   "type_name":name,
    //   "parentId":parentId
    // }

    // console.log(type)
    const c = async()=>{
      const response = await axios.post(updateType, {
        "type":{
          id:id,
          type_name:name,
          parentId:parentId
        }
        
      });
    }

    const c2 = async()=>{
      const response = await axios.post(addType, {
        
        type_name:name,
        parentId:parentId
      });
    }

    if(isAdd===true){
      c2()
    }else{
      c()
    }
    

    
    alert("Your file is being uploaded!")
    window.location.reload(false);
    setTypeOpenModal(false);
    setIsAdd(false);
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
                setTypeOpenModal(false);
                setIsAdd(false)
            }}
          >
            X
          </button>
        </div>



        <div className = "container">
                        <div className = "row">
                            <div >
                                <h3 className="text-center">Update Type</h3>
                                <div className = "card-body">
                                    <form>
                                        <div className = "form-group">
                                            <label> Type id: </label>
                                            <input   className="form-control" 
                                            disabled
                                                onChange={event => setTypeId(event.target.value)}
                                                value={id}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Type name: </label>
                                            <input  className="form-control" 
                                                onChange={event => setTypename(event.target.value)}
                                                value={name}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> parentId: </label>
                                            <input  className="form-control" 
                                                onChange={event => setParentId(event.target.value)}
                                                value={parentId}/>
                                        </div>

                                       


                                        <button className="btn btn-success" onClick={sub}>Save</button>

                                        <button className="btn btn-danger" onClick={() => {
                                            setTypeOpenModal(false);
                                            setIsAdd(false)
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

export default TypeModal;