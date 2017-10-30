jest.mock('../../../forms/components/SingleFieldFormContainer')

import {renderDecorator} from '../../../support/testRender'
import FriendAdderContainer from './FriendAdderContainer'
import SingleFieldFormContainer from '../../../forms/components/SingleFieldFormContainer'

beforeEach(() => jest.resetAllMocks())

describe('FriendAdderContainer', () => {

    const mockProps = {friendAdd: jest.fn()}

    it('should map friendAdd to submit', () => {
        const {subject} = renderDecorator(FriendAdderContainer, mockProps)
        expect(subject.props().submit).toBe(mockProps.friendAdd)
    })

    it('should pass the WrappedComponent to SingleFieldFormContainer', () => {
        const {mockWrappedComponent} = renderDecorator(FriendAdderContainer, mockProps)
        expect(SingleFieldFormContainer).toBeCalledWith(mockWrappedComponent)
    })
})
