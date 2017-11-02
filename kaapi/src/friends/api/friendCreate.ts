import parseResponse from './parseResponse'
import {Friend} from '../reducer'

const headers = new Headers()
headers.append('Content-Type', 'application/json')

export default (body) => {
    return fetch('/api/friends', {
        method: 'POST',
        headers,
        body: JSON.stringify(body)
    }).then((response: Response) => parseResponse<Friend>(response)) // TODO generic type?
}
