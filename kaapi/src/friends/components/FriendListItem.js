import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.module.css'
import Button from '../../layout/Button'
import Coffee from './coffee.svg'

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'YYYY-MM-DDTHH:mm:ss.SSSZ').fromNow() : 'never'}</div>
        <div className={styles.action}><Button clickHandler={() => recordCoffee(friend)}><Coffee className={styles.icon}/></Button></div>
    </div>
)
