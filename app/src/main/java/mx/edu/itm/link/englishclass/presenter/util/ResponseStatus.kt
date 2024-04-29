package mx.edu.itm.link.englishclass.presenter.util

sealed class ResponseStatus<out T> {
    class Success<out T>(val data:T) : ResponseStatus<T>()

    class Error(val error: String?) : ResponseStatus<Nothing>()

    class Loading : ResponseStatus<Nothing>()
}