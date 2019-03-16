import React from 'react'
import {shallow} from 'enzyme'
import moment from 'moment'
import FriendListItem from './FriendListItem'
import Button from '../../layout/Button'

describe('FriendListItem', () => {

    const mockProps = {
        friend: {id: 1, name: 'John Ryan', coffees: []},
        recordCoffee: jest.fn()
    }

    it('should render the friends name', () => {
        const subject = shallow(<FriendListItem {...mockProps}/>)

        expect(subject).toIncludeText(mockProps.friend.name)
    })

    describe('rendering the submit button', () => {
        it('should pass a clickHandler that calls recordCoffee with friend', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(mockProps.recordCoffee).not.toHaveBeenCalled()
            subject.find(Button).prop('clickHandler')()
            expect(mockProps.recordCoffee).toHaveBeenCalledWith(mockProps.friend)
        })
    })

    describe('when the friend has no coffees', () => {
        it('should render text to indicate the friend has no coffees', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(subject).toIncludeText('never')
        })
    })

    describe('when the friend has coffees', () => {
        beforeEach(() => mockProps.friend.coffees = [{dateTime: moment().subtract(9, 'days').format('YYYY-MM-DDTHH:mm:ss.SSSZ')}])

        it('should render how long since the last coffee', () => {
            const subject = shallow(<FriendListItem {...mockProps}/>)

            expect(subject).toIncludeText('9 days ago')
        })
    })
})
