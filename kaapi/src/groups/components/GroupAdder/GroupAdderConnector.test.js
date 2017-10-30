jest.mock('../../actions/groupAdd')

import {renderDecorator} from '../../../support/testRender'
import GroupAdderConnector from './GroupAdderConnector'
import groupAdd from '../../actions/groupAdd'

beforeEach(() => jest.resetAllMocks())

describe('GroupAdderConnector', () => {

    it('should provide a function that dispatches groupAdd', () => {
        const mockGroupsLoadAction = {type: 'mock groupAdd'}
        groupAdd.mockReturnValueOnce(mockGroupsLoadAction)

        const {subject, mockWrappedComponent, mockStore} = renderDecorator(GroupAdderConnector)

        return subject.find(mockWrappedComponent).props().groupAdd('Will Read')
            .then(() => {
                expect(groupAdd).toHaveBeenCalledWith('Will Read')
                expect(mockStore.getActions()).toContainEqual(mockGroupsLoadAction)
            })
    })
})
