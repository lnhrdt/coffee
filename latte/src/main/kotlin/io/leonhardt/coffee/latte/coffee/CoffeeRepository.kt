package io.leonhardt.coffee.latte.coffee

import io.leonhardt.coffee.latte.DataRepository
import io.leonhardt.coffee.latte.Translator
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CoffeeRepository(
        dataTranslator: Translator<Coffee, CoffeeEntity>,
        entityTranslator: Translator<CoffeeEntity, Coffee>,
        entityRepository: JpaRepository<CoffeeEntity, UUID>
) : DataRepository<Coffee, CoffeeEntity, UUID>(
        dataTranslator = dataTranslator,
        entityTranslator = entityTranslator,
        entityRepository = entityRepository
)
