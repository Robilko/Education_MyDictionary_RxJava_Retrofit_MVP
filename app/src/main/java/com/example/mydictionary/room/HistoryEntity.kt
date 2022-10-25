package com.example.mydictionary.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**Так как мы пишем на Kotlin, то достаточно написать поля в конструкторе
 * класса. В качестве основной ячейки мы используем ячейку "word", то есть
 * слово, которое мы искали и хотим сохранить. unique = true означает, что
 * в БД не будут сохраняться повторяющиеся слова.
 */
@Entity (indices = [Index(value = arrayOf("word"), unique = true)])
class HistoryEntity(
    /** Далее всё стандартно для любой БД. Мы храним слово и его перевод
     * (перевод не сохраняется в текущем приложении, но вы можете
     * имплементировать дополнительный функционал самостоятельно)
     */
    @field:PrimaryKey
    @field:ColumnInfo(name = "word")
    var word: String,
    @field:ColumnInfo(name = "description")
    var description: String?,
    @field:ColumnInfo(name = "imageUrl")
    var imageUrl: String?,
    @field:ColumnInfo(name = "transcription")
    var transcription: String?
)