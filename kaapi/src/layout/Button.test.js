import React from 'react'
import {shallow} from 'enzyme'
import Button from './Button'

describe('Button', () => {
    let mockProps

    beforeEach(() => {
        mockProps = {
            action: 'Do It',
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
                const subject = shallow(<Button {...mockProps}/>)
                expect(subject.find('button').text()).toContain(mockProps.action)
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
                // TODO: once we can import SVG as a component with svg-react-loader
                // https://github.com/kitze/custom-react-scripts/issues/59
                // expect(subject.find('button').find(svg).exists()).toBe(true)
            })

            describe('when the Promise resolves', () => {
                it('should render action', () => {
                    const subject = shallow(<Button {...mockProps}/>)
                    subject.find('button').simulate('click')
                    clickHandlerResolve()

                    return clickHandlerPromise.then(() => {
                        subject.update()
                        expect(subject.find('button').text()).toContain(mockProps.action)
                    })
                })
            })
        })
    })
})
