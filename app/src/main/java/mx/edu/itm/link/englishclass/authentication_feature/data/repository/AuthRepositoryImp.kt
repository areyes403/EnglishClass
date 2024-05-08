package mx.edu.itm.link.englishclass.authentication_feature.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.Token
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import javax.inject.Inject
import javax.inject.Singleton

class AuthRepositoryImp @Inject constructor(
    private val authRef: FirebaseAuth,
    private val firestoreRef:FirebaseFirestore,
    private val fcm:FirebaseMessaging
) : AuthRepository {

    override suspend fun login(emai: String, password: String): ResponseStatus<User> = try {
        val ref = authRef.signInWithEmailAndPassword(emai,password).await()
        val user= firestoreRef.collection(FirestoreCollecions.USER)
            .document(ref.user!!.uid)
            .get()
            .await()
            .toObject(User::class.java)

        updateTokenToServer(id = user?.id.toString(), token = getToken())

        ResponseStatus.Success(user!!)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun register(email: String, password: String, user: User): ResponseStatus<User> = try {
        val uid = authRef.createUserWithEmailAndPassword(email,password).await().user!!.uid
        user.id=uid
        firestoreRef.collection(FirestoreCollecions.USER).document(user.id).set(user)
        ResponseStatus.Success(user)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun updateTokenToServer(id: String,token:String) {
        try {
            val data= Token(token = token)
            firestoreRef.collection(FirestoreCollecions.TOKEN)
                .document(id)
                .set(data)
                .await()
        }catch (e:Exception){
            Log.e("userToken",e.localizedMessage)
        }
    }

    override suspend fun getToken(): String = fcm.token.await()
}