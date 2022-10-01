import React from "react";
import { SiGoogleanalytics } from "react-icons/si";
import { BiNews, BiRocket } from "react-icons/bi";
import { FaWallet } from "react-icons/fa";
import { AiFillSetting, AiFillAppstore } from "react-icons/ai";
import { BsCashStack } from "react-icons/bs";
import {BsPersonFill} from "react-icons/bs";
import { MdAccountCircle } from "react-icons/md";
import { IoMenuSharp,IoPersonSharp,IoBook,IoAccessibility } from "react-icons/io5";
import './Sidebar.scss'
export default function Sidebar({setCurrentLink}) {
  const links = [
    
    {
      title: "User",
      icon: IoPersonSharp,
      part:1
    },

    {
      title: "Admins",
      icon: IoAccessibility,
      part:2
    },

    {
      title: "Book",
      icon: IoBook,
      part:3
    },

    {
      title: "Type",
      icon: IoMenuSharp,
      part:4
      // link:"/"
    },

    {
      title: "Record",
      icon: AiFillAppstore,
      part:5
      // link:"/"
    },
  
  ];

  const changeMenu = (link) =>{
    console.log(link)
    setCurrentLink(link)
  }


  return (
    <div className="sidebar">
      <div className="brand">
        <h2>
          RE<span>ACT</span>
        </h2>
      </div>
      <ul className="links">
        {links.map((link) => {
          return (
            <li>
              <div onClick={() => changeMenu(link)}>
                {<link.icon />}
                {link.title}
              </div>
            </li>
          );
        })}
      </ul>
    </div>
  );
}