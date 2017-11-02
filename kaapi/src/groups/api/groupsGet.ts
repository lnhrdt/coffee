import parseResponse from '../../friends/api/parseResponse'
import {Group} from '../types'

const headers = new Headers()
headers.append('Content-Type', 'application/json')

export default (): Promise<Group[]> => {
    return fetch('/api/groups', {
        method: 'GET',
        headers
    }).then((response: Response) => parseResponse<Group[]>(response)) // TODO types maybe?
}
