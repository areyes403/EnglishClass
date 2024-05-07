package mx.edu.itm.link.englishclass.call_feature.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.englishclass.call_feature.domain.model.Call
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus

interface CallRepository {
    suspend fun getCallHistory(idUser:String):Flow<ResponseStatus<List<Call>>>
}