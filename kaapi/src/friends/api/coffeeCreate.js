// @flow

type CoffeeCreateRequestType = {
    friendId: string
}

export default (body: CoffeeCreateRequestType) => {
    return fetch('/api/coffees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
}
