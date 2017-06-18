import React from 'react'
import moment from 'moment'

import styles from '!!style!css?modules&camelCase&importLoaders=1!postcss!./FriendListItem.css' // eslint-disable-line import/no-webpack-loader-syntax
import CoffeeIcon from '!!babel?{"presets":["react-app"]}!react-svg!./coffee.svg' // eslint-disable-line import/no-webpack-loader-syntax

import Button from '../../layout/Button'

const coffee = <CoffeeIcon className={styles.icon}/>

export default ({friend, recordCoffee}) => (
    <div className={styles.friend}>
        <div className={styles.name}>{friend.name}</div>
        <div className={styles.lastCoffee}>{friend.coffees.length ? moment(friend.coffees[0].dateTime, 'X').fromNow() : 'never'}</div>
        <div className={styles.action}><Button action={coffee} clickHandler={() => recordCoffee(friend)}/></div>
    </div>
)
