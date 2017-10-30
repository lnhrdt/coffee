import parseResponse from './parseResponse'

export default (groupId) => {
    return fetch(`/api/groups/${groupId}/friends`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(parseResponse)
}
