import {connect} from 'react-redux'
import groupsLoad from '../../actions/groupsLoad'

export default (WrappedComponent) => {

    const mapStateToProps = (state) => ({
        groups: [...state.groups].sort((a, b) => (a.name > b.name))
    })

    const mapDispatchToProps = (dispatch) => ({
        groupsLoad: () => Promise.resolve(dispatch(groupsLoad()))
    })

    return connect(mapStateToProps, mapDispatchToProps)(WrappedComponent)
}
