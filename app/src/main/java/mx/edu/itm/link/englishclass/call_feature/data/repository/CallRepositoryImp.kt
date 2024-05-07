package mx.edu.itm.link.englishclass.call_feature.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import mx.edu.itm.link.englishclass.call_feature.domain.model.Call
import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import javax.inject.Inject

class CallRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val realtimeDatabase:FirebaseDatabase
) : CallRepository {

    override suspend fun getCallHistory(idUser: String): Flow<ResponseStatus<List<Call>>> = firestore.collection(FirestoreCollecions.USER)
        .document(idUser)
        .collection(FirestoreCollecions.CALL_HISTORY)
        .snapshots()
        .mapNotNull {
            ResponseStatus.Success(it.toObjects<Call>())
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }
}