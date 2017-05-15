import React from "react"
import FriendList from "./FriendList"
import FriendAdder from "./FriendAdder"


import Card from '../../layout/Card'

export default () => (
    <Card>
        <FriendList/>
        <FriendAdder/>
    </Card>
)
