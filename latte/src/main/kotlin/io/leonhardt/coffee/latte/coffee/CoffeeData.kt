package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.UUIDEntity
import io.leonhardt.coffee.latte.UUIDEntityClass
import io.leonhardt.coffee.latte.UUIDTable
import io.leonhardt.coffee.latte.friends.FriendEntity
import io.leonhardt.coffee.latte.friends.FriendTable
import org.jetbrains.exposed.dao.EntityID
import java.time.Instant
import java.util.*

data class CoffeeNew(val friendId: UUID)

data class Coffee(
    val id: UUID,
    val friendId: UUID,
    val dateTime: Instant
)

object CoffeeTable : UUIDTable("coffee") {
    val friend = reference("friend_id", FriendTable)
    val dateTime = datetime("date_time")
}

class CoffeeEntity(id: EntityID<String>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CoffeeEntity>(CoffeeTable)

    var friend by FriendEntity referencedOn CoffeeTable.friend
    var dateTime by CoffeeTable.dateTime
}

fun CoffeeEntity.toCoffee(): Coffee = Coffee(
    id = UUID.fromString(id.value),
    friendId = UUID.fromString(friend.id.value),
    dateTime = Instant.ofEpochMilli(dateTime.millis)
)
