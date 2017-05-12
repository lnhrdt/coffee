jest.mock('../../actions/friendsLoad')
jest.mock('../../actions/recordCoffee')
jest.mock('../../friendSort')

import {renderDecorator} from '../../../support/testRender'
import FriendListConnector from './FriendListConnector'
import friendsLoad from '../../actions/friendsLoad'
import recordCoffee from '../../actions/recordCoffee'
import friendSort from '../../friendSort'

beforeEach(() => jest.resetAllMocks())

describe('FriendListConnector', () => {

    it('should provide sorted friends from state', () => {
        friendSort.mockReturnValueOnce('sorted friends')
        const mockState = {friends: 'unsorted friends'}
        const {subject, mockWrappedComponent} = renderDecorator(FriendListConnector, undefined, mockState)

        expect(friendSort).toBeCalledWith('unsorted friends')
        expect(subject.find(mockWrappedComponent).props().friends).toEqual('sorted friends')
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
