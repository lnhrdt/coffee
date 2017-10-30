import fetchMock from 'fetch-mock'
import groupsGet from './groupsGet'

beforeEach(() => fetchMock.restore())

describe('groupsGet', () => {

    const groups = ['Santa Monica', 'Austin', 'Chicago']

    it('should fetch groups from the API', () => {
        fetchMock.mock({
            method: 'GET',
            matcher: '/api/groups',
            response: {
                body: {data: groups},
                status: 200
            }
        })

        return groupsGet().then(() => {
            expect(fetchMock.lastUrl()).toEqual('/api/groups')
            expect(fetchMock.lastOptions().method).toEqual('GET')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
        })
    })

    describe('when the API responds successfully', () => {
        beforeEach(() => fetchMock.mock({
            method: 'GET',
            matcher: '/api/groups',
            response: {
                body: {data: groups},
                status: 200
            }
        }))

        it('should return groups from the API', () => {
            return expect(groupsGet()).resolves.toEqual(groups)
        })
    })
})
