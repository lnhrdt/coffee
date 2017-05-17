import fetchMock from 'fetch-mock'
import friendsGet from './friendsGet'

beforeEach(() => fetchMock.restore())

describe('friendsGet', () => {

    const friends = [
        {id: 1, name: 'Ian Ornstein'},
        {id: 2, name: 'Zachary Gershman'},
        {id: 3, name: 'John Ryan'}
    ]

    describe('when the API returns friends', () => {
        it('should fetch friends from the API', () => {
            fetchMock.mock({
                method: 'GET',
                matcher: '/api/friends',
                response: {
                    body: {data: friends},
                    status: 200
                }
            })

            return friendsGet().then(() => {
                expect(fetchMock.lastUrl()).toEqual('/api/friends')
                expect(fetchMock.lastOptions().method).toEqual('GET')
                expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            })
        })

        describe('when the responds successfully', () => {
            beforeEach(() => fetchMock.mock({
                method: 'GET',
                matcher: '/api/friends',
                response: {
                    body: {data: friends},
                    status: 200
                }
            }))

            it('should return friends from the API', () => {
                // TODO jest v20
                // return friendsGet().resolves.toEqual(friends)

                return friendsGet().then((result) => {
                    expect(result).toEqual(friends)
                })
            })
        })
    })
})
