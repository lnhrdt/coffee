export type Friend = {
    id: string,
    groupId: string,
    name: string
}

export type GroupFriends = { groupId: string, friends: Friend[] }

export enum FriendActionType {
    FRIENDS_RECEIVED = 'FRIENDS_RECEIVED'
}

export interface FriendsReceivedAction {
    type: FriendActionType.FRIENDS_RECEIVED,
    data: GroupFriends
}

type FriendAction =
    | FriendsReceivedAction

export default (inputState = {}, action: FriendAction) => {
    switch (action.type) {
        case FriendActionType.FRIENDS_RECEIVED:
            const newState = {...inputState}
            newState[action.data.groupId] = [...action.data.friends]
            return newState
        default:
            return inputState
    }
}
