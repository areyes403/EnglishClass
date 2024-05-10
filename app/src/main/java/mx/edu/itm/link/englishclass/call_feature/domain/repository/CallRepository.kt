package mx.edu.itm.link.englishclass.call_feature.domain.repository

import kotlinx.coroutines.flow.Flow
import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.core.domain.model.GeneralId
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus

interface CallRepository {
    suspend fun getCallHistory(idUser:String):Flow<ResponseStatus<List<RealtimeCall>>>
    fun observeCall(id:String):Flow<ResponseStatus<RealtimeCall>>
    suspend fun requestCall(videoCallData:RealtimeCall,onResult:(ResponseStatus<String>)->Unit)
}