jest.mock('../api/coffeeCreate')
jest.mock('./friendsLoad')

import recordCoffee from './recordCoffee'
import friendsLoad from './friendsLoad'
import coffeeCreate from '../api/coffeeCreate'

beforeEach(() => jest.resetAllMocks())

describe('recordCoffee', () => {

    it('should dispatch recordCoffee', () => {
        coffeeCreate.mockReturnValueOnce(Promise.resolve())
        friendsLoad.mockReturnValueOnce('mock friendsLoad')
        const mockDispatch = jest.fn()

        return recordCoffee({id: 'abc123'})(mockDispatch).then(() => {
            expect(coffeeCreate).toBeCalledWith({friendId: 'abc123'})
            expect(mockDispatch).toBeCalledWith('mock friendsLoad')
        })
    })
})
