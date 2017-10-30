import React from 'react'

export default (WrappedComponent) => {

    return class extends React.Component {

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
