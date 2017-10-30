import types from './types'

export default (groups) => ({
    type: types.GROUPS_RECEIVED,
    data: groups
})
