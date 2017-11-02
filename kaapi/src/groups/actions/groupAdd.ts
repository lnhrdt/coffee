import groupCreate from '../api/groupCreate'
import groupsLoad from './groupsLoad'

export default (name: string) => (dispatch) => {
    return groupCreate({name}).then(() => dispatch(groupsLoad()))
}
