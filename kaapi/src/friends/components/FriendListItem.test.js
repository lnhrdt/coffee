import React from 'react'
import {shallow} from 'enzyme'
import moment from 'moment'

import FriendListItem from './FriendListItem'

describe('FriendListItem', () => {

    const mockProps = {
        friend: {id: 1, name: 'John Ryan', coffees: []},
        recordCoffee: jest.fn()
    }

    it('should render the friends name', () => {
        const subject = shallow(<FriendListItem {...mockProps}/>)

        expect(subject.text()).toContain(mockProps.friend.name)
    })

    describe('when the button is clicked', () => {
        it('should call recordCoffee', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(mockProps.recordCoffee).not.toHaveBeenCalled()
            subject.find('button').simulate('click')
            expect(mockProps.recordCoffee).toHaveBeenCalledWith(mockProps.friend)
        })
    })

    describe('when the friend has no coffees', () => {
        it('should render text to indicate the friend has no coffees', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(subject.text()).toContain('never')
        })
    })

    describe('when the friend has coffees', () => {
        beforeEach(() => mockProps.friend.coffees = [{dateTime: moment().subtract(9, 'days').unix()}])

        it('should render how long since the last coffee', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(subject.text()).toContain('9 days ago')
        })
    })
})
