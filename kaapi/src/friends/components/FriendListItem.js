import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.css'

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <button className={styles.button} onClick={() => recordCoffee(friend)}>☕️</button>
        <div className={styles.name}>{friend.name}</div>
        <div>–{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
    </div>
)
