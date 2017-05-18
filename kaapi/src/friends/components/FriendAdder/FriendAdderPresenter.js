import React from 'react'
import styles from './FriendAdderPresenter.css'
import Button from '../../../layout/Button'

export default ({friendName, friendNameChange, friendAdd, submitting, error}) => (
    <div>
        <div className={styles.adder}>
            <input type="text"
                   value={friendName}
                   onChange={friendNameChange} disabled={submitting}
                   className={error ? styles.inputError : styles.input}
            />
            <Button action="Add" clickHandler={friendAdd}/>
        </div>
        <div className={styles.error}>{error}</div>
    </div>
)