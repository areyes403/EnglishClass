package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import javax.inject.Inject

class RequestCall @Inject constructor(
    private val repo:CallRepository
) {
    suspend operator fun invoke(data:RealtimeCall,onResult: (ResponseStatus<String>) -> Unit) = repo.requestCall(videoCallData = data){
        onResult.invoke(it)
    }
}