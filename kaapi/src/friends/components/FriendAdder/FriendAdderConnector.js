import {connect} from 'react-redux'
import friendAdd from '../../actions/friendAdd'

export default (WrappedComponent) => {

    const mapDispatchToProps = (dispatch) => ({
        friendAdd: (...args) => Promise.resolve(dispatch(friendAdd(...args)))
    })

    return connect(undefined, mapDispatchToProps)(WrappedComponent)
}
