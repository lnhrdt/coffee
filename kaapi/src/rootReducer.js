import {combineReducers} from 'redux'
import friendsReducer from './friends/reducer'

const rootReducer = combineReducers({
    friends: friendsReducer
})

export default rootReducer
