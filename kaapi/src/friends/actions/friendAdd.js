// @flow
import type {Dispatch} from 'redux'

import friendCreate from '../api/friendCreate'
import friendsLoad from './friendsLoad'

export default (name: string) => (dispatch: Dispatch): Promise<> => {
    return friendCreate({name}).then(() => dispatch(friendsLoad()))
}
