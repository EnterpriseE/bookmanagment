import React from "react";
import { VscArrowSwap } from "react-icons/vsc";
import { IoMdNotificationsOutline } from "react-icons/io";
import { FaSearch } from "react-icons/fa";
import { FiMail } from "react-icons/fi";
import './Navbar.scss'
// import avatar from "./logo192.png";
export default function Navbar({user}) {
  return (
    <div className="navbar">
      <div className="avatar">
        <img src="logo192.png" alt="" />
        <div className="info">
          <h4 className="title" style={{"color":"white"}}> {user&& user.username}</h4>
          {/* <h6 className="status">{user&& user.username}</h6> */}
        </div>
      </div>
      <div className="brand">
        <h2>
          RE<span>ACT</span>
        </h2>
      </div>
      {/* <div className="navbar__info">
     
        <div className="search__bar">
          <input type="text" placeholder="Search" />
          <FaSearch />
        </div>
      </div> */}
    </div>
  );
}