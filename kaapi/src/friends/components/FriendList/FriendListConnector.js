import {connect} from 'react-redux'
import friendsLoad from '../../actions/friendsLoad'
import recordCoffee from '../../actions/recordCoffee'

export default (WrappedComponent) => {

    const mapStateToProps = (state) => ({
        friends: state.friends
    })

    const mapDispatchToProps = (dispatch) => ({
        friendsLoad: () => Promise.resolve(dispatch(friendsLoad())),
        recordCoffee: (...args) => Promise.resolve(dispatch(recordCoffee(...args)))
    })

    return connect(mapStateToProps, mapDispatchToProps)(WrappedComponent)
}
