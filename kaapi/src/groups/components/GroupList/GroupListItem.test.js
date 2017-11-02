import * as React from 'react'
import {Link} from 'react-router-dom'
import {shallow} from 'enzyme'
import GroupListItem from './GroupListItem'

describe('GroupListItem', () => {

    const mockProps = {group: {id: 'abc123', name: 'Seattle'}}

    it('should render the group name', () => {
        const subject = shallow(<GroupListItem {...mockProps}/>)

        expect(subject.find(Link).children()).toHaveText('Seattle')
    })

    it('should link to the group', () => {
        const subject = shallow(<GroupListItem {...mockProps}/>)

        expect(subject.find(Link).props().to).toEqual('/group/abc123')
    })
})
