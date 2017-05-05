export default () => {
    return fetch('/friends')
        .then(response => response.json())
}
