package com.kvooys.applegarden.repository

import com.kvooys.applegarden.model.ApplicationForm
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.orm.jpa.JpaSystemException
import java.time.Instant
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
        applicationForm.id = 123L
        applicationForm.createdDate = Instant.ofEpochSecond(123456)
        applicationFormRepository.save(applicationForm)
        val retrievedForm: Optional<ApplicationForm> = applicationFormRepository.findById(123L)
        assertTrue(retrievedForm.get().id == 123L)
        assertTrue(retrievedForm.get().createdDate == Instant.ofEpochSecond(123456))

    }
}