import * as React from 'react'

import GroupListItem from './GroupListItem'
import {Group} from '../../types'

const styles = require('./GroupListPresenter.module.scss')

export interface IGroupListPresenterProps {
    groups: Group[]
}

export default ({groups}: IGroupListPresenterProps) => {
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
