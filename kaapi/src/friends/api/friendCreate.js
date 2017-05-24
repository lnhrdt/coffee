// @flow

import parseResponse from './parseResponse'

type FriendCreateRequestType = {
    name: string
}

export default (body: FriendCreateRequestType) => {
    return fetch('/api/friends', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }).then(parseResponse)
}
