import parseResponse from './parseResponse'
import {Friend} from '../reducer'

const headers = new Headers()
headers.append('Content-Type', 'application/json')

export default (groupId) => {
    return fetch(`/api/groups/${groupId}/friends`, {
        method: 'GET',
        headers
    }).then(response => parseResponse<Friend[]>(response))
}
