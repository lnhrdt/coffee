package io.leonhardt.coffee.latte

import io.leonhardt.coffee.latte.groups.GroupTable
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@ActiveProfiles("test")
abstract class DatabaseTest {

    @BeforeEach
    @AfterEach
    fun cleanup() {
        transaction { GroupTable.deleteAll() }
    }
}
