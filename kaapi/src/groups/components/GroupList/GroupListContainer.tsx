import * as React from 'react'
import {Group} from '../../types'
import {IGroupListPresenterProps} from './GroupListPresenter';

export interface IGroupListContainerProps {
    groups: Group[],
    groupsLoad: Promise<Group[]>
}

export default (WrappedComponent: React.ComponentType<IGroupListPresenterProps>) => { // TODO types?

    return class extends React.Component<IGroupListContainerProps> { // TODO

        constructor(props) {
            super(props)
            props.groupsLoad()
        }

        render() {
            const {groups} = this.props
            return <WrappedComponent groups={groups}/>
        }
    }
}
