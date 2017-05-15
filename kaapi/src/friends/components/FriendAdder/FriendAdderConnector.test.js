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

        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendAdderConnector)

        return subject.find(mockWrappedComponent).props().friendAdd('Will Read')
            .then(() => {
                expect(friendAdd).toHaveBeenCalledWith('Will Read')
                expect(mockStore.getActions()).toContainEqual(mockFriendsLoadAction)
            })
    })
})
