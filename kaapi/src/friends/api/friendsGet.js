import parseResponse from './parseResponse'

export default () => {
    return fetch('/api/friends', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(parseResponse)
}
