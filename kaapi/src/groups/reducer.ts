import {Group} from './types'

export enum GroupActionType {
    GROUPS_RECEIVED = 'GROUPS_RECEIVED'
}

interface GroupsReceivedAction {
    type: GroupActionType.GROUPS_RECEIVED
    data: Group[]
}

type GroupAction =
    | GroupsReceivedAction

export default (inputState: Group[] = [], action: GroupAction) => {
    switch (action.type) {
        case GroupActionType.GROUPS_RECEIVED:
            return [...action.data]
        default:
            return inputState
    }
}
