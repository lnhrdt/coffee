jest.mock('../api/friendCreate')
jest.mock('./friendsLoad')

import friendAdd from './friendAdd'
import friendsLoad from './friendsLoad'
import friendCreate from '../api/friendCreate'

beforeEach(() => jest.resetAllMocks())

describe('friendAdd', () => {

    it('should call friendCreate and dispatch friendsLoad', () => {
        friendCreate.mockReturnValueOnce(Promise.resolve())
        friendsLoad.mockReturnValueOnce('mock friendsLoad')
        const mockDispatch = jest.fn()

        const request = {name: 'Johnathon Britz', groupId: 'abc123'}
        return friendAdd(request)(mockDispatch).then(() => {
            expect(friendCreate).toBeCalledWith(request)
            expect(friendsLoad).toBeCalledWith('abc123')
            expect(mockDispatch).toBeCalledWith('mock friendsLoad')
        })
    })
})
