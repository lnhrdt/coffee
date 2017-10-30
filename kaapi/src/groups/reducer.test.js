import reducer from './reducer'
import groupsReceived from './actions/groupsReceived'

describe('groups reducer', () => {
  it('sets up initial state', () => {
    const outputState = reducer()
    expect(outputState).toEqual([])
  })

  it('returns input state by default', () => {
    const inputState = []
    const outputState = reducer(inputState, {type: 'foo'})
    expect(outputState).toBe(inputState)
  })

  describe('when groupsReceived is dispatched', () => {
    it('should set output state to the groups', () => {
      const outputState = reducer(['San Francisco'], groupsReceived(['New York', 'Berlin']))
      expect(outputState).not.toContainEqual('San Francisco')
      expect(outputState).toContainEqual('New York')
      expect(outputState).toContainEqual('Berlin')
    })
  })
})
