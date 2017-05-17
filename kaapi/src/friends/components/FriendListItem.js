import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.css'
import Button from '../../layout/Button'

const coffee = (
    <svg className={styles.icon} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 92.1 100">
        <path d="M49.4 32.4c.1.8-.5 1.5-1.3 1.6-.8.1-1.6-.4-1.7-1.2-.2-1.2-.5-2.6-.8-4.1-1.2-5-2.4-10.3 1.3-13.4 3.8-3.2 2.2-7 .7-10.7-.4-.9-.7-1.8-1.1-2.8-.3-.7.2-1.5 1-1.8s1.7.2 1.9.9c.3.8.7 1.7 1 2.6 1.9 4.7 6.5 9.9 1.1 14.4-2.4 2-4 5.9-3 10.2.4 1.5.7 2.9.9 4.3zm-13.9 0c.1.8-.5 1.5-1.3 1.6-.8.1-1.6-.4-1.7-1.2-.2-1.2-.5-2.6-.8-4-1.2-5-2.4-10.3 1.3-13.4 3.8-3.2 2.2-7 .7-10.7-.4-.9-.7-1.8-1.1-2.8-.3-.7.2-1.5 1-1.8.8-.2 1.7.2 1.9.9.3.8.7 1.7 1 2.6 1.9 4.7 5.8 9.9.4 14.4-2.4 2-3.2 5.9-2.3 10.2.3 1.4.7 2.8.9 4.2zm-12.8 0c-.2-1.4-.5-2.8-.9-4.3-1-4.2.3-8.7 2.7-10.7 5.3-4.5 1.1-9.1-.9-13.8-.4-.9-.7-1.8-1-2.6-.3-.7-1.1-1.1-1.9-.9-.8.2-1.3 1-1 1.8.3 1 .7 1.9 1.1 2.8 1.6 3.8 3.2 7.5-.7 10.7-3.7 3.1-2.5 8.4-1.3 13.4.3 1.4.6 2.8.8 4 .1.8.9 1.3 1.7 1.2 1-.1 1.5-.9 1.4-1.6zm47.3 15c28.3-4.3 32 30.9-4.6 39.1-2.3 4.4-5.4 8.3-9 11.5-1.5 1.3-3.2 2-5.3 2H18.9c-2 0-3.8-.7-5.3-2C5.3 90.6 0 79.7 0 67.6v-20-1.3c0-1.2.4-2.3 1.2-3.2s1.9-1.3 3.2-1.3h61.2c1.2 0 2.3.5 3.2 1.3.8.9 1.3 2 1.2 3.2v1.1zm-1.3 30.4c15.5-4.3 23-27 1.3-22.3v12c0 3.6-.4 7.1-1.3 10.3zm-46.4 8.8c-2.4-2.4-4.4-5.4-5.7-8.8-1.3-3.4-2-7.3-2-11.3V50.7c0-1.7-1.4-3.1-3.1-3.1-1.7 0-3.1 1.4-3.1 3.1v15.8c0 4.8.9 9.4 2.5 13.5 1.6 4.2 4 8 7.1 11 1.2 1.2 3.2 1.2 4.4 0 1.2-1.2 1.1-3.2-.1-4.4z"/>
    </svg>
)

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><Button action={coffee} clickHandler={() => recordCoffee(friend)}/></div>
    </div>
)
