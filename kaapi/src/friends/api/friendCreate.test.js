import fetchMock from 'fetch-mock'
import friendCreate from './friendCreate'

beforeEach(() => fetchMock.restore())

describe('friendCreate', () => {

    it('should POST to /api/friends', () => {
        fetchMock.mock({
            method: 'POST',
            matcher: '/api/friends',
            response: {
                body: {},
                status: 201
            }
        })

        return friendCreate({name: 'Michael McCormick'}).then(() => {
            expect(fetchMock.lastUrl()).toEqual('/api/friends')
            expect(fetchMock.lastOptions().method).toEqual('POST')
            expect(fetchMock.lastOptions().headers).toEqual({'Content-Type': 'application/json'})
            const requestBody = JSON.parse(fetchMock.lastOptions().body)
            expect(requestBody.name).toEqual('Michael McCormick')
        })
    })

    describe('when the responds unsuccessfully', () => {
        const errors = {'coffee': 'Too cold.'}

        it('should reject the Promise with errors from the API', () => {
            fetchMock.mock({
                method: 'POST',
                matcher: '/api/friends',
                response: {
                    body: {errors: errors},
                    status: 400
                }
            })

            // TODO jest v20
            // return friendCreate(...).rejects.toEqual(errors)

            return friendCreate({name: 'enemy'})
                .then(() => expect(true).toBe(false))
                .catch((result) => {
                    expect(result).toEqual(errors)
                })
        })
    })
})
