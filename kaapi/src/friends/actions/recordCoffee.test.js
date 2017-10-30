jest.mock('../api/coffeeCreate')
jest.mock('./friendsLoad')

import recordCoffee from './recordCoffee'
import friendsLoad from './friendsLoad'
import coffeeCreate from '../api/coffeeCreate'

beforeEach(() => jest.resetAllMocks())

describe('recordCoffee', () => {

    it('should call coffeeCreate and dispatch friendsLoad', () => {
        coffeeCreate.mockReturnValueOnce(Promise.resolve())
        friendsLoad.mockReturnValueOnce('mock friendsLoad')
        const mockDispatch = jest.fn()

        return recordCoffee({id: 'abc123', groupId: 'def456'})(mockDispatch).then(() => {
            expect(coffeeCreate).toBeCalledWith({friendId: 'abc123'})
            expect(friendsLoad).toBeCalledWith('def456')
            expect(mockDispatch).toBeCalledWith('mock friendsLoad')
        })
    })
})
