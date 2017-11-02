type APISuccess<T> = { data: T }
type APIFailure = { errors: {} }

export default function parseResponse<T>(response: Response): Promise<T> {

    if (!response.ok) {
        return new Promise((resolve, reject) => {
            response.json().then((json: APIFailure) => reject(json.errors))
        })
    }
    return response.json().then((json: APISuccess<T>) => json.data)
}
