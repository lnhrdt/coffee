// @flow
import {Friend} from '../types'

const compareString = (a, b) => a < b ? -1 : a > b ? 1 : 0

export default (friends: Array<Friend>) => {
    return friends
        .map(friend => {
            friend.coffees.sort((a, b) => b.dateTime - a.dateTime)
            return friend
        })
        .sort((a, b) => {
            const aDateTime = a.coffees.length ? a.coffees[0].dateTime : 0
            const bDateTime = b.coffees.length ? b.coffees[0].dateTime : 0
            return aDateTime - bDateTime || compareString(a.name, b.name)
        })
}
