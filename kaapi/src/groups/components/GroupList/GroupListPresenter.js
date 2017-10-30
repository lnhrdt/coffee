import React from 'react'

import GroupListItem from './GroupListItem'
import styles from './GroupListPresenter.module.scss'

export default ({groups}) => {
    return groups.length ? (
        <ul className={styles.list}>
            {groups.map((group, index) => (
                <li key={index} className={styles.item}>
                    <GroupListItem group={group}/>
                </li>
            ))}
        </ul>
    ) : null
}
