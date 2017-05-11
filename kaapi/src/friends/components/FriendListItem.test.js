import React from 'react'
import {shallow} from 'enzyme'

import FriendListItem from './FriendListItem'

describe('FriendListItem', () => {

    const mockProps = {
        friend: {id: 1, name: 'John Ryan'},
        recordCoffee: jest.fn()
    }

    it('should render the friend name', () => {
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
})
