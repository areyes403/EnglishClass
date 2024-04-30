package mx.edu.itm.link.englishclass.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.data.model.User
import mx.edu.itm.link.englishclass.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.domain.repository.AuthRepository
import javax.inject.Singleton

@Singleton
class AuthRepositoryImp(
    private val authRef: FirebaseAuth
) : AuthRepository {
    override suspend fun login(emai: String, password: String): ResponseStatus<String> = try {
        val user = authRef.signInWithEmailAndPassword(emai,password).await()
        ResponseStatus.Success(user.user!!.uid)
    }catch (e:Exception){
        ResponseStatus.Error(e.localizedMessage)
    }

    override suspend fun register(email: String, password: String, user: User):ResponseStatus<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): FirebaseUser? = authRef.currentUser
}