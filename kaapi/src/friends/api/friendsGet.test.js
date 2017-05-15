import fetchMock from 'fetch-mock'
import friendsGet from './friendsGet'

beforeEach(() => fetchMock.reset())

describe('friendsGet', () => {

    const friends = [
        {id: 1, name: 'Ian Ornstein'},
        {id: 2, name: 'Zachary Gershman'},
        {id: 3, name: 'John Ryan'}
    ]

    describe('when the API returns friends', () => {
        beforeEach(() => fetchMock.mock({
            method: 'GET',
            matcher: '/api/friends',
            response: {
                body: friends,
                status: 200
            }
        }))

        it('should fetch friends from the API', () => {
            return friendsGet().then(() => {
                expect(fetchMock.lastUrl()).toEqual('/api/friends')
                expect(fetchMock.lastOptions().method).toEqual('GET')
                expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            })
        })

        it('should return friends from the API', () => {
            return friendsGet().then((result) => {
                expect(result).toEqual(friends)
            })
        })
    })
})
