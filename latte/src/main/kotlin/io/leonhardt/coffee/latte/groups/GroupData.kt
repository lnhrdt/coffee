package io.leonhardt.coffee.latte.groups

import io.leonhardt.coffee.latte.UUIDEntity
import io.leonhardt.coffee.latte.UUIDEntityClass
import io.leonhardt.coffee.latte.UUIDTable
import io.leonhardt.coffee.latte.friends.Friend
import io.leonhardt.coffee.latte.friends.FriendEntity
import io.leonhardt.coffee.latte.friends.FriendTable
import io.leonhardt.coffee.latte.friends.toFriend
import org.jetbrains.exposed.dao.EntityID
import java.util.*

data class GroupNew(val name: String)

data class Group(
        val id: UUID,
        val name: String,
        val friends: List<Friend>
)

object GroupTable : UUIDTable("group_table") {
    val name = varchar("name", 255)
}

class GroupEntity(id: EntityID<String>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<GroupEntity>(GroupTable)

    var name by GroupTable.name
    val friends by FriendEntity referrersOn FriendTable.group
}

fun GroupEntity.toGroup(): Group {
    return Group(
            id = UUID.fromString(id.value),
            name = name,
            friends = friends.map { it.toFriend() }
    )
}
