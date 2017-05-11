import {renderDecorator} from '../../../support/testRender'
import FriendListContainer from './FriendListContainer'

describe('FriendListContainer', () => {

    const friends = [
        {id: 1, name: 'Ian Ornstein'},
        {id: 2, name: 'Zachary Gershman'},
        {id: 3, name: 'John Ryan'}
    ]

    const mockProps = {friends, friendsLoad: jest.fn(), recordCoffee: jest.fn()}

    it('should call friendsLoad on mount', () => {
        renderDecorator(FriendListContainer, mockProps)

        expect(mockProps.friendsLoad).toHaveBeenCalled()
    })

    it('should render WrappedComponent with friend', () => {
        const {subject, mockWrappedComponent} = renderDecorator(FriendListContainer, mockProps)

        expect(subject.find(mockWrappedComponent).props().friends).toEqual(mockProps.friends)
    })

    it('should pass recordCoffee to WrappedComponent', () => {
        const {subject, mockWrappedComponent} = renderDecorator(FriendListContainer, mockProps)

        expect(subject.find(mockWrappedComponent).props().recordCoffee).toEqual(mockProps.recordCoffee)
    })
})
