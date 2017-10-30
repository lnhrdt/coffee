import React from 'react'
import {Link} from 'react-router-dom'
import styles from './GroupListItem.module.scss'

export default ({group}) => (
    <Link className={styles.item} to={`/group/${group.id}`}>{group.name}</Link>
)
