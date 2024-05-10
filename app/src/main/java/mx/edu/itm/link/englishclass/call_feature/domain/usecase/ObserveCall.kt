package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import javax.inject.Inject

class ObserveCall @Inject constructor(
    private val repo:CallRepository
){
    operator fun invoke(idCall:String)=repo.observeCall(id = idCall)
}