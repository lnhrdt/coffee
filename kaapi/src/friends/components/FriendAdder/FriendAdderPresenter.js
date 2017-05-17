import React from 'react'
import styles from './FriendAdderPresenter.css'

export default ({friendName, friendNameChange, friendAdd, submitting}) => (
    <div className={styles.adder}>
        <input className={styles.input} type="text" value={friendName} onChange={friendNameChange}/>
        <button className={styles.button} onClick={friendAdd} disabled={submitting}>{submitting ? 'Submitting' : 'Add'}</button>
    </div>
)
