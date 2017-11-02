import * as React from 'react'
import {BrowserRouter as Router, Route} from 'react-router-dom'
import {Provider} from 'react-redux'
import store from './store'
import Page from './layout/Page'
import FriendListPage from './friends/components/FriendListPage'
import GroupChoosePage from './groups/components/GroupChoosePage'

export default () => (
    <Provider store={store}>
        <Router>
            <Page>
                <Route exact path="/" component={GroupChoosePage}/>
                <Route path="/group/:groupId" component={FriendListPage}/>
            </Page>
        </Router>
    </Provider>
)
