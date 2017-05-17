import React from 'react'

export default (WrappedComponent) => {

    return class extends React.Component {

        constructor(props) {
            super(props)
            this.state = {
                submitting: false,
                friendName: '',
                error: undefined
            }

            this.handleFriendAdd = this.handleFriendAdd.bind(this)
            this.handleFriendNameChange = this.handleFriendNameChange.bind(this)
        }

        handleFriendAdd() {
            this.setState({submitting: true})
            return this.props.friendAdd(this.state.friendName)
                .then(() => {
                    this.setState({submitting: false, friendName: ''})
                })
                .catch(errors => {
                    this.setState({submitting: false, error: errors.name})
                })
        }

        handleFriendNameChange(event) {
            this.setState({friendName: event.target.value, error: undefined})
        }

        render() {
            return <WrappedComponent friendAdd={this.handleFriendAdd}
                                     friendNameChange={this.handleFriendNameChange}
                                     friendName={this.state.friendName}
                                     submitting={this.state.submitting}
                                     error={this.state.error}
            />
        }
    }
}
