import React from 'react'
import styles from './GroupAdderPresenter.module.scss'
import Button from '../../../layout/Button'

export default ({value, valueChange, submit, submitting, error}) => (
    <div>
        <div className={styles.adder}>
            <input type="text"
                   value={value}
                   onChange={valueChange} disabled={submitting}
                   className={error ? styles.inputError : styles.input}
            />
            <Button action="Add" clickHandler={submit}/>
        </div>
        <div className={styles.error}>{error}</div>
    </div>
)
