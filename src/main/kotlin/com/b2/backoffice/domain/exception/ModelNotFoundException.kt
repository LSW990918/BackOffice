package com.b2.backoffice.domain.exception

data class ModelNotFoundException(
    val modelName: String, val id:Int?): RuntimeException(
    "Model $modelName not found with given id: $id"
)
