import React, {useState} from 'react';

import {Avatar} from '@mui/material';
import {Button} from '@mui/material';
import style from './style.module.scss';

function Feeds() {
const [UserPostDescrip, setUserPostDescrip ]= useState("");
  return (
    <div className={style.mainFeedsContainer}>
        <div className={style.topContainer}>
        <div className={style.feedInputContainer}>
            <div className={style.avatarContainer}><Avatar src=""></Avatar></div>
            <div className={style.inputContainer}>
                <input className={style.postDesc} placeholder='Food to post to the community'
                onChange={(e) =>{setUserPostDescrip(e.target.value)}}
                />
                </div>  
        </div>
        <div className={style.feedPostButtonContainer}>
            <Button onClick></Button>
        </div>
       </div> 
       <div className={style.bottomContainer}>
        
       </div>
    </div>
    
  )
}

export default Feeds