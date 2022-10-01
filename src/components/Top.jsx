import React, { useEffect, useState } from 'react'
import { useNavigate,Link } from "react-router-dom";

function Top({user}) {
    const navigate = useNavigate()
    
    const logout = ()=>{
        if(window.confirm('Are you sure to quit')) {
            localStorage.clear();
            navigate("/Login");
        }
    }
  return (
    <div>

    
        <table style = {{cellPadding:"0",width:"100%",height:"64"}}  >
            <tbody>
                <tr style = {{align:"top"}}>
                    {/* <td width="70%"><a ></a></td> */}
                    <td width="30%" style={{ paddingTop:'13px',font:'15px' ,color:'blue'}}> 当前用户:<p>{user&&user.username}</p>&nbsp;<a onClick={logout}>安全退出</a></td>
                </tr>
            </tbody>
            
        </table>
    
</div>
  )
}

export default Top