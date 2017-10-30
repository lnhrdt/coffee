import parseResponse from '../../friends/api/parseResponse'

export default () => {
    return fetch('/api/groups', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(parseResponse)
}
