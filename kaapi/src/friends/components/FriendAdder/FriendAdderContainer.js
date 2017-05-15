import React from 'react'

export default (WrappedComponent) => {

    return class extends React.Component {

        constructor(props) {
            super(props)
            this.state = {
                submitting: false,
                friendName: ''
            }

            this.handleFriendAdd = this.handleFriendAdd.bind(this)
            this.handleFriendNameChange = this.handleFriendNameChange.bind(this)
        }

        handleFriendAdd() {
            this.setState({submitting: true})
            return this.props.friendAdd(this.state.friendName)
                .then(() => {
                    this.setState({submitting: false});
                    this.setState({friendName: ''});
                })
        }

        handleFriendNameChange(event) {
            this.setState({friendName: event.target.value})
        }

        render() {
            return <WrappedComponent friendAdd={this.handleFriendAdd}
                                     friendNameChange={this.handleFriendNameChange}
                                     submitting={this.state.submitting}
                                     friendName={this.state.friendName}
            />
        }
    }
}
