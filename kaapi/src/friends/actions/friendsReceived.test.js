import friendsReceived from './friendsReceived'
import types from './types'

describe('friendsReceived', () => {
    it('should build the action', () => {
        expect(friendsReceived('mock friend')).toEqual({
            type: types.FRIENDS_RECEIVED,
            data: 'mock friend'
        })
    })
})
