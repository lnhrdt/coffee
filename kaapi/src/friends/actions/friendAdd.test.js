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

        return friendAdd('Johnathon Britz')(mockDispatch).then(() => {
            expect(friendCreate).toBeCalledWith({name: 'Johnathon Britz'})
            expect(mockDispatch).toBeCalledWith('mock friendsLoad')
        })
    })
})
