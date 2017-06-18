package io.leonhardt.coffee.latte

import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

abstract class DataRepository<dataType, entityType, idType>(
        private val dataTranslator: Translator<dataType, entityType>,
        private val entityTranslator: Translator<entityType, dataType>,
        private val entityRepository: JpaRepository<entityType, idType>
) {
    open fun findOne(input: Example<dataType>): Optional<dataType> = entityRepository.findOne(Example.of(input.probe.toEntityType())).map { it.toDataType() }
    open fun findAll(): List<dataType> = entityRepository.findAll().map { it.toDataType() }

    open fun save(input: dataType): dataType = entityRepository.save(input.toEntityType()).toDataType()
    open fun saveAll(input: Iterable<dataType>): List<dataType> = entityRepository.saveAll(input.map { it.toEntityType() }).map { it.toDataType() }

    open fun deleteAll(): Unit = entityRepository.deleteAll()

    private fun dataType.toEntityType(): entityType = dataTranslator.translate(this)
    private fun entityType.toDataType(): dataType = entityTranslator.translate(this)
}
