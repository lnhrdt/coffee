// @flow
import {Friend} from '../../../types'

import React from 'react'

import FriendListItem from '../FriendListItem'
import styles from './FriendListPresenter.css'

export default ({friends, recordCoffee}: {friends: Array<Friend>, recordCoffee: Function}) => {
    return friends.length ? (
        <ul className={styles.list}>
            {friends.map((friend, index) => (
                <li key={index} className={styles.item}>
                    <FriendListItem friend={friend} recordCoffee={recordCoffee}/>
                </li>
            ))}
        </ul>
    ) : null
}
