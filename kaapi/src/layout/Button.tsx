import * as React from 'react'
const styles = require('./Button.module.scss')
const Loader = require('./loader.svg')

export default class extends React.Component<any, any> { // TODO

    constructor(props) {
        super(props)
        this.state = {submitting: false}
    }

    clickHandler = () => {
        // TODO I took out conditional checking for Promise, check tests and stuff
        this.setState({submitting: true})
        this.props.clickHandler().then(() => this.setState({submitting: false}))
    }

    render() {
        return (
            <button disabled={this.state.submitting}
                    onClick={this.clickHandler}
                    className={styles.button}
            >{this.state.submitting ? <Loader className={styles.loader}/> : this.props.children}</button>
        )
    }
}
