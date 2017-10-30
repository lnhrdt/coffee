import coffeeCreate from '../api/coffeeCreate'
import friendsLoad from './friendsLoad'

export default (friend) => (dispatch) => {
    return coffeeCreate({friendId: friend.id}).then(() => dispatch(friendsLoad(friend.groupId)))
}
