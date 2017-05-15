import friendCreate from '../api/friendCreate'
import friendsLoad from './friendsLoad'

export default (name) => (dispatch) => {
    return friendCreate({name}).then(() => dispatch(friendsLoad()))
}
