import {connect} from 'react-redux'
import groupAdd from '../../actions/groupAdd'

export default (WrappedComponent) => {

    const mapDispatchToProps = (dispatch) => ({
        groupAdd: (...args) => Promise.resolve(dispatch(groupAdd(...args)))
    })

    return connect(undefined, mapDispatchToProps)(WrappedComponent)
}
