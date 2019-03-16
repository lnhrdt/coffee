import React from 'react'
import styles from './Button.module.scss'
import {ReactComponent as Loader} from './loader.svg'

export default class extends React.Component {

    constructor(props) {
        super(props)
        this.state = {submitting: false}
    }

    clickHandler = () => {
        const clickResult = this.props.clickHandler()
        if (clickResult instanceof Promise) {
            this.setState({submitting: true})
            clickResult.then(() => this.setState({submitting: false}))
        }
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
