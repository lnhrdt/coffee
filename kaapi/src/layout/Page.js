import React from 'react'

import styles from '!!style!css?modules&camelCase&importLoaders=1!postcss!./Page.css' // eslint-disable-line import/no-webpack-loader-syntax

export default ({children}) => (
    <div className={styles.page}>
        <div className={styles.center}>
            {children}
        </div>
    </div>
)
