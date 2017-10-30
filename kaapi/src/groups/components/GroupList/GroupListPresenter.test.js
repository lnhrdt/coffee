import React from 'react'
import {shallow} from 'enzyme'
import GroupListPresenter from './GroupListPresenter'
import GroupListItem from './GroupListItem'

describe('GroupListPresenter', () => {

    const mockProps = {
        groups: [
            {id: '1', name: 'Paris'},
            {id: '2', name: 'London'},
            {id: '3', name: 'Munich'}
        ]
    }

    describe('when passed a list of groups', () => {
        it('should render each group', () => {
            const subject = shallow(<GroupListPresenter {...mockProps}/>)

            expect(subject.find(GroupListItem)).toHaveLength(3)
            expect(subject.find(GroupListItem).at(0).props().group).toBe(mockProps.groups[0])
            expect(subject.find(GroupListItem).at(1).props().group).toBe(mockProps.groups[1])
            expect(subject.find(GroupListItem).at(2).props().group).toBe(mockProps.groups[2])
        })
    })

    describe('when passed an empty list of groups', () => {
        it('should not render a list', () => {
            mockProps.groups = []
            const subject = shallow(<GroupListPresenter {...mockProps}/>)

            expect(subject.find('ul').exists()).toBe(false)
        })
    })
})
