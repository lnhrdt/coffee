import * as React from 'react'
import {shallow} from 'enzyme'
import configureStore from 'redux-mock-store'

export function renderDecorator(decorator, mockProps, mockState) {
    const mockStore = configureStore()(mockState)
    const mockWrappedComponent = jest.fn()
    const subject = shallow(React.createElement(decorator(mockWrappedComponent), {...mockProps, store: mockStore}))
    return {subject, mockWrappedComponent, mockStore}
}
