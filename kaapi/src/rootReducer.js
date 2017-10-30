import {combineReducers} from 'redux'
import friendsReducer from './friends/reducer'
import groupsReducer from './groups/reducer'

const rootReducer = combineReducers({
    friends: friendsReducer,
    groups: groupsReducer
})

export default rootReducer
