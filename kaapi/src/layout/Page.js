import React from 'react'

import styles from './Page.css'

export default ({children}) => (
    <div className={styles.page}>
        <div className={styles.center}>
            {children}
        </div>
    </div>
)
