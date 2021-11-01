package com.kvooys.applegarden.repository

import com.kvooys.applegarden.model.ApplicationForm
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.orm.jpa.JpaSystemException
import java.time.LocalDateTime
import java.util.*

@DataJpaTest
class ApplicationFormRepositoryTest @Autowired constructor(
        val applicationFormRepository: ApplicationFormRepository
) {
    @Test
    fun shouldNotSaveApplicationFormWithoutId() {
        val applicationForm = ApplicationForm()
        assertThrows<JpaSystemException> {
            applicationFormRepository.save(applicationForm)
        }
    }

    @Test
    fun shouldSaveAndRetrieveValidApplicationForm() {
        val applicationForm = ApplicationForm()
        val now: LocalDateTime = LocalDateTime.now()
        applicationForm.id = 123L
        applicationForm.firstName = "Art"
        applicationForm.lastName = "the Bard"
        applicationForm.createdDate = now
        applicationFormRepository.save(applicationForm)
        val retrievedForm: Optional<ApplicationForm> = applicationFormRepository.findById(123L)
        assertTrue(retrievedForm.get().id == 123L)
        assertTrue(retrievedForm.get().firstName == "Art")
        assertTrue(retrievedForm.get().lastName == "the Bard")
        assertTrue(retrievedForm.get().createdDate == now)
    }
}