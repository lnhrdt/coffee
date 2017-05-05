import React from 'react'
import {Provider} from 'react-redux'
import store from './store'
import FriendListPage from './friends/components/FriendListPage'

export default () => <Provider store={store}><FriendListPage/></Provider>
