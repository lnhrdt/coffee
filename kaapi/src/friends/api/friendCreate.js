import parseResponse from './parseResponse'

export default (body) => {
    return fetch('/api/friends', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(parseResponse)
}
