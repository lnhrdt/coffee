import * as React from 'react'

const styles = require('./Card.module.scss')

export default ({children}) => <div className={styles.card}>{children}</div>
