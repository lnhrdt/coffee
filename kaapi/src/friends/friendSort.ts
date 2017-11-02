export default (friends) => {
    return friends
        .map(friend => {
            friend.coffees.sort((a, b) => b.dateTime - a.dateTime)
            return friend
        })
        .sort((a, b) => {
            const aDateTime = a.coffees.length ? a.coffees[0].dateTime : 0
            const bDateTime = b.coffees.length ? b.coffees[0].dateTime : 0
            return aDateTime - bDateTime || a.name > b.name // TODO return types are different
        })
}
