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
        beforeEach(() => fetchMock.getOnce('/friends', friends))

        it('should return friends from the API', () => {
            return friendsGet().then((result) => {
                expect(result).toEqual(friends)
            })
        })
    })
})
