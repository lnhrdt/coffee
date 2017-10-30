import actionTypes from './actions/types'

export default (inputState = {}, action = {}) => {
    switch (action.type) {
        case actionTypes.FRIENDS_RECEIVED:
            const newState = {...inputState}
            newState[action.data.groupId] = [...action.data.friends]
            return newState
        default:
            return inputState
    }
}
