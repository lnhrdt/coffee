import {Group} from '../types'
import {GroupActionType} from '../reducer'

export default (groups: Group[]) => ({
    type: GroupActionType.GROUPS_RECEIVED,
    data: groups
})
