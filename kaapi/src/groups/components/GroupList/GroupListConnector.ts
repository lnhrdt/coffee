import * as React from 'react'

import {connect} from 'react-redux'
import groupsLoad from '../../actions/groupsLoad'
import {Group} from '../../types'

type Props = {
    groups: Group[],
    groupsLoad: () => Promise<Group[]>
}

export default (WrappedComponent) => {

    const mapStateToProps = (state) => ({
        groups: [...state.groups].sort((a, b) => (a.name > b.name) ? 1 : 0)
    })

    const mapDispatchToProps = (dispatch) => ({
        groupsLoad: () => Promise.resolve(dispatch(groupsLoad()))
    })

    return connect(mapStateToProps, mapDispatchToProps)(WrappedComponent)
}
