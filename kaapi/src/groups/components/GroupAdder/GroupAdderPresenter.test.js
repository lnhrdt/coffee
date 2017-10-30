import React from 'react'
import {shallow} from 'enzyme'
import GroupAdderPresenter from './GroupAdderPresenter'
import Button from '../../../layout/Button'

describe('GroupAdderPresenter', () => {

    const mockProps = {
        submit: jest.fn(),
        valueChange: jest.fn(),
        value: 'Toronto'
    }

    it('should render a submit button', () => {
        const subject = shallow(<GroupAdderPresenter {...mockProps}/>)
        const button = subject.find(Button)

        expect(button.prop('action')).toEqual('Add')
        expect(button.prop('clickHandler')).toEqual(mockProps.submit)
    })

    describe('when component loads', () => {
        const subject = shallow(<GroupAdderPresenter {...mockProps}/>)

        it('should set the value of input to value', () => {
            expect(subject.find('input').props().value).toEqual('Toronto')
        })

        describe('when text is entered', () => {
            beforeEach(() => subject.find('input').simulate('change', {target: {value: 'Miami'}}))

            it('should trigger valueChange', () => {
                expect(mockProps.valueChange).toHaveBeenCalledWith({'target': {'value': 'Miami'}})
            })
        })
    })

    describe('when submitting=false', () => {
        beforeEach(() => mockProps.submitting = false)

        it('should render input enabled', () => {
            const subject = shallow(<GroupAdderPresenter {...mockProps}/>)
            expect(subject.find('input').prop('disabled')).toBeFalsy()
        })
    })

    describe('when submitting=true', () => {
        beforeEach(() => mockProps.submitting = true)

        it('should render input disabled', () => {
            const subject = shallow(<GroupAdderPresenter {...mockProps}/>)
            expect(subject.find('input').prop('disabled')).toBeTruthy()
        })
    })
})
