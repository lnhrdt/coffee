package io.leonhardt.coffee.latte.coffee

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "coffee")
data class CoffeeEntity(
        @Id
        @Type(type = "uuid-char")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @GeneratedValue(generator = "uuid")
        var id: UUID? = null,
        @Type(type = "uuid-char")
        var friendId: UUID? = null,
        var dateTime: Instant? = null
)
