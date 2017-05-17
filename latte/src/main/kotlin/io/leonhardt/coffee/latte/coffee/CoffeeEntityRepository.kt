package io.leonhardt.coffee.latte.coffee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CoffeeEntityRepository : JpaRepository<CoffeeEntity, UUID>
