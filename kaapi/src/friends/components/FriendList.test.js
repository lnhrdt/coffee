import React from 'react'
import {shallow} from 'enzyme'

import FriendList from './FriendList'
import FriendListView from './FriendListView'

describe('FriendList', () => {

    it('provides a list of friends to the view', () => {
        const subject = shallow(<FriendList/>)

        expect(subject.find(FriendListView).props().friends).toHaveLength(3)
    })
})
