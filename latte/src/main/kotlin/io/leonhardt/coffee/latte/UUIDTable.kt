package io.leonhardt.coffee.latte

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IdTable
import org.jetbrains.exposed.sql.Column
import java.util.*

abstract class UUIDEntity(id: EntityID<String>) : Entity<String>(id)


abstract class UUIDEntityClass<out EntityType : UUIDEntity>(
    table: IdTable<String>,
    entityType: Class<EntityType>? = null
) : EntityClass<String, EntityType>(table, entityType)

open class UUIDTable(name: String = "", columnName: String = "id") : IdTable<String>(name) {
    override val id: Column<EntityID<String>> = varchar(columnName, 36)
        .clientDefault { UUID.randomUUID().toString().toUpperCase() }
        .primaryKey()
        .entityId()
}
