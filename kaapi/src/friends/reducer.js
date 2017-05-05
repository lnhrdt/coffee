import actionTypes from './actions/types'

export default (inputState = [], action = {}) => {
    switch (action.type) {
        case actionTypes.FRIENDS_RECEIVED:
            return [...action.data]
        default:
            return inputState
    }
}
