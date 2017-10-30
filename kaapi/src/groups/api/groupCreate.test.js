import fetchMock from 'fetch-mock'
import groupCreate from './groupCreate'

beforeEach(() => fetchMock.restore())

describe('groupCreate', () => {

    beforeEach(() => fetchMock.mock({
        method: 'POST',
        matcher: '/api/groups',
        response: {
            status: 201
        }
    }))

    it('should POST to /api/groups', () => {
        return groupCreate({name: 'Pivotal Austin'}).then(() => {
            expect(fetchMock.lastUrl()).toEqual('/api/groups')
            expect(fetchMock.lastOptions().method).toEqual('POST')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            const requestBody = JSON.parse(fetchMock.lastOptions().body)
            expect(requestBody.name).toEqual('Pivotal Austin')
        })
    })
})
