import * as React from 'react'
import {shallow} from 'enzyme'
import Button from './Button'

describe('Button', () => {
    let mockProps

    beforeEach(() => {
        mockProps = {
            clickHandler: jest.fn()
        }
    })

    describe('when clickHandler does not return a Promise', () => {
        beforeEach(() => {
            mockProps.clickHandler.mockReturnValue(undefined)
        })

        describe('when the button is clicked', () => {
            it('should call clickHandler', () => {
                const subject = shallow(<Button {...mockProps}/>)
                subject.find('button').simulate('click')
                expect(mockProps.clickHandler).toHaveBeenCalled()
            })

            it('should render action', () => {
                const subject = shallow(<Button {...mockProps}>Do It</Button>)
                expect(subject.find('button')).toHaveText('Do It')
            })
        })
    })

    describe('when clickHandler returns a Promise', () => {
        let clickHandlerPromise, clickHandlerResolve
        beforeEach(() => {
            clickHandlerPromise = new Promise((resolve) => clickHandlerResolve = resolve)
            mockProps.clickHandler.mockReturnValue(clickHandlerPromise)
        })

        describe('when the button is clicked', () => {
            it('should call clickHandler', () => {
                const subject = shallow(<Button {...mockProps}/>)
                subject.find('button').simulate('click')
                expect(mockProps.clickHandler).toHaveBeenCalled()
            })

            it('should render a spinner', () => {
                const subject = shallow(<Button {...mockProps}/>)
                subject.find('button').simulate('click')
                expect(subject.find('button').childAt(0)).toHaveTagName('loader.svg')
            })

            describe('when the Promise resolves', () => {
                it('should render action', () => {
                    const subject = shallow(<Button {...mockProps}>Do It</Button>)
                    subject.find('button').simulate('click')
                    clickHandlerResolve()

                    return clickHandlerPromise.then(() => {
                        subject.update()
                        expect(subject.find('button')).toHaveText('Do It')
                    })
                })
            })
        })
    })
})
