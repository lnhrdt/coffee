jest.mock('../../actions/friendsLoad')

import {renderDecorator} from '../../../support/testRender'
import FriendListConnector from './FriendListConnector'
import friendsLoad from '../../actions/friendsLoad'

beforeEach(() => jest.resetAllMocks())

describe('FriendListConnector', () => {

    it('should provide friends from state', () => {
        const friends = [{id: 1, name: 'Rob Mee'}]
        const mockState = {friends}
        const {subject, mockWrappedComponent} = renderDecorator(FriendListConnector, undefined, mockState)

        expect(subject.find(mockWrappedComponent).props().friends).toEqual(friends)
    })

    it('should provide a function that dispatches friendsLoad', () => {
        const mockFriendsLoadAction = {type: 'mock friendsLoad'}
        friendsLoad.mockReturnValueOnce(mockFriendsLoadAction)

        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendListConnector)

        return subject.find(mockWrappedComponent).props().friendsLoad()
            .then(() => {
                expect(friendsLoad).toHaveBeenCalled()
                expect(mockStore.getActions()).toContainEqual(mockFriendsLoadAction)
            })
    })
})
