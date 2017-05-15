import React from 'react'

export default ({friendName, friendNameChange, friendAdd, submitting}) => (
    <div>
        <input type='text' value={friendName} onChange={friendNameChange}/>
        <button onClick={friendAdd} disabled={submitting}>{submitting ? 'Submitting' : 'Add'}</button>
    </div>
)
