jest.mock('../api/friendsGet')
jest.mock('./friendsReceived')

import friendsLoad from './friendsLoad'
import friendsReceived from './friendsReceived'
import friendsGet from '../api/friendsGet'

beforeEach(() => jest.resetAllMocks())

describe('friendsLoad', () => {

    describe('when friendsGet returns friend', () => {
        beforeEach(() => {
            friendsGet.mockReturnValueOnce(Promise.resolve('mock friendsGet'))
            friendsReceived.mockReturnValueOnce('mock friendsReceived')
        })

        it('should dispatch friendsReceived with response from friendsGet', () => {
            const mockDispatch = jest.fn()

            return friendsLoad()(mockDispatch).then(() => {
                expect(mockDispatch).toBeCalledWith('mock friendsReceived')
                expect(friendsReceived).toBeCalledWith('mock friendsGet')
            })
        })
    })
})
