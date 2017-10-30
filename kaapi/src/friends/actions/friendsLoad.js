import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'

export default (groupId) => (dispatch) => {
    return friendsGet(groupId).then(friends => dispatch(friendsReceived({friends, groupId})))
}
