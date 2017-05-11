import reducer from './reducer'
import friendsReceived from './actions/friendsReceived'

describe('friend reducer', () => {
  it('sets up initial state', () => {
    const outputState = reducer()
    expect(outputState).toEqual([])
  })

  it('returns input state by default', () => {
    const inputState = []
    const outputState = reducer(inputState, {type: 'foo'})
    expect(outputState).toBe(inputState)
  })

  describe('when friendsReceived is dispatched', () => {
    it('should set output state to the friend', () => {
      const outputState = reducer([{id: 1}], friendsReceived([{id: 2}, {id: 3}]))
      expect(outputState).not.toContainEqual({id: 1})
      expect(outputState).toContainEqual({id: 2})
      expect(outputState).toContainEqual({id: 3})
    })
  })
})
