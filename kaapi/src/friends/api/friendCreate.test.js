import fetchMock from 'fetch-mock'
import friendCreate from './friendCreate'

beforeEach(() => fetchMock.reset())

describe('friendCreate', () => {

    beforeEach(() => fetchMock.mock({
        method: 'POST',
        matcher: '/api/friends',
        response: {
            status: 201
        }
    }))

    it('should POST to /api/friends', () => {
        return friendCreate({name: 'Michael McCormick'}).then(() => {
            expect(fetchMock.lastUrl()).toEqual('/api/friends')
            expect(fetchMock.lastOptions().method).toEqual('POST')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            const requestBody = JSON.parse(fetchMock.lastOptions().body)
            expect(requestBody.name).toEqual('Michael McCormick')
        })
    })
})
