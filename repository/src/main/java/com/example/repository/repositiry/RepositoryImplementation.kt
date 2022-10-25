package com.example.repository.repositiry

import com.example.model.data.dto.SearchResultDto
import com.example.repository.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource<List<SearchResultDto>>) :
    Repository<List<SearchResultDto>> {
    /** Репозиторий возвращает данные, используя dataSource (локальный или внешний)*/
    override suspend fun getData(word: String): List<SearchResultDto> {
        return dataSource.getData(word)
    }
}