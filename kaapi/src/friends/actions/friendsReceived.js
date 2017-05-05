import types from './types'

export default (friends) => ({
    type: types.FRIENDS_RECEIVED,
    data: friends
})
