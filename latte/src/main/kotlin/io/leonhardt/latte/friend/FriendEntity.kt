package io.leonhardt.latte.friend

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class FriendEntity(
        @Id
        @Type(type = "uuid-char")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @GeneratedValue(generator = "uuid")
        var id: UUID? = null,
        var name: String? = null
)
