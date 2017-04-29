import React from 'react'
import {shallow} from 'enzyme'

import FriendListItem from './FriendListItem'

describe('FriendListItem', () => {

    const friend = {id: 1, name: 'John Ryan'}

    it('should render the friend name', () => {
        const subject = shallow(<FriendListItem friend={friend}/>)

        expect(subject.text()).toContain(friend.name)
    })
})
