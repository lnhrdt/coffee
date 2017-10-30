import friendsReceived from './friendsReceived'
import types from './types'

describe('friendsReceived', () => {
    it('should build the action', () => {
        expect(friendsReceived({friends: 'mock friends', groupId: 'abc123'})).toEqual({
            type: types.FRIENDS_RECEIVED,
            data: {friends: 'mock friends', groupId: 'abc123'}
        })
    })
})
