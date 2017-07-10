import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.css'
import CoffeeIcon from './coffee.svg'

import Button from '../../layout/Button'

const coffee = <CoffeeIcon className={styles.icon}/>

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><Button action={coffee} clickHandler={() => recordCoffee(friend)}/></div>
    </div>
)
