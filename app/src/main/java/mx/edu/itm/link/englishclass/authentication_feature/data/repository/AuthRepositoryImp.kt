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

class AuthRepositoryImp (
    private val authRef: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): ResponseStatus<String> = try {

        val ref = authRef.signInWithEmailAndPassword(email,password).await()

        ResponseStatus.Success(ref.user!!.uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun register(email: String, password: String): ResponseStatus<String> = try {
        val uid = authRef.createUserWithEmailAndPassword(email,password).await().user!!.uid

        ResponseStatus.Success(uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }
/*
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

 */

}