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

        it('should pass friendName=', () => {
            expect(subject.find(mockWrappedComponent).props().friendName).toEqual('')
        })

        describe('when friendNameChange is called', () => {
            beforeEach(() => {
                subject.find(mockWrappedComponent).props().friendNameChange({target: {value: 'Ross Hale'}})
            })

            it('should update friendName with the valued passed', () => {
                expect(subject.find(mockWrappedComponent).props().friendName).toEqual('Ross Hale')
            })

            describe('when friendAdd is called', () => {
                let friendAddResolve
                const friendAddPromise = new Promise((resolve) => friendAddResolve = resolve)

                beforeEach(() => {
                    mockProps.friendAdd.mockReturnValueOnce(friendAddPromise)
                    subject.find(mockWrappedComponent).props().friendAdd()
                })

                it('should pass submitting=true', () => {
                    expect(subject.find(mockWrappedComponent).props().submitting).toEqual(true)
                })

                it('should call friendAdd with the value of friendName', () => {
                    expect(mockProps.friendAdd).toHaveBeenCalledWith('Ross Hale')
                })

                describe('when the friendAdd call completes', () => {
                    beforeEach(() => {
                        friendAddResolve()
                    })

                    it('should pass submitting=false', () => {
                        return friendAddPromise.then(() => {
                            expect(subject.find(mockWrappedComponent).props().submitting).toEqual(false)
                        })
                    })

                    it('should pass friendName=', () => {
                        return friendAddPromise.then(() => {
                            expect(subject.find(mockWrappedComponent).props().friendName).toEqual('')
                        })
                    })
                })
            })
        })
    })
})
