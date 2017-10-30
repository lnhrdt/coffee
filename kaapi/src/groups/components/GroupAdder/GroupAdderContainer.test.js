jest.mock('../../../forms/components/SingleFieldFormContainer')

import {renderDecorator} from '../../../support/testRender'
import GroupAdderContainer from './GroupAdderContainer'
import SingleFieldFormContainer from '../../../forms/components/SingleFieldFormContainer'

beforeEach(() => jest.resetAllMocks())

describe('GroupAdderContainer', () => {

    const mockProps = {groupAdd: jest.fn()}

    it('should map groupAdd to submit', () => {
        const {subject} = renderDecorator(GroupAdderContainer, mockProps)
        expect(subject.props().submit).toBe(mockProps.groupAdd)
    })

    it('should pass the WrappedComponent to SingleFieldFormContainer', () => {
        const {mockWrappedComponent} = renderDecorator(GroupAdderContainer, mockProps)
        expect(SingleFieldFormContainer).toBeCalledWith(mockWrappedComponent)
    })
})
