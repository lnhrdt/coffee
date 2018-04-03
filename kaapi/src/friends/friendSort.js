import moment from 'moment'

export default (friends) => {
    return friends
        .map(friend => {
            friend.coffees.sort((a, b) => moment(b.dateTime).valueOf() - moment(a.dateTime).valueOf())
            return friend
        })
        .sort((a, b) => {
            const aDateTime = a.coffees.length ? moment(a.coffees[0].dateTime).valueOf() : 0
            const bDateTime = b.coffees.length ? moment(b.coffees[0].dateTime).valueOf() : 0
            return aDateTime - bDateTime || a.name > b.name
        })
}
