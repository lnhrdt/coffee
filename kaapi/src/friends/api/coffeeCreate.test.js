import fetchMock from 'fetch-mock'
import coffeeCreate from './coffeeCreate'

beforeEach(() => fetchMock.reset())

describe('coffeeCreate', () => {

    beforeEach(() => fetchMock.mock({
        method: 'POST',
        matcher: '/coffee',
        response: {
            status: 201
        }
    }))

    it('should POST to /coffee', () => {
        return coffeeCreate({friendId: 'abc123'}).then(() => {
            expect(fetchMock.lastUrl()).toEqual('/coffee')
            expect(fetchMock.lastOptions().method).toEqual('POST')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            const requestBody = JSON.parse(fetchMock.lastOptions().body)
            expect(requestBody.friendId).toEqual('abc123')
        })
    })
})
