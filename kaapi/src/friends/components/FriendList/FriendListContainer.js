import React from 'react'

export default (WrappedComponent) => {

    return class extends React.Component {

        constructor(props) {
            super(props)
            props.friendsLoad()
        }

        render() {
            return <WrappedComponent friends={this.props.friends}
                                     recordCoffee={this.props.recordCoffee} />
        }
    }
}
