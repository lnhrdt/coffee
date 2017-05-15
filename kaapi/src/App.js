import React from 'react'
import {Provider} from 'react-redux'
import store from './store'
import Page from './layout/Page'
import FriendListPage from './friends/components/FriendListPage'

export default () => (
    <Provider store={store}>
        <Page><FriendListPage/></Page>
    </Provider>
)
