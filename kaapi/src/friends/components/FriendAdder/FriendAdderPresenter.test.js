import React from 'react'
import {shallow} from 'enzyme'
import FriendAdderPresenter from './FriendAdderPresenter'
import Button from '../../../layout/Button'

describe('FriendAdderPresenter', () => {

    const mockProps = {
        submit: jest.fn(),
        valueChange: jest.fn(),
        value: 'Arjun Shah'
    }

    it('should render a submit button', () => {
        const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
        const button = subject.find(Button)

        expect(button.children()).toHaveText('Add')
        expect(button.prop('clickHandler')).toEqual(mockProps.submit)
    })

    describe('when component loads', () => {
        const subject = shallow(<FriendAdderPresenter {...mockProps}/>)

        it('should set the value of input to value', () => {
            expect(subject.find('input').props().value).toEqual('Arjun Shah')
        })

        describe('when text is entered', () => {
            beforeEach(() => subject.find('input').simulate('change', {target: {value: 'Prity Patel'}}))

            it('should trigger valueChange', () => {
                expect(mockProps.valueChange).toHaveBeenCalledWith({'target': {'value': 'Prity Patel'}})
            })
        })
    })

    describe('when submitting=false', () => {
        beforeEach(() => mockProps.submitting = false)

        it('should render input enabled', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('input').prop('disabled')).toBeFalsy()
        })
    })

    describe('when submitting=true', () => {
        beforeEach(() => mockProps.submitting = true)

        it('should render input disabled', () => {
            const subject = shallow(<FriendAdderPresenter {...mockProps}/>)
            expect(subject.find('input').prop('disabled')).toBeTruthy()
        })
    })
})
