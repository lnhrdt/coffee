jest.mock('../../actions/friendsLoad')
jest.mock('../../actions/recordCoffee')

import {renderDecorator} from '../../../support/testRender'
import FriendListConnector from './FriendListConnector'
import friendsLoad from '../../actions/friendsLoad'
import recordCoffee from '../../actions/recordCoffee'

beforeEach(() => jest.resetAllMocks())

describe('FriendListConnector', () => {

    it('should provide friend from state', () => {
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

    it('should provide a function that dispatches recordCoffee', () => {
        const mockRecordCoffeeAction = {type: 'mock recordCoffee'}
        recordCoffee.mockReturnValueOnce(mockRecordCoffeeAction)

        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendListConnector)

        return subject.find(mockWrappedComponent).props().recordCoffee('mock args')
            .then(() => {
                expect(recordCoffee).toHaveBeenCalledWith('mock args')
                expect(mockStore.getActions()).toContainEqual(mockRecordCoffeeAction)
            })
    })
})
