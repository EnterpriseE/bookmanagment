import React, { useEffect, useState } from 'react'
import axios from 'axios';
import {  allRecords  } from "../../apiRoutes";
import ReactPaginate from "react-paginate";


function Record() {

    const [records, setAllRecords] = useState()
    const [pageNumber, setPageNumber] = useState(0);

    const [pageCount, setPageCount] = useState(0);
    const [displayRecords, setDisplayRecords] = useState()

  
    const recordsPerPage = 9;
    const pagesVisited = pageNumber * recordsPerPage;


    useEffect(()=>{
        const f = async()=>{

            await axios.get(allRecords, {
              // user:data
            }).then(response=>{
                setAllRecords(response.data.data);
                setPageCount(Math.ceil(response.data.data.length / recordsPerPage))
                console.log(response.data)
            
            })
            .catch(err=>{console.log(err)});
    
            
          }
    
          f()
    },[])


    useEffect(()=>{
        if(records){
           

            const displayRecords = records
            .slice(pagesVisited, pagesVisited + recordsPerPage)
            .map((record) => {
            return (
                <tr >
                    <td> { record.date} </td>  
                    <td> { record.subject} </td>   
                    <td> { record.action} </td> 
               
                   
            </tr>
            );
            });

            setDisplayRecords(displayRecords)

            
        }


    },[records,pageNumber])

    const changePage = ({ selected }) => {
        setPageNumber(selected);
      };


  return (
    <div style={{overflowX:"hidden"}}>
                 <h2 className="text-center">Record List</h2>
                 {/* <button style = {{marginLeft:"10vh"}} className="btn btn-info">Add </button> */}
                 <br></br>
                 <div className = "row">
                        <table style = {{maxWidth:"90%",marginLeft:"10vh"}}className = "table table-striped table-bordered">

                            <thead>
                                <tr>
                                    <th>Record create</th>
                                    <th>Record subject</th>
                                    <th>Action</th>
                                    {/* <th> Employee Last Name</th> */}
                                    {/* <th> Employee Email Id</th> */}

                                </tr>
                            </thead>
                            <tbody>
                                {displayRecords?displayRecords:<></>}
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

export default Record