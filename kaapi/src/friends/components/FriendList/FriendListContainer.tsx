import * as React from 'react'
import {Friend} from '../../reducer'

export default (WrappedComponent) => {

    type Props = {
        friends: Friend[],
        recordCoffee: (friend: Friend) => Promise<any>, // TODO
    }
    type State = {}

    return class extends React.Component<Props, State> {

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
