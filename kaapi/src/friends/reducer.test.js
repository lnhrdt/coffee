import reducer from './reducer'
import friendsReceived from './actions/friendsReceived'

describe('friends reducer', () => {
  it('sets up initial state', () => {
    const outputState = reducer()
    expect(outputState).toEqual({})
  })

  it('returns input state by default', () => {
    const inputState = {}
    const outputState = reducer(inputState, {type: 'foo'})
    expect(outputState).toBe(inputState)
  })

  describe('when friendsReceived is dispatched', () => {
    it('should set the friends as the value for the groupId', () => {
      const outputState = reducer({'def456': [{id: 1}]}, friendsReceived({
          friends: [{id: 2}, {id: 3}],
          groupId: 'abc123'
      }))
      expect(outputState['abc123']).toHaveLength(2)
      expect(outputState['abc123']).toContainEqual({id: 2})
      expect(outputState['abc123']).toContainEqual({id: 3})
      expect(outputState['def456']).toContainEqual({id: 1})
    })
  })
})
