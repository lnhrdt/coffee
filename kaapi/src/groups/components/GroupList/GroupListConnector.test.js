jest.mock('../../actions/groupsLoad')

import {renderDecorator} from '../../../support/testRender'
import GroupListConnector from './GroupListConnector'
import groupsLoad from '../../actions/groupsLoad'

describe('GroupListConnector', () => {

    it('should provide sorted groups from state', () => {
        const mockState = {
            groups: [
                {id: '1', name: 'Santa Monica'},
                {id: '2', name: 'Austin'},
                {id: '3', name: 'Chicago'}
            ]
        }

        const sortedGroups = [
            {id: '2', name: 'Austin'},
            {id: '3', name: 'Chicago'},
            {id: '1', name: 'Santa Monica'}
        ]

        const {subject, mockWrappedComponent} = renderDecorator(GroupListConnector, undefined, mockState)
        expect(subject.find(mockWrappedComponent).props().groups).toEqual(sortedGroups)
    })

    it('should provide a function that dispatches groupsLoad', () => {
        const mockState = {groups: []}
        const mockGroupsLoadAction = {type: 'mock groupsLoad'}
        groupsLoad.mockReturnValueOnce(mockGroupsLoadAction)

        const {subject, mockWrappedComponent, mockStore} = renderDecorator(GroupListConnector, undefined, mockState)

        return subject.find(mockWrappedComponent).props().groupsLoad()
            .then(() => {
                expect(groupsLoad).toHaveBeenCalled()
                expect(mockStore.getActions()).toContainEqual(mockGroupsLoadAction)
            })
    })
});
