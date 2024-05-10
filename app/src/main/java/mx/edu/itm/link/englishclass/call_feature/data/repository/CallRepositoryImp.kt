package mx.edu.itm.link.englishclass.call_feature.data.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.snapshots
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.call_feature.domain.model.RealtimeCall
import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.FirebaseReferences
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import javax.inject.Inject

class CallRepositoryImp @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val realtimeDatabase:FirebaseDatabase
) : CallRepository {

    override suspend fun getCallHistory(idUser: String): Flow<ResponseStatus<List<RealtimeCall>>> = firestore.collection(FirestoreCollecions.USER)
        .document(idUser)
        .collection(FirestoreCollecions.CALL_HISTORY)
        .snapshots()
        .mapNotNull {
            ResponseStatus.Success(it.toObjects<RealtimeCall>())
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }

    override fun observeCall(id:String): Flow<ResponseStatus<RealtimeCall>> = realtimeDatabase.getReference(FirebaseReferences.CALLS)
        .child(id)
        .snapshots
        .mapNotNull { data->
            data.getValue(RealtimeCall::class.java)?.let {
                ResponseStatus.Success(it)
            }
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }

    override suspend fun requestCall(videoCallData: RealtimeCall, onResult: (ResponseStatus<String>) -> Unit) {
        try {
            realtimeDatabase.getReference(FirebaseReferences.CALLS)
                .child(videoCallData.id)
                .setValue(videoCallData)
                .await()

            onResult.invoke(ResponseStatus.Success("Completed"))

        }catch (e:Exception){
            onResult.invoke(ResponseStatus.Error(e.localizedMessage))
        }
    }
}