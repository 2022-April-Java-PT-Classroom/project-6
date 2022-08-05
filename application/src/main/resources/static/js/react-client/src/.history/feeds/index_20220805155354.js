import React, {useState} from 'react';

import {Avatar} from '@mui/material';
import style from './style.module.scss';

function Feeds() {
const [UserPostDescrip, setUserPostDescrip ]= useState("");
  return (
    <div className={style.mainFeedsContainer}>
        <div className={style.topContainer}>
        <div className={style.feedInputContainer}>
            <div className={style.avatarContainer}><Avatar></Avatar></div>
            <div className={style.inputContainer}>
                <input placeholder='Food to post to the community'
                ></input>
                </div>  
        </div>
        <div className={style.feedPostButtonContainer}></div>
       </div> 
       <div className={style.bottomContainer}>
        
       </div>
    </div>
    
  )
}

export default Feeds