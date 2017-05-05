import {connect} from 'react-redux'
import friendsLoad from '../../actions/friendsLoad'

export default (WrappedComponent) => {

    const mapStateToProps = (state) => ({
        friends: state.friends
    })

    const mapDispatchToProps = (dispatch) => ({
        friendsLoad: () => Promise.resolve(dispatch(friendsLoad()))
    })

    return connect(mapStateToProps, mapDispatchToProps)(WrappedComponent)
}
