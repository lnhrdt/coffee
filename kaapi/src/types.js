export type Friend = {
    id: string,
    name: string,
    coffees: Array<Coffee>
}

export type Coffee = {
    id: string,
    friendId: string,
    dateTime: number
}
