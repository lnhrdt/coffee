import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'
import {Friend} from '../reducer'

export default (groupId: string) => (dispatch) => {
    return friendsGet(groupId).then((friends: Friend[]) => dispatch(friendsReceived(friends, groupId)))
}
