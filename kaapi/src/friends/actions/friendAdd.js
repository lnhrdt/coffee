import friendCreate from '../api/friendCreate'
import friendsLoad from './friendsLoad'

export default (friend) => (dispatch) => {
    return friendCreate(friend).then(() => dispatch(friendsLoad(friend.groupId)))
}
