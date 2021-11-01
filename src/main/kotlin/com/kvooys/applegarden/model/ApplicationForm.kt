package com.kvooys.applegarden.model

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ApplicationForm {
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var firstName: String? = null
    var lastName: String? = null

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdDate: LocalDateTime? = LocalDateTime.now()
}