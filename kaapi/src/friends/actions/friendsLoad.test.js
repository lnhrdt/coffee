jest.mock('../api/friendsGet')
jest.mock('./friendsReceived')

import friendsLoad from './friendsLoad'
import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'

beforeEach(() => jest.resetAllMocks())

describe('friendsLoad', () => {

    describe('when friendsGet returns friends', () => {
        beforeEach(() => {
            friendsGet.mockReturnValueOnce(Promise.resolve('mock friendsGet'))
            friendsReceived.mockReturnValueOnce('mock friendsReceived')
        })

        it('should dispatch friendsReceived with response from friendsGet', () => {
            const mockDispatch = jest.fn()

            return friendsLoad('abc123')(mockDispatch).then(() => {
                expect(friendsGet).toBeCalledWith('abc123')
                expect(mockDispatch).toBeCalledWith('mock friendsReceived')
                expect(friendsReceived).toBeCalledWith({friends: 'mock friendsGet', groupId: 'abc123'})
            })
        })
    })
})
