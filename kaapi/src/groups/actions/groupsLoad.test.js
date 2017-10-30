jest.mock('../api/groupsGet')
jest.mock('./groupsReceived')

import groupsLoad from './groupsLoad'
import groupsReceived from './groupsReceived'
import groupsGet from '../api/groupsGet'

beforeEach(() => jest.resetAllMocks())

describe('groupsLoad', () => {

    describe('when groupsGet returns groups', () => {
        beforeEach(() => {
            groupsGet.mockReturnValueOnce(Promise.resolve('mock groupsGet'))
            groupsReceived.mockReturnValueOnce('mock groupsReceived')
        })

        it('should dispatch groupsReceived with response from groupsGet', () => {
            const mockDispatch = jest.fn()

            return groupsLoad()(mockDispatch).then(() => {
                expect(mockDispatch).toBeCalledWith('mock groupsReceived')
                expect(groupsReceived).toBeCalledWith('mock groupsGet')
            })
        })
    })
})
