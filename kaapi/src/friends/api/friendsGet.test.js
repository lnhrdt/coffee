import fetchMock from 'fetch-mock'
import friendsGet from './friendsGet'

beforeEach(() => fetchMock.restore())

describe('friendsGet', () => {

    const friends = [
        {id: 1, name: 'Ian Ornstein'},
        {id: 2, name: 'Zachary Gershman'},
        {id: 3, name: 'John Ryan'}
    ]

    it('should fetch friends from the API', () => {
        fetchMock.mock({
            method: 'GET',
            matcher: '/api/groups/abc123/friends',
            response: {
                body: {data: friends},
                status: 200
            }
        })

        return friendsGet('abc123').then(() => {
            expect(fetchMock.lastUrl()).toEqual('/api/groups/abc123/friends')
            expect(fetchMock.lastOptions().method).toEqual('GET')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
        })
    })

    describe('when the API responds successfully', () => {
        beforeEach(() => fetchMock.mock({
            method: 'GET',
            matcher: '/api/groups/abc123/friends',
            response: {
                body: {data: friends},
                status: 200
            }
        }))

        it('should return friends from the API', () => {
            return expect(friendsGet('abc123')).resolves.toEqual(friends)
        })
    })
})
