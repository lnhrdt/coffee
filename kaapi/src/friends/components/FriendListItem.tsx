import * as React from 'react'
import * as moment from 'moment'

const styles = require('./FriendListItem.module.css')
import Button from '../../layout/Button'
const Coffee = require('./coffee.svg')

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><Button clickHandler={() => recordCoffee(friend)}><Coffee className={styles.icon}/></Button></div>
    </div>
)
