package com.kvooys.applegarden.model

import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ApplicationForm {
    @Id
    @Column(name = "id", nullable = false)
    var id: Long? = null

    var createdDate: Instant = Instant.now()
}