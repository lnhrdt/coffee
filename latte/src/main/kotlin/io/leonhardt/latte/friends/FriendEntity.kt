package io.leonhardt.latte.friends

import io.leonhardt.latte.coffee.CoffeeEntity
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "friend")
data class FriendEntity(
        @Id
        @Type(type = "uuid-char")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @GeneratedValue(generator = "uuid")
        var id: UUID? = null,
        var name: String? = null,
        @OneToMany(mappedBy = "friendId")
        var coffees: List<CoffeeEntity> = emptyList()
)
