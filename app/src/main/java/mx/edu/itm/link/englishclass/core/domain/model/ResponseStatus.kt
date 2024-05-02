package mx.edu.itm.link.englishclass.core.domain.model

sealed class ResponseStatus<out T> {
    data class Success<out T>(val data:T) : ResponseStatus<T>()

    data class Error(val error: String?) : ResponseStatus<Nothing>()

    object Loading : ResponseStatus<Nothing>()
}