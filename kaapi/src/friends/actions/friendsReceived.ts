import {Friend, FriendActionType, FriendsReceivedAction} from '../reducer'

export default (friends: Friend[], groupId: string): FriendsReceivedAction => ({
    type: FriendActionType.FRIENDS_RECEIVED,
    data: {friends, groupId}
})
