import {renderDecorator} from '../../support/testRender'
import FriendAdderContainer from './SingleFieldFormContainer'

beforeEach(() => jest.resetAllMocks())

describe('SingleFieldFormContainer', () => {

    const mockProps = {submit: jest.fn()}

    describe('when component loads', () => {
        const {subject, mockWrappedComponent} = renderDecorator(FriendAdderContainer, mockProps)

        it('should pass submitting=false', () => {
            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
        })

        it('should pass value=""', () => {
            expect(subject.find(mockWrappedComponent).props().value).toEqual('')
        })

        it('should pass error=undefined', () => {
            expect(subject.find(mockWrappedComponent).props().error).toBeUndefined()
        })

        describe('when valueChange is called', () => {
            beforeEach(() => {
                subject.find(mockWrappedComponent).props().valueChange({target: {value: 'test-value-1'}})
                subject.update()
            })

            it('should update value with the valued passed', () => {
                expect(subject.find(mockWrappedComponent).props().value).toEqual('test-value-1')
            })

            describe('when submit is called', () => {
                it('should pass submitting=true', () => {
                    mockProps.submit.mockReturnValue(Promise.resolve())
                    subject.find(mockWrappedComponent).props().submit()
                    subject.update()
                    expect(subject.find(mockWrappedComponent).props().submitting).toEqual(true)
                })

                it('should call submit with the value of value', () => {
                    mockProps.submit.mockReturnValue(Promise.resolve())
                    subject.find(mockWrappedComponent).props().submit()
                    expect(mockProps.submit).toHaveBeenCalledWith('test-value-1')
                })

                describe('when the submit call completes successfully', () => {
                    it('should pass submitting=false', () => {
                        mockProps.submit.mockReturnValue(Promise.resolve())
                        return subject.find(mockWrappedComponent).props().submit().then(() => {
                            subject.update()
                            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
                        })
                    })

                    it('should pass value=""', () => {
                        mockProps.submit.mockReturnValue(Promise.resolve())
                        return subject.find(mockWrappedComponent).props().submit().then(() => {
                            subject.update()
                            expect(subject.find(mockWrappedComponent).props().value).toEqual('')
                        })
                    })
                })

                describe('when the submit call completes unsuccessfully', () => {
                    it('should pass submitting=false', () => {
                        mockProps.submit.mockReturnValue(Promise.reject({name: 'It did not work.'}))
                        return subject.find(mockWrappedComponent).props().submit().then(() => {
                            subject.update()
                            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
                        })
                    })

                    it('should not clear value', () => {
                        mockProps.submit.mockReturnValue(Promise.reject({name: 'It did not work.'}))
                        return subject.find(mockWrappedComponent).props().submit().then(() => {
                            subject.update()
                            expect(subject.find(mockWrappedComponent).props().value).toEqual('test-value-1')
                        })
                    })

                    it('should pass the error', () => {
                        mockProps.submit.mockReturnValue(Promise.reject({name: 'It did not work.'}))
                        return subject.find(mockWrappedComponent).props().submit().then(() => {
                            subject.update()
                            expect(subject.find(mockWrappedComponent).props().error).toEqual('It did not work.')
                        })
                    })

                    describe('when valueChange is called', () => {
                        beforeEach(() => {
                            subject.find(mockWrappedComponent).props().valueChange({target: {value: 'test-value-2'}})
                            subject.update()
                        })

                        it('should clear the error', () => {
                            expect(subject.find(mockWrappedComponent).props().error).toBeUndefined()
                        })
                    })
                })
            })
        })
    })
})
