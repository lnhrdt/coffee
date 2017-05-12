import fetchMock from 'fetch-mock'
import coffeeCreate from './coffeeCreate'

beforeEach(() => fetchMock.reset())

describe('coffeeCreate', () => {

    beforeEach(() => fetchMock.mock({
        method: 'POST',
        matcher: '/coffees',
        response: {
            status: 201
        }
    }))

    it('should POST to /coffees', () => {
        return coffeeCreate({friendId: 'abc123'}).then(() => {
            expect(fetchMock.lastUrl()).toEqual('/coffees')
            expect(fetchMock.lastOptions().method).toEqual('POST')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            const requestBody = JSON.parse(fetchMock.lastOptions().body)
            expect(requestBody.friendId).toEqual('abc123')
        })
    })
})
