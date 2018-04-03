package io.leonhardt.coffee.latte

import org.jetbrains.exposed.sql.Database
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class ExposedDatabaseConnector(val dataSource: DataSource) {

    @EventListener
    fun connectExposedToDatabase(event: ContextRefreshedEvent) {
        Database.connect(dataSource)
    }
}
