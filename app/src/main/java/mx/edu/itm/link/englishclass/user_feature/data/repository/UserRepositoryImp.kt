package mx.edu.itm.link.englishclass.user_feature.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.Token
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import mx.edu.itm.link.englishclass.core.utils.UserCollection
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

class UserRepositoryImp(
    private val firestore:FirebaseFirestore
):UserRepository {
    override suspend fun getUser(uid: String): ResponseStatus<User> = try {
        val user= firestore.collection(FirestoreCollecions.USER)
            .document(uid)
            .get()
            .await()
            .toObject(User::class.java)

        ResponseStatus.Success(user!!)
    } catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun setUser(user: User): ResponseStatus<Unit> = try {

        firestore.collection(FirestoreCollecions.USER)
            .document(user.id)
            .set(user)
            .await()

        ResponseStatus.Success(Unit)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun getActiveUsers(): Flow<ResponseStatus<List<User>>> = firestore.collection(FirestoreCollecions.USER)
        .snapshots()
        .mapNotNull {
            ResponseStatus.Success(it.toObjects(User::class.java))
        }.catch {
            ResponseStatus.Error(it.localizedMessage)
        }

    override suspend fun getRemoteToken(uid:String): ResponseStatus<String> = try {
        val token=firestore.collection(FirestoreCollecions.TOKEN)
            .document(uid)
            .get()
            .await()
            .toObject<Token>()

        ResponseStatus.Success(token!!.token)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun updateRemoteToken(uid: String, token: Token) {
        try {
            firestore.collection(FirestoreCollecions.TOKEN)
                .document(uid)
                .set(token)
                .await()
        }catch (_:Exception){

        }
    }


}