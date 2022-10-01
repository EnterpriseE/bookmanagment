import React, { useEffect, useState } from 'react'
import "./Modal.css";
import { updateBook,uploadPic,uploadLink,saveBook } from "../../apiRoutes";
import axios from 'axios';
import Multiselect from "multiselect-react-dropdown";

function BookModal({ types, setAllTypes,isBookAdd,user,currentBook, setBookOpenModal,setIsBookAdd }) {
  console.log(types)

  console.log(currentBook.typeResults)
    // const [rk, setRank] = useState(currentType.rk);
  // const [id, setTypeId] = useState(currentType.id);

  const [name, setBookname] = useState(currentBook.name);
  const [price, setPrice] = useState(currentBook.price);

  const [brief, setBrief] = useState(currentBook.brief);
  const [author, setAuthor] = useState(currentBook.author);

  const [imageName, setImageName] = useState(currentBook.pic)
  const [linkName, setLinkName] = useState(currentBook.link)

  const [imageData, setImageData] = useState(undefined)
  const [linkData, setLinkData] = useState(undefined)
  const [preview, setPreview]  = useState('')
  const [previewLink, setPreviewLink]  = useState('')


  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(10);
  const [typeResults, setTypeResults] = useState(currentBook.typeResults)
  const [selectedTypes, setSelectedTypes] = useState([])



  // const [loading, setLoading] = useState(false)
  // const [parentId, setParentId] = useState(currentType.parentId);
  // const [email, setEmail] = useState(currentType.email);
  // const [brief, setBrief] = useState(currentType.brief);


  useEffect(()=>{
    var l = []
    for(var i = 0; i< typeResults.length;i++){
      if(typeResults[i].id===types[i].id){
        l.push(types[i])
      }
      // console.log(typeResults[i])
      
    }
    console.log(l)

    setSelectedTypes(l)
  },[])
  const sub = async(e)=>{
    e.preventDefault(); 


    console.log(imageData)
    console.log(linkData)

    
    if(imageData!=undefined){
      const res = await fetch(
        uploadPic,
        {
          method: 'POST',
          body: imageData
        }
      )
    }
    

    if(linkData!=undefined){
      const res2 = await fetch(
        uploadLink,
        {
          method: 'POST',
          body: linkData
        }
      )
    }
   
    if(isBookAdd===true){

      const r = {
        // id:currentBook.id,
        name:name,
        brief:brief,
        price:price,
        author:author,
        isDelete:0,
        
        pic:imageName,
        link:linkName,
        typeResults:typeResults
  
      }
      console.log(typeResults)
      const res3 = await axios.post(saveBook,r)


    }else{
      const r = {
        id:currentBook.id,
          name:name,
          brief:brief,
          price:price,
          author:author,
          isDelete:0,
          
          pic:imageName,
          link:linkName,
          typeResults:typeResults
        
  
      }
      console.log(typeResults)
      const res4 = await axios.post(saveBook,r)
    }

    
    alert("Your file is being uploaded!")
    window.location.reload(false);
    setBookOpenModal(false);
    setIsBookAdd(false);
  }

  const uploadImage = async e => {
    const files = e.target.files
    console.log(files)
    const data = new FormData()
    data.append('file', files[0])
    console.log(files[0].name)
    // data.append('upload_preset', 'darwin')
    // setLoading(true)
    setImageName("/Images/"+files[0].name)
    setImageData(data)
    
    setPreview(URL.createObjectURL(e.target.files[0]))

  }


  const uploadFile = async e => {
    const files = e.target.files
    console.log(files)
    const data = new FormData()
    data.append('file', files[0])
    console.log(data)
    // data.append('upload_preset', 'darwin')
    // setLoading(true)
    setLinkName("/file/"+files[0].name)
    setLinkData(data)
    setPreviewLink(URL.createObjectURL(e.target.files[0]))
   
  }


  



  return (
    <div className="modalBackground">
      <div className="bookmodalContainer">
        <div className="titleCloseBtn">
          <button
            onClick={() => {
                setBookOpenModal(false);
            }}
          >
            X
          </button>
        </div>


        <h3 className="text-center">Update Book</h3>

        <div className = "bookcontainer">
                        <div className='img-box'>
                          {/* <h3>Original image</h3> */}
                            <div className='img-inside'>
                              {preview?
                              <img src={preview} alt="" />
                              :currentBook.pic?<img src={currentBook.pic} alt="" />
                              :<img src="logo192.png" alt="" />}
                            </div>
                              
                            </div>


                        <div className = "row">
                            


                            <div className='rightPart'>
                                

                                
                                <div className = "card-body">
                                    <form>
                                        {/* <div className = "form-group">
                                            <label> Book id: </label>
                                            <input   className="form-control" 
                                            disabled
                                                // onChange={event => setBookId(event.target.value)}
                                                value={id}/>
                                        </div> */}

                                        <div className = "form-group">
                                            <label> Book name: </label>
                                            <input  className="form-control" 
                                                onChange={event => setBookname(event.target.value)}
                                                value={name}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Book Price: </label>
                                            <input  className="form-control" 
                                                onChange={event => setPrice(event.target.value)}
                                                value={price}/>
                                        </div>

                                        <div className = "form-group">
                                            <label> Book author: </label>
                                            <input  className="form-control" 
                                                onChange={event => setAuthor(event.target.value)}
                                                value={author}/>
                                        </div>

                                       

                                        <div className = "form-group">
                                            <label> Type Select: </label>
                                            {types&&
                                            <Multiselect
                                              // isObject={true}
                                              onRemove={(event) => {
                                                console.log(event);
                                                setTypeResults(event)
                                              }}
                                              onSelect={(event) => {
                                                console.log(event);
                                                setTypeResults(event)
                                              }}

                                              
                                              // isObject={true}
                                              // fields = {{value:"id",text:"name"}}
                                              // dataSource = {types}
                                              // value = {typeNames}
                                              options={types}
                                              displayValue = "type_name"
                                              selectedValues={typeResults}
                                              // defaultValue={typeResults}
                                              showCheckbox
                                            />}
                                            
                                        </div>
                                        
                                        <div className = "form-group">
                                            <label> Book Brief: </label>
                                            <textarea  className="form-control" style = {{height:" 20vh"}}
                                                onChange={event => setBrief(event.target.value)}
                                                value={brief}/>
                                        </div>

                                        

                                        <div className = "form-group">
                                        <label>Upload Image</label>
                                          <input
                                            type="file"
                                            name="file"
                                            placeholder="Upload an image"
                                            onChange={uploadImage}
                                          />
                                          </div>
                                          <div>
                                          <label>Upload Link</label>
                                          <input
                                            type="file"
                                            name="file"
                                            placeholder="Upload an Link"
                                            onChange={uploadFile}
                                          />
                                          {currentBook.link&&<a href={'D:/VScodeWorkspace/book/public'+currentBook.link } download> attached file</a>}
                                          
                                          {/* <a href={previewLink}> </a> */}
                                          </div>
                                          
                                          {/* <img src={image} style={{ width: '300px' }} /> */}
                                          {/* {loading ? (
                                            <h3>Loading...</h3>
                                          ) : (
                                            <img src={image} style={{ width: '300px' }} />
                                          )} */}

                                       


                                        <button className="btn btn-success" onClick={(e)=>sub(e)}>Save</button>

                                        <button className="btn btn-danger" onClick={() => {
                                            setBookOpenModal(false);
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

export default BookModal;