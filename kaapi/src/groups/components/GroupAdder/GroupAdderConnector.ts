import {connect} from 'react-redux'
import groupAdd from '../../actions/groupAdd'

export default (WrappedComponent) => {

    const mapDispatchToProps = (dispatch) => ({
        groupAdd: (name: string) => Promise.resolve(dispatch(groupAdd(name)))
    })

    return connect(undefined, mapDispatchToProps)(WrappedComponent)
}
