import groupsReceived from './groupsReceived'
import types from './types'

describe('groupsReceived', () => {
    it('should build the action', () => {
        expect(groupsReceived('mock groups')).toEqual({
            type: types.GROUPS_RECEIVED,
            data: 'mock groups'
        })
    })
})
