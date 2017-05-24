// @flow
import {Friend} from '../../types'

import types from './types'

type FriendsReceivedAction = {
    type: string,
    data: Array<Friend>
}

export default (friends: Array<Friend>): FriendsReceivedAction => ({
    type: types.FRIENDS_RECEIVED,
    data: friends
})
