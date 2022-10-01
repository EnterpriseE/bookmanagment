import React, { useEffect, useState } from 'react'
import { useNavigate,Link } from "react-router-dom";
import Navbar from '../../components/Navbar/Navbar'
import Sidebar from '../../components/Sidebar/sidebar'
import DashboardApp from '../../components/dashboard/Dashboard';
import Top from '../../components/Top'
import Footer from '../../components/footer/Footer';
import './base.css'
import {  allMember } from "../../apiRoutes";
import axios from 'axios';
import Modal from '../../components/MemberModal/Modal';
import UserModal from '../../components/userModal/UserModal';
import TypeModal from '../../components/TypeModal/TypeModal';
import BookModal from '../../components/BookModal/BookModal';
import { AiFillSetting, AiFillAppstore } from "react-icons/ai";
import {  allTypes  } from "../../apiRoutes";


function MainPage() {

  const navigate = useNavigate()
  const [user,setCUser] = useState()
  const [members,setAllMember] = useState()
  const [currentMember, setCurrentMember] = useState()
  const [modalOpen, setModalOpen] = useState(false);
  const [currentUser,setCurrentUser] = useState(false);
  const [userOpenModal,setUserOpenModal] = useState(false);

  const [currentType,setCurrentType] = useState(false);
  const [typeOpenModal,setTypeOpenModal] = useState(false);
  const [typeAddOpenModal,setTypeAddOpenModal] = useState(false);

  const [currentBook,setCurrentBook] = useState(false);

  const [bookOpenModal,setBookOpenModal] = useState(false);
  // const [bookAddOpenModal,setBookAddOpenModal] = useState(false);

  const [link,setCurrentLink] = useState({ })

  const [isAdd, setIsAdd] = useState(false)
  const [isBookAdd, setIsBookAdd] = useState(false)

  const [types, setAllTypes] = useState()


  console.log(JSON.parse(localStorage.user))
  useEffect(()=>{
      console.log("gg")
      const d = async()=>{
        const data =  JSON.parse(localStorage.user)
        setCUser(data)
      }

      d()
      setCurrentLink(
        {
          title: "User",
          icon: AiFillAppstore,
          part:1
        }
      )

      const f = async()=>{

        await axios.get(allTypes, {
          // user:data
        }).then(response=>{
        //   const ts = response.data.data
        //   obj['newKey'] = obj['oldKey'];
        // delete obj['oldKey'];
            setAllTypes(response.data.data);
            console.log(response.data)
        
        })
        .catch(err=>{console.log(err)});

        
      }

      f()
              
      // console.log(data)
      
 

  },[])

  

  
  return (
    <div className="main">
      <div className="dashboard">
      
        <Sidebar setCurrentLink = {setCurrentLink}/>
        <DashboardApp 
        user = {user} 
        link = {link}
        setModalOpen = {setModalOpen} 
        setCurrentMember = {setCurrentMember}
        setUserOpenModal = {setUserOpenModal}
        setCurrentUser = {setCurrentUser}

        setTypeOpenModal = {setTypeOpenModal}
        setCurrentType = {setCurrentType}

        setBookOpenModal = {setBookOpenModal}
        setCurrentBook = {setCurrentBook}


        setIsAdd = {setIsAdd}
        setIsBookAdd = {setIsBookAdd}

        types={types}
        setAllTypes=  {setAllTypes}
        />
        {modalOpen && <Modal user = {user} setOpenModal={setModalOpen} currentMember = {currentMember}/>}
        {userOpenModal &&  <UserModal user = {user} setUserOpenModal={setUserOpenModal} currentUser = {currentUser}/>}
        {typeOpenModal &&  <TypeModal isAdd = {isAdd} user = {user} setTypeOpenModal={setTypeOpenModal} currentType = {currentType} setIsAdd = {setIsAdd}/>}
        {bookOpenModal &&  <BookModal types={types} setAllTypes=  {setAllTypes} isBookAdd = {isBookAdd} user = {user} setBookOpenModal={setBookOpenModal} currentBook = {currentBook} setIsBookAdd = {setIsBookAdd}/>}

      </div>
      {/* <Footer /> */}
    </div>
    
  )
}

export default MainPage