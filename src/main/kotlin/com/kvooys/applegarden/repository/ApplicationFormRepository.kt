package com.kvooys.applegarden.repository

import com.kvooys.applegarden.model.ApplicationForm
import org.springframework.data.repository.CrudRepository

interface ApplicationFormRepository : CrudRepository<ApplicationForm, Long> {
}