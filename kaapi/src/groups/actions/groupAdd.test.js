jest.mock('../api/groupCreate')
jest.mock('./groupsLoad')

import groupAdd from './groupAdd'
import groupsLoad from './groupsLoad'
import groupCreate from '../api/groupCreate'

beforeEach(() => jest.resetAllMocks())

describe('groupAdd', () => {

    it('should call groupCreate and dispatch groupsLoad', () => {
        groupCreate.mockReturnValueOnce(Promise.resolve())
        groupsLoad.mockReturnValueOnce('mock groupsLoad')
        const mockDispatch = jest.fn()

        return groupAdd('Chicago')(mockDispatch).then(() => {
            expect(groupCreate).toBeCalledWith({name: 'Chicago'})
            expect(mockDispatch).toBeCalledWith('mock groupsLoad')
        })
    })
})
