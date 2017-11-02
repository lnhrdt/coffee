const headers = new Headers()
headers.append('Content-Type', 'application/json')

export default (body) => {
    return fetch('/api/groups', {
        method: 'POST',
        headers,
        body: JSON.stringify(body)
    })
}
