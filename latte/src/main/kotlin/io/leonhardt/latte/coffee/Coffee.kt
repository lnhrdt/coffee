package io.leonhardt.latte.coffee

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.GeneratedValue
import javax.persistence.Id

class Coffee(
        @Id
        @Type(type = "uuid-char")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @GeneratedValue(generator = "uuid")
        var id: UUID? = null
)
