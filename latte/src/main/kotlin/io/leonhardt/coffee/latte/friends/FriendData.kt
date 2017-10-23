package io.leonhardt.coffee.latte.friends

import io.leonhardt.coffee.latte.UUIDEntity
import io.leonhardt.coffee.latte.UUIDEntityClass
import io.leonhardt.coffee.latte.UUIDTable
import io.leonhardt.coffee.latte.coffee.Coffee
import io.leonhardt.coffee.latte.coffee.CoffeeEntity
import io.leonhardt.coffee.latte.coffee.CoffeeTable
import io.leonhardt.coffee.latte.coffee.toCoffee
import org.jetbrains.exposed.dao.EntityID
import java.util.*

data class FriendNew(val name: String)

data class Friend(
        val id: UUID,
        val name: String,
        val coffees: List<Coffee>
)

object FriendTable : UUIDTable("friend") {
    val name = varchar("name", 255)
}

class FriendEntity(id: EntityID<String>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<FriendEntity>(FriendTable)

    var name by FriendTable.name
    val coffees by CoffeeEntity referrersOn CoffeeTable.friend
}

fun FriendEntity.toFriend(): Friend {
    return Friend(
            id = UUID.fromString(id.value),
            name = name,
            coffees = coffees.map { it.toCoffee() }
    )
}
