import React from 'react';
import style from './style.module.scss';
function UserScreen() {
  return (
    <div className={style.MainProfileDiv}>
        <div className={style.profile.container}>
        <div className={style.topPortion}>
        <div className={style.userProfileBgImage}>
            <img src='' alt='' srcSet=''/>
        </div>
      </div>
      <div className={style.bottomPortion}>
        <div className={style.rightide}></div>
        <div className={style.left-side}></div>
      </div>
        </div>
    </div>
  );
}

export default UserScreen;
