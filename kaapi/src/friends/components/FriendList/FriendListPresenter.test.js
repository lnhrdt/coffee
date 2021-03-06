import React from 'react'
import {shallow} from 'enzyme'
import FriendListPresenter from './FriendListPresenter'
import FriendListItem from '../FriendListItem'

describe('FriendListPresenter', () => {

    const mockProps = {
        friends: [
            {id: 1, name: 'Ian Ornstein'},
            {id: 2, name: 'Zachary Gershman'},
            {id: 3, name: 'John Ryan'}
        ],
        recordCoffee: jest.fn()
    }

    describe('when passed a list of friends', () => {
        it('should render each friends', () => {
            const subject = shallow(<FriendListPresenter {...mockProps}/>)

            expect(subject.find(FriendListItem)).toHaveLength(3)
            expect(subject.find(FriendListItem).at(0).props().friend).toBe(mockProps.friends[0])
            expect(subject.find(FriendListItem).at(1).props().friend).toBe(mockProps.friends[1])
            expect(subject.find(FriendListItem).at(2).props().friend).toBe(mockProps.friends[2])

            expect(subject.find(FriendListItem).at(0).props().recordCoffee).toBe(mockProps.recordCoffee)
            expect(subject.find(FriendListItem).at(1).props().recordCoffee).toBe(mockProps.recordCoffee)
            expect(subject.find(FriendListItem).at(2).props().recordCoffee).toBe(mockProps.recordCoffee)
        })
    })

    describe('when passed an empty list of friends', () => {
        it('should not render a list', () => {
            mockProps.friends = []
            const subject = shallow(<FriendListPresenter {...mockProps}/>)

            expect(subject.find('ul').exists()).toBe(false)
        })
    })
})
