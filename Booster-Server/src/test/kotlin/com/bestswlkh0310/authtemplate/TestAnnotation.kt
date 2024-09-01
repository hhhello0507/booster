package com.bestswlkh0310.authtemplate

import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.TestPropertySource


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // reset DB
@AutoConfigureTestDatabase(
    connection = EmbeddedDatabaseConnection.H2,
    replace = AutoConfigureTestDatabase.Replace.ANY
)
@TestPropertySource("classpath:application-test.yml")
@AutoConfigureMockMvc
annotation class TestAnnotation