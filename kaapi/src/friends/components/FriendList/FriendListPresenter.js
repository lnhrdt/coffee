import React from 'react'

import FriendListItem from '../FriendListItem'
import styles from '!!style!css?modules&camelCase&importLoaders=1!postcss!./FriendListPresenter.css' // eslint-disable-line import/no-webpack-loader-syntax

export default ({friends, recordCoffee}) => {
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
