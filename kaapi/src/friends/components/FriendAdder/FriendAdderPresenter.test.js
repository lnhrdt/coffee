import React from 'react'
import {shallow} from 'enzyme'
import FriendAdderPresenter from './FriendAdderPresenter'

describe('FriendAdderView', () => {

    const mockProps = {
        friendAdd: jest.fn(),
        friendNameChange: jest.fn(),
        submitting: false,
        friendName: 'Arjun Shah'
    }

    describe('when component loads', () => {
        const subject = shallow(<FriendAdderPresenter {...mockProps}/>)

        it('should set the value of input to friendName', () => {
            expect(subject.find('input').props().value).toEqual('Arjun Shah')
        })

        describe('when text is entered', () => {
            beforeEach(() => subject.find('input').simulate('change', {target: {value: 'Prity Patel'}}))

            it('should trigger friendNameChange', () => {
                expect(mockProps.friendNameChange).toHaveBeenCalledWith({'target': {'value': 'Prity Patel'}})
            })
        })

        describe('when button is clicked', () => {
            beforeEach(() => subject.find('button').simulate('click'))

            it('should call friendAdd', () => {
                expect(mockProps.friendAdd).toHaveBeenCalled()
            })
        })
    })

    describe('when submitting=false', () => {
        beforeEach(() => mockProps.submitting = false)

        it('should render button enabled', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('button').prop('disabled')).toBeFalsy()
        })

        it('should render with text Add', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('button').text()).toContain('Add')
        })
    })

    describe('when submitting=true', () => {
        beforeEach(() => mockProps.submitting = true)

        it('should render button disabled', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('button').prop('disabled')).toBeTruthy()
        })

        it('should render with text Submitting', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('button').text()).toContain('Submitting')
        })
    })
})
