import * as React from 'react'

import SingleFieldFormContainer from '../../../forms/components/SingleFieldFormContainer'

export default (WrappedComponent) => {

    return (props) => {
        return React.createElement(SingleFieldFormContainer(WrappedComponent), {
            submit: props.friendAdd
        })
    }
}
