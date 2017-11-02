import {connect} from 'react-redux'
import friendsLoad from '../../actions/friendsLoad'
import recordCoffee from '../../actions/recordCoffee'
import friendSort from '../../friendSort'
import {Friend} from '../../reducer'

export default (WrappedComponent) => {

    const mapStateToProps = (state, ownProps) => {
        const groupId = ownProps.match.params.groupId
        const unsortedFriends = state.friends && state.friends[groupId]
        const friends = (unsortedFriends && friendSort(unsortedFriends)) || []
        return {friends}
    }

    const mapDispatchToProps = (dispatch, ownProps) => {
        const groupId = ownProps.match.params.groupId
        return {
            friendsLoad: () => groupId && Promise.resolve(dispatch(friendsLoad(groupId))),
            recordCoffee: (friend: Friend) => Promise.resolve(dispatch(recordCoffee(friend)))
        }
    }

    return connect(mapStateToProps, mapDispatchToProps)(WrappedComponent)
}
