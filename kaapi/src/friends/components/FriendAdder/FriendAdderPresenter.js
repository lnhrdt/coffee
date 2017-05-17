import React from 'react'
import styles from './FriendAdderPresenter.css'
import Button from '../../../layout/Button'

export default ({friendName, friendNameChange, friendAdd, submitting}) => (
    <div className={styles.adder}>
        <input type="text"
               value={friendName}
               onChange={friendNameChange} disabled={submitting}
               className={styles.input}
        />
        <Button action="Add" clickHandler={friendAdd}/>
    </div>
)
