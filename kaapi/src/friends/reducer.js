// @flow
import {Friend} from '../types'

import actionTypes from './actions/types'

export default (inputState: Array<Friend> = [], action: Object = {}) => {
    switch (action.type) {
        case actionTypes.FRIENDS_RECEIVED:
            return [...action.data]
        default:
            return inputState
    }
}
