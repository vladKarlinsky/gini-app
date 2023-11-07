package com.example.gini.data.model

data class ResponseModel(
    val total: Int,
    val totalHits: Int,
    val hits: List<Image>
)
