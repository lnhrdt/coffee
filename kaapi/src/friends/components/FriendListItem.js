import React from 'react'
import moment from 'moment'

import styles from './FriendListItem.css'
import Button from '../../layout/Button'
import CoffeeIcon from '!!../../../node_modules/babel-loader?{"presets":["react-app"]}!../../../node_modules/react-svg-loader!./coffee.svg' // eslint-disable-line import/no-webpack-loader-syntax

const coffee = <CoffeeIcon className={styles.icon}/>

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><Button action={coffee} clickHandler={() => recordCoffee(friend)}/></div>
    </div>
)
