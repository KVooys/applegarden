package com.kvooys.applegarden.controller

import com.kvooys.applegarden.model.ApplicationForm
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
@WebFluxTest(controllers = [ApplicationFormController::class])
@Import(ThymeleafAutoConfiguration::class)
internal class ApplicationFormControllerTest {

    private val client = WebTestClient
        .bindToController(ApplicationFormController())
        .build()

    @Test
    fun home() {
        client.get().uri("/")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun getForm() {
        client.get().uri("/application_form")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun formSubmit() {
        val applicationForm = ApplicationForm()
        val now = LocalDateTime.now()
        applicationForm.id = 123L
        applicationForm.firstName = "Art"
        applicationForm.lastName = "the Bard"
        applicationForm.createdDate = now

        client.post()
            .uri("/application_form")
            .body(BodyInserters.fromObject(applicationForm))
            .exchange()
            .expectStatus().isOk();
    }
}