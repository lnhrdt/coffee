import coffeeCreate from '../api/coffeeCreate'
import friendsLoad from './friendsLoad'
import {Friend} from '../reducer'

export default (friend: Friend) => (dispatch) => {
    return coffeeCreate({friendId: friend.id}).then(() => dispatch(friendsLoad(friend.groupId)))
}
