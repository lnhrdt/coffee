import {renderDecorator} from '../../../support/testRender'
import GroupListContainer from './GroupListContainer'

describe('GroupListContainer', () => {

    const groups = 'mock-groups'

    const mockProps = {groups, groupsLoad: jest.fn()}

    it('should call groupsLoad on mount', () => {
        renderDecorator(GroupListContainer, mockProps)

        expect(mockProps.groupsLoad).toHaveBeenCalled()
    })

    it('should render WrappedComponent with groups', () => {
        const {subject, mockWrappedComponent} = renderDecorator(GroupListContainer, mockProps)

        expect(subject.find(mockWrappedComponent).props().groups).toEqual(mockProps.groups)
    })
})
