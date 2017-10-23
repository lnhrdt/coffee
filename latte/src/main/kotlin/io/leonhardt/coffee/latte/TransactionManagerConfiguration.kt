package io.leonhardt.coffee.latte

import org.jetbrains.exposed.spring.SpringTransactionManager
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class TransactionManagerConfiguration {

    @Bean
    fun transactionManager(dataSource: DataSource): PlatformTransactionManager = SpringTransactionManager(dataSource)
}
