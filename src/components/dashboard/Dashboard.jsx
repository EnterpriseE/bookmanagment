import React, { useEffect } from "react";
// import ActivityLog from "./ActivityLog";
// import Expenses from "./Expenses";
// import Navbar from "./Navbar";
import Navbar from "../Navbar/Navbar";
// import Overview from "./Overview";
// import Transactions from "./Transactions";
// import Wallets from "./Wallets";
import 'bootstrap/dist/css/bootstrap.min.css';

import User from "../User/User";
import Member from "../Member/Member";
import Type from "../Type/Type";
import Book from "../Book/Book"
import Record from "../Records/Records";
export default function DashboardApp({
  user
  ,link
  ,setUserOpenModal
  ,setModalOpen
  ,setCurrentMember
  ,setCurrentUser
  
  ,setTypeOpenModal
  ,setCurrentType

  ,setBookOpenModal
  ,setCurrentBook

  ,part
  ,setIsAdd

  ,setIsBookAdd
  ,types
  ,setAllTypes

}) {

  console.log(link)
  // useEffect(()=>{



  // },[])
  return (
    <div class="app">
      
      <Navbar user = {user}/>
      <div style={{maxHeight:'90vh',overflowY: 'scroll'}}>
        {link&&
        link.part===1 ?<Member user = {user} setModalOpen = {setModalOpen} setCurrentMember = {setCurrentMember}/>
      :link.part===2?<User user = {user} setUserOpenModal = {setUserOpenModal} setCurrentUser ={setCurrentUser}/>
      :link.part===3?<Book types={types} setAllTypes=  {setAllTypes} setIsBookAdd = {setIsBookAdd} setBookOpenModal = {setBookOpenModal} setCurrentBook ={setCurrentBook}/>
      :link.part===4?<Type types={types} setAllTypes=  {setAllTypes} setIsAdd = {setIsAdd} setTypeOpenModal = {setTypeOpenModal} setCurrentType ={setCurrentType}/>
      :< Record/>
    }
        
        

        
        
      </div>
      
    </div>
  );
}