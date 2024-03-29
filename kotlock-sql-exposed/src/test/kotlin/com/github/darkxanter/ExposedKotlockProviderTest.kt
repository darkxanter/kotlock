package com.github.darkxanter

import com.gitlab.darkxanter.tests.KotlockProviderTest
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
class ExposedKotlockProviderTest : KotlockProviderTest() {
    @Container
    val container = PostgreSQLContainer(DockerImageName.parse("postgres:13-alpine")).apply {
        withDatabaseName("test")
        withUsername("postgres")
        withPassword("postgres")
        withReuse(true)
    }

    override val kotlockProvider = ExposedKotlockProvider()
    private var db: Database? = null
    @BeforeEach
    fun connect() {
        Database.connect("jdbc:postgresql://localhost:5432/test", user = "postgres", password = "postgres")
        transaction {
            SchemaUtils.drop(kotlockProvider.table)
            SchemaUtils.createMissingTablesAndColumns(kotlockProvider.table)
        }
    }

    @AfterEach
    fun disconnect() {
        db?.let {
            TransactionManager.closeAndUnregister(it)
        }
    }
}