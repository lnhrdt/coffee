import * as React from 'react'
import GroupAdder from './GroupAdder/index'
import GroupList from './GroupList/index'

import Card from '../../layout/Card'

export default () => ( // todo types?
    <Card>
        <GroupAdder/>
        <GroupList/>
    </Card>
)
