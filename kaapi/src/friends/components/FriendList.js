import React from 'react'

import FriendListView from './FriendListView'

const friends = [
    {id: 1, name: 'Ian Ornstein'},
    {id: 2, name: 'Zachary Gershman'},
    {id: 3, name: 'John Ryan'}
]

export default () => <FriendListView friends={friends}/>
