package com.example.model.data.dto

import com.google.gson.annotations.SerializedName

class SearchResultDto(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<MeaningsDto>?
)