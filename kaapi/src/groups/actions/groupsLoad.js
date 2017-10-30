import groupsReceived from './groupsReceived'
import groupsGet from '../../groups/api/groupsGet'

export default () => (dispatch) => {
    return groupsGet().then(response => dispatch(groupsReceived(response)))
}
