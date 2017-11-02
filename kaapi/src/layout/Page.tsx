import * as React from 'react'

const styles = require('./Page.module.scss')

export default ({children}) => (
    <div className={styles.page}>
        <div className={styles.center}>
            {children}
        </div>
    </div>
)
