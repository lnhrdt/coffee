// @flow
import type {Dispatch} from 'redux'

import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'

export default () => (dispatch: Dispatch) => {
    return friendsGet().then(response => dispatch(friendsReceived(response)))
}
