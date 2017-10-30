jest.mock('../../actions/friendAdd')
jest.mock('../../actions/recordCoffee')
jest.mock('../../friendSort')

import {renderDecorator} from '../../../support/testRender'
import FriendAdderConnector from './FriendAdderConnector'
import friendAdd from '../../actions/friendAdd'

beforeEach(() => jest.resetAllMocks())

describe('FriendAdderConnector', () => {

    it('should provide a function that dispatches friendAdd', () => {
        const mockFriendsLoadAction = {type: 'mock friendAdd'}
        friendAdd.mockReturnValueOnce(mockFriendsLoadAction)

        const mockProps = {match: {params: {groupId: 'abc123'}}}
        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendAdderConnector, mockProps)

        return subject.find(mockWrappedComponent).props().friendAdd('Will Read')
            .then(() => {
                expect(friendAdd).toHaveBeenCalledWith({name: 'Will Read', groupId: 'abc123'})
                expect(mockStore.getActions()).toContainEqual(mockFriendsLoadAction)
            })
    })
})
