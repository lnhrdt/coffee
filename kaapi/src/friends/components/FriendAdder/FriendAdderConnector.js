import {connect} from 'react-redux'
import friendAdd from '../../actions/friendAdd'

export default (WrappedComponent) => {

    const mapDispatchToProps = (dispatch, ownProps) => {
        const groupId = ownProps.match.params.groupId
        return {
            friendAdd: (name) => Promise.resolve(dispatch(friendAdd({name, groupId})))
        }
    }

    return connect(undefined, mapDispatchToProps)(WrappedComponent)
}
