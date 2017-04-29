import React from 'react'
import {shallow} from 'enzyme'

import FriendListView from './FriendListView'
import FriendListItem from './FriendListItem'

describe('FriendListView', () => {

    const friends = [
        {id: 1, name: 'John Ryan'},
        {id: 2, name: 'Zach Gershman'},
        {id: 3, name: 'Ian'}
    ]

    describe('when passed a list of friends', () => {
        it('should render each friend', () => {
            const subject = shallow(<FriendListView friends={friends}/>)

            expect(subject.find(FriendListItem)).toHaveLength(3)
            expect(subject.find(FriendListItem).at(0).props().friend).toBe(friends[0])
            expect(subject.find(FriendListItem).at(1).props().friend).toBe(friends[1])
            expect(subject.find(FriendListItem).at(2).props().friend).toBe(friends[2])
        })
    })
})
