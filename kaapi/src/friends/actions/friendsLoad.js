import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'

export default () => (dispatch) => {
    return friendsGet().then(response => dispatch(friendsReceived(response)))
}
