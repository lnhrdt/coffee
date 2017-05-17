export default (response) => {

    if (!response.ok) {
        return new Promise((resolve, reject) => {
            response.json().then(json => reject(json.errors))
        })
    }
    return response.json().then(json => json.data)
}
