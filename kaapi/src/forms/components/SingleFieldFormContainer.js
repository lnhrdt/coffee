import React from 'react'

export default (WrappedComponent) => {

    return class extends React.Component {

        constructor(props) {
            super(props)
            this.state = {
                submitting: false,
                value: '',
                error: undefined
            }
        }

        handleSubmit = () => {
            this.setState({submitting: true})
            return this.props.submit(this.state.value)
                .then(() => {
                    this.setState({submitting: false, value: ''})
                })
                .catch(errors => {
                    this.setState({submitting: false, error: errors && errors.name})
                })
        }

        handleValueChange = (event) => {
            this.setState({value: event.target.value, error: undefined})
        }

        render() {
            return <WrappedComponent submit={this.handleSubmit}
                                     valueChange={this.handleValueChange}
                                     value={this.state.value}
                                     submitting={this.state.submitting}
                                     error={this.state.error}
            />
        }
    }
}
