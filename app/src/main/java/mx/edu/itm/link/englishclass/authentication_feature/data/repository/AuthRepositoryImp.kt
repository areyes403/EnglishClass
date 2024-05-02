package mx.edu.itm.link.englishclass.authentication_feature.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.profile_feature.domain.model.User
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import javax.inject.Singleton

@Singleton
class AuthRepositoryImp(
    private val authRef: FirebaseAuth,
    private val firetoreRef:FirebaseFirestore
) : AuthRepository {

    override suspend fun login(emai: String, password: String): ResponseStatus<String> = try {
        val user = authRef.signInWithEmailAndPassword(emai,password).await()
        ResponseStatus.Success(user.user!!.uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun register(email: String, password: String, user: User): ResponseStatus<String> = try {
        val uid = authRef.createUserWithEmailAndPassword(email,password).await().user!!.uid
        firetoreRef.collection(FirestoreCollecions.USER).document(uid).set(user)
        ResponseStatus.Success(uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }
}