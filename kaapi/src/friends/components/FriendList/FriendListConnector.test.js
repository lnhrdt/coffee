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
        const mockProps = {match: {params: {groupId: 'abc123'}}}
        const mockState = {friends: {'abc123': 'unsorted friends'}}
        const {subject, mockWrappedComponent} = renderDecorator(FriendListConnector, mockProps, mockState)

        expect(friendSort).toBeCalledWith('unsorted friends')
        expect(subject.find(mockWrappedComponent).props().friends).toEqual('sorted friends')
    })

    it('should provide an empty array of friends when state does not contain group', () => {
        const mockProps = {match: {params: {groupId: 'abc123'}}}
        const mockState = {friends: {}}
        const {subject, mockWrappedComponent} = renderDecorator(FriendListConnector, mockProps, mockState)

        expect(subject.find(mockWrappedComponent).props().friends).toEqual([])
    })

    it('should provide a function that dispatches friendsLoad', () => {
        const mockFriendsLoadAction = {type: 'mock friendsLoad'}
        friendsLoad.mockReturnValueOnce(mockFriendsLoadAction)

        const mockProps = {match: {params: {groupId: 'abc123'}}}
        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendListConnector, mockProps)

        return subject.find(mockWrappedComponent).props().friendsLoad()
            .then(() => {
                expect(friendsLoad).toHaveBeenCalledWith('abc123')
                expect(mockStore.getActions()).toContainEqual(mockFriendsLoadAction)
            })
    })

    it('should provide a function that dispatches recordCoffee', () => {
        const mockRecordCoffeeAction = {type: 'mock recordCoffee'}
        recordCoffee.mockReturnValueOnce(mockRecordCoffeeAction)

        const mockProps = {match: {params: {groupId: 'abc123'}}}
        const {subject, mockWrappedComponent, mockStore} = renderDecorator(FriendListConnector, mockProps)

        return subject.find(mockWrappedComponent).props().recordCoffee('mock args')
            .then(() => {
                expect(recordCoffee).toHaveBeenCalledWith('mock args')
                expect(mockStore.getActions()).toContainEqual(mockRecordCoffeeAction)
            })
    })
})
