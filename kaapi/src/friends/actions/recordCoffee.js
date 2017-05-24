// @flow
import type {Dispatch} from 'redux'
import {Friend} from '../../types'

import coffeeCreate from '../api/coffeeCreate'
import friendsLoad from './friendsLoad'

export default (friend: Friend) => (dispatch: Dispatch) => {
    return coffeeCreate({friendId: friend.id}).then(() => dispatch(friendsLoad()))
}
