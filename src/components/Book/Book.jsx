import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {  allBooks,saveBook,dele } from "../../apiRoutes";
import ReactPaginate from "react-paginate";

function Book({types, setAllTypes,setIsBookAdd,setBookOpenModal, setCurrentBook}) {

    
    const [books, setAllBooks] = useState()
    const [displayBooks, setDisplayBooks] = useState()


    
// const [users, setUsers] = useState(JsonData.slice(0, 50));
  const [pageNumber, setPageNumber] = useState(0);

  const [pageCount, setPageCount] = useState(0);


  const booksPerPage = 4;
  const pagesVisited = pageNumber * booksPerPage;

  const maniBooks =(books) =>{

    console.log(books)
    var tempDic = {}
    for(var i = 0; i< books.length;i++){

        if(tempDic[books[i].id]==null){
            tempDic[books[i].id] = books[i];
        }else{
            const t = books[i].typeResults[0]
            tempDic[books[i].id].typeResults.push(t)//typeResults 第一位放到temp里的那个typeresult里面
        }
        
    }


    var returnArray = []
    for (var key in tempDic){//go over the dict and put them into an array
        returnArray.push(tempDic[key])
    }

    console.log(returnArray)
    setAllBooks(returnArray)


  }
  


    useEffect(()=>{
        const f = async()=>{

            await axios.get(allBooks, {
              // user:data
            }).then(response=>{
                // setAllBooks(response.data.data);

                maniBooks(response.data.data)
                // setAllSliceBooks(response.data.data.slice(0, 5))
                setPageCount(Math.ceil(response.data.data.length / booksPerPage))
                // console.log(response.data.data)
            
            })
            .catch(err=>{console.log(err)});
    
            
          }
    
          f()


    },[])

    useEffect(()=>{
        if(books){
            const displayBooks = books
            .slice(pagesVisited, pagesVisited + booksPerPage)
            .map((books) => {
            return (
                <tr >
                    <td> { books.name} </td>   
                    <td> { books.price} </td>   
                    <td> { books.pic} </td>   
                    <td> { books.author} </td>   
                    <td> { books.link} </td>   
                    <td> { books.name} </td>   
                    {/* <td> {employee.lastName}</td> */}
                    {/* <td> {employee.emailId}</td> */}
                    <td>
                        <button  className="btn btn-info" onClick={() => openModal(books)}>Update </button>
                        {/* <button style={{marginLeft: "10px"}} className="btn btn-danger">Delete </button> */}
                        <button style={{marginLeft: "10px"}} className="btn btn-danger" onClick={(e) => del(e,books)}>Delete </button>
                    </td>
            </tr>
            );
            });
    
            setDisplayBooks(displayBooks)
        }
       
    },[books,pageNumber])


//   const pageCount = Math.ceil(books.length / booksPerPage);

  const changePage = ({ selected }) => {
    setPageNumber(selected);
  };



    const openModal = (book) =>{
        setBookOpenModal(true)
        setCurrentBook(book)
    }

    const del = (e,books) =>{
        if (window.confirm("Are you sure?")){
            e.preventDefault(); 

            console.log(books)
            const r = {
                // id:currentBook.id,
                id:books.id,
                name:books.name,
                brief:books.brief,
                price:books.price,
                author:books.author,
                isDelete:1,
                
                pic:null,
                link:null,
                typeResults:null
          
              }
            //   console.log(typeResults)
              const f = async() =>{
                await axios.post(dele,r)
              }
              f()

              alert("Your file is being uploaded!")
              window.location.reload(false);
          }
    }

    const setAdd = ()=>{
        setBookOpenModal(true)
        setIsBookAdd(true)
    }

    // setIsBookAdd
  


  return (
    <div style={{overflowX:"hidden"}}>
                 <h2 className="text-center">books List</h2>
                 <button style = {{marginLeft:"10vh"}} className="btn btn-info" onClick={() => setAdd()}>Add </button>
                 <br></br>
                 <div className = "row">
                        <table style = {{maxWidth:"90%",marginLeft:"10vh"}}className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th> books Name</th>
                                    <th> books price</th>
                                    <th> books pic</th>
                                    <th> books author</th>
                                    <th> books link</th>
                                    <th> books Type</th>
                                    {/* <th> Employee Last Name</th> */}
                                    {/* <th> Employee Email Id</th> */}
                                    <th> Actions</th>
                                </tr>
                            </thead>
                            <tbody>

                                {displayBooks?displayBooks:<></>}
                                
                            </tbody>
                        </table>

                        <ReactPaginate
        previousLabel={"Previous"}
        nextLabel={"Next"}
        pageCount={pageCount}
        onPageChange={changePage}
        containerClassName={"paginationBttns"}
        previousLinkClassName={"previousBttn"}
        nextLinkClassName={"nextBttn"}
        disabledClassName={"paginationDisabled"}
        activeClassName={"paginationActive"}
      />

                 </div>

            </div>
  )
}

export default Book