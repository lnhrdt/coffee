import {renderDecorator} from '../../../support/testRender'
import FriendAdderContainer from './FriendAdderContainer'

beforeEach(() => jest.resetAllMocks())

describe('FriendAdderContainer', () => {

    const mockProps = {friendAdd: jest.fn()}

    describe('when component loads', () => {
        const {subject, mockWrappedComponent} = renderDecorator(FriendAdderContainer, mockProps)

        it('should pass submitting=false', () => {
            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
        })

        it('should pass friendName=""', () => {
            expect(subject.find(mockWrappedComponent).props().friendName).toEqual('')
        })

        it('should pass error=undefined', () => {
            expect(subject.find(mockWrappedComponent).props().error).toBeUndefined()
        })

        describe('when friendNameChange is called', () => {
            beforeEach(() => {
                subject.find(mockWrappedComponent).props().friendNameChange({target: {value: 'Ross Hale'}})
            })

            it('should update friendName with the valued passed', () => {
                expect(subject.find(mockWrappedComponent).props().friendName).toEqual('Ross Hale')
            })

            describe('when friendAdd is called', () => {
                it('should pass submitting=true', () => {
                    mockProps.friendAdd.mockReturnValue(Promise.resolve())
                    subject.find(mockWrappedComponent).props().friendAdd()
                    expect(subject.find(mockWrappedComponent).props().submitting).toEqual(true)
                })

                it('should call friendAdd with the value of friendName', () => {
                    mockProps.friendAdd.mockReturnValue(Promise.resolve())
                    subject.find(mockWrappedComponent).props().friendAdd()
                    expect(mockProps.friendAdd).toHaveBeenCalledWith('Ross Hale')
                })

                describe('when the friendAdd call completes successfully', () => {
                    it('should pass submitting=false', () => {
                        mockProps.friendAdd.mockReturnValue(Promise.resolve())
                        return subject.find(mockWrappedComponent).props().friendAdd().then(() => {
                            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
                        })
                    })

                    it('should pass friendName=""', () => {
                        mockProps.friendAdd.mockReturnValue(Promise.resolve())
                        return subject.find(mockWrappedComponent).props().friendAdd().then(() => {
                            expect(subject.find(mockWrappedComponent).props().friendName).toEqual('')
                        })
                    })
                })

                describe('when the friendAdd call completes unsuccessfully', () => {
                    it('should pass submitting=false', () => {
                        mockProps.friendAdd.mockReturnValue(Promise.reject({name: 'Was not friendly.'}))
                        return subject.find(mockWrappedComponent).props().friendAdd().then(() => {
                            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
                        })
                    })

                    it('should not clear friendName', () => {
                        mockProps.friendAdd.mockReturnValue(Promise.reject({name: 'Was not friendly.'}))
                        return subject.find(mockWrappedComponent).props().friendAdd().then(() => {
                            expect(subject.find(mockWrappedComponent).props().friendName).toEqual('Ross Hale')
                        })
                    })

                    it('should pass the error', () => {
                        mockProps.friendAdd.mockReturnValue(Promise.reject({name: 'Was not friendly.'}))
                        return subject.find(mockWrappedComponent).props().friendAdd().then(() => {
                            expect(subject.find(mockWrappedComponent).props().error).toEqual('Was not friendly.')
                        })
                    })

                    describe('when friendNameChange is called', () => {
                        beforeEach(() => {
                            subject.find(mockWrappedComponent).props().friendNameChange({target: {value: 'Michael Vos'}})
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
