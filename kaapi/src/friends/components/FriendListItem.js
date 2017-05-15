import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.css'

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><button className={styles.button} onClick={() => recordCoffee(friend)}>☕️</button></div>
    </div>
)
