import React from 'react'

import styles from './FriendListItem.css'

export default ({friend}) => (
    <div className={styles.friend}>
        <button className={styles.button}>☕️</button>
        <div className={styles.name}>{friend.name}</div>
    </div>
)
