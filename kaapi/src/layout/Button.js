import React from 'react'
import styles from './Button.css'

const loader = (
    <svg className={styles.loader} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 100 100">
        <path d="M50 5c2.2 0 3.9 2.5 3.9 5.6v11.2c0 3.1-1.8 5.6-3.9 5.6-2.2 0-3.9-2.5-3.9-5.6V10.6C46.1 7.5 47.8 5 50 5zm31.8 13.2c1.5 1.5 1 4.6-1.2 6.8l-8 8c-2.2 2.2-5.2 2.7-6.8 1.2-1.5-1.5-1-4.6 1.2-6.8l8-8c2.3-2.2 5.3-2.8 6.8-1.2zM95 50c0 2.2-2.5 3.9-5.6 3.9H78.1c-3.1 0-5.6-1.8-5.6-3.9 0-2.2 2.5-3.9 5.6-3.9h11.2c3.2 0 5.7 1.7 5.7 3.9zM81.8 81.8c-1.5 1.5-4.6 1-6.8-1.2l-8-8c-2.2-2.2-2.7-5.2-1.2-6.8 1.5-1.5 4.6-1 6.8 1.2l8 8c2.2 2.3 2.8 5.3 1.2 6.8zM50 95c-2.2 0-3.9-2.5-3.9-5.6V78.1c0-3.1 1.8-5.6 3.9-5.6 2.2 0 3.9 2.5 3.9 5.6v11.2c0 3.2-1.7 5.7-3.9 5.7zM18.2 81.8c-1.5-1.5-1-4.6 1.2-6.8l8-8c2.2-2.2 5.2-2.7 6.8-1.2 1.5 1.5 1 4.6-1.2 6.8l-8 8c-2.3 2.2-5.3 2.8-6.8 1.2zM5 50c0-2.2 2.5-3.9 5.6-3.9h11.2c3.1 0 5.6 1.8 5.6 3.9 0 2.2-2.5 3.9-5.6 3.9H10.6C7.5 53.9 5 52.2 5 50zm13.2-31.8c1.5-1.5 4.6-1 6.8 1.2l8 8c2.2 2.2 2.7 5.2 1.2 6.8-1.5 1.5-4.6 1-6.8-1.2l-8-8c-2.2-2.3-2.8-5.3-1.2-6.8z"/>
    </svg>
)

export default class extends React.Component {

    constructor(props) {
        super(props)
        this.state = {submitting: false}
        this.clickHandler = this.clickHandler.bind(this)
    }

    clickHandler() {
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
            >{this.state.submitting ? loader : this.props.action}</button>
        )
    }
}
