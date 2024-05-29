package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.notification_feature.domain.usecase.SendNotification
import javax.inject.Inject

class RequestCall @Inject constructor(
    private val repo:CallRepository,
    private val sendNotificationUseCase:SendNotification
) {
    suspend operator fun invoke(data:RealtimeCall):ResponseStatus<String> {
        return when(val callRequest = repo.requestCall(videoCallData = data)){
            is ResponseStatus.Loading->callRequest
            is ResponseStatus.Error->callRequest
            is ResponseStatus.Success->{

                val idCall=callRequest.data

                val notificationResult=sendNotificationUseCase(receptor = data.receptor!!, idCall = data.id, emisor = data.emisor!!)

                when(notificationResult){
                    is ResponseStatus.Loading-> notificationResult
                    is ResponseStatus.Error->notificationResult
                    is ResponseStatus.Success->ResponseStatus.Success(idCall)
                }
            }
        }
    }
}