import * as React from 'react'

export default (WrappedComponent) => {

    type Props = {
        submit: (value: string) => Promise<void>
    }

    type State = {
        submitting: boolean,
        value: string,
        error: string // TODO what type is error?
    }

    return class extends React.Component<Props, State> {

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
