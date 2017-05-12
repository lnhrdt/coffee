import friendSort from './friendSort'

describe('friendSort', () => {
    const unsortedFriends = [
        {name: 'Austin Vance', coffees: [{dateTime: 200}, {dateTime: 100}, {dateTime: 300}]},
        {name: 'David Julia', coffees: [{dateTime: 50}]},
        {name: 'Luke Mueller', coffees: [{dateTime: 300}]},
        {name: 'Anthony Tarantini', coffees: [{dateTime: 300}]},
        {name: 'Utsav Sethi', coffees: []},
    ]

    it('should sort the coffees of each friend', () => {
        const result = friendSort(unsortedFriends)

        const sortedAustin = result.find(friend => friend.name === 'Austin Vance')

        expect(sortedAustin.coffees[0].dateTime).toEqual(300)
        expect(sortedAustin.coffees[1].dateTime).toEqual(200)
        expect(sortedAustin.coffees[2].dateTime).toEqual(100)
    })

    it('should sort the friends by least recent coffee and name', () => {
        const result = friendSort(unsortedFriends)

        expect(result[0].name).toEqual('Utsav Sethi')
        expect(result[1].name).toEqual('David Julia')
        expect(result[2].name).toEqual('Anthony Tarantini')
        expect(result[3].name).toEqual('Austin Vance')
        expect(result[4].name).toEqual('Luke Mueller')
    })
})
