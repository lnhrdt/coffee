import types from './types'

export default ({friends, groupId}) => ({
    type: types.FRIENDS_RECEIVED,
    data: {friends, groupId}
})
