package mx.edu.itm.link.englishclass.call_feature.domain.usecase

import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import javax.inject.Inject

class GetCallHistory @Inject constructor(
    private val repo:CallRepository
) {
    suspend fun invoke(
        uid:String
    ) = repo.getCallHistory(idUser = uid)
}