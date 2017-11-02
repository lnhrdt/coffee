import * as React from 'react'
import FriendList from './FriendList'
import FriendAdder from './FriendAdder'
import Card from '../../layout/Card'

export default (props) => (
    <Card>
        <FriendList {...props}/>
        <FriendAdder {...props}/>
    </Card>
)
