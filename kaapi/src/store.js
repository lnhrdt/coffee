import {applyMiddleware, createStore} from 'redux'
import thunk from 'redux-thunk'
import {createLogger} from 'redux-logger'
import rootReducer from './rootReducer'

const middlewares = [thunk]
if (process.env.NODE_ENV === 'development') {
    const logger = createLogger()
    middlewares.push(logger)
}
const store = applyMiddleware(...middlewares)(createStore)(rootReducer)

export default store
