package com.kvooys.applegarden.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.kvooys.applegarden.model.ApplicationForm
import org.slf4j.LoggerFactory
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@RestController
class ApplicationFormController {

    private val logger = LoggerFactory.getLogger(ApplicationFormController::class.java)

    @GetMapping("/")
    fun home(): String {
        return "redirect:/application_form"
    }

    @GetMapping("/application_form")
    fun getForm(model: Model): String {
        model.addAttribute("application_form", ApplicationForm())
        return "application_form"
    }

    @PostMapping("/application_form", produces =["application/json; encoding=UTf-8"])
    @ResponseBody
    fun formSubmit(@ModelAttribute("application_form") applicationForm: ApplicationForm, model: Model): String {
        model.addAttribute("application_form", applicationForm)
        logger.info(
            String.format(
                "Application submission successful: id = %s, firstName = %s, lastName = %s, createdDate = %s",
                applicationForm.id, applicationForm.firstName, applicationForm.lastName, applicationForm.createdDate
            )
        )
        val mapper = ObjectMapper()
        mapper.registerModule(JavaTimeModule())
        return mapper.writeValueAsString(applicationForm)
    }
}