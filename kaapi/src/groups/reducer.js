import actionTypes from './actions/types'

export default (inputState = [], action = {}) => {
    switch (action.type) {
        case actionTypes.GROUPS_RECEIVED:
            return [...action.data]
        default:
            return inputState
    }
}
