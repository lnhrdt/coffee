import groupCreate from '../api/groupCreate'
import groupsLoad from './groupsLoad'

export default (name) => (dispatch) => {
    return groupCreate({name}).then(() => dispatch(groupsLoad()))
}
