import * as React from 'react'
import {Link} from 'react-router-dom'
import {Group} from '../../types'

const styles = require('./GroupListItem.module.scss')

interface GroupListPresenterProps {
    group: Group
}

const GroupListItem: React.SFC<GroupListPresenterProps> = ({group}) => (
    <Link className={styles.item} to={`/group/${group.id}`}>{group.name}</Link>
)

export default GroupListItem
