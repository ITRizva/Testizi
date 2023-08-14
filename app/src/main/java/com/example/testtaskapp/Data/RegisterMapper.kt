package com.example.testtaskapp.Data

import com.example.testtaskapp.Data.localModels.LocalEntity
import com.example.testtaskapp.Domain.domainModels.DirectEntity

fun LocalEntity.toDirectEntity(): DirectEntity {
    return DirectEntity(
        grz = this.grz,
        sts = this.sts,
        vu = this.vu
    )
}
fun DirectEntity.toLocalEntity():LocalEntity{
    return LocalEntity(
        grz = this.grz,
        sts = this.sts,
        vu = this.vu
    )
}