import React from 'react';
import style from './style.module.scss';
function Post() {
  return (
    <div className={style.mainPostContainer}>
        <div className={style.headPosition}>
            <div className={style.userInfoPortion}></div>
            <div className={style.postPortion}></div>
        </div>
        <div className={style.bodyPosition}></div>
        <div className={style.footerPosition}></div>
    </div>
  );
}

export default Post;
