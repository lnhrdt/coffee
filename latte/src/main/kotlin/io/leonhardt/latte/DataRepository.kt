package io.leonhardt.latte

import org.springframework.data.domain.Example
import org.springframework.data.jpa.repository.JpaRepository

abstract class DataRepository<dataType, entityType, idType>(
        private val dataTranslator: Translator<dataType, entityType>,
        private val repository: JpaRepository<entityType, idType>,
        private val entityTranslator: Translator<entityType, dataType>
) {
    fun findOne(input: Example<dataType>): dataType = repository.findOne(Example.of(input.probe.toEntityType())).toDataType()
    fun findAll(): List<dataType> = repository.findAll().map { it.toDataType() }

    fun save(input: dataType): dataType = repository.save(input.toEntityType()).toDataType()
    fun saveAll(input: Iterable<dataType>): List<dataType> = repository.saveAll(input.map { it.toEntityType() }).map { it.toDataType() }

    fun deleteAll(): Unit = repository.deleteAll()

    private fun dataType.toEntityType(): entityType = dataTranslator.translate(this)
    private fun entityType.toDataType(): dataType = entityTranslator.translate(this)
}
