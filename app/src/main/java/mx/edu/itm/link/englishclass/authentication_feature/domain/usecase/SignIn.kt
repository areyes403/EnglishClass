package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.Token
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class SignIn @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo: UserRepository,
    private val roomRepo:RoomRepository,
) {
    suspend operator fun invoke(email:String, password:String):ResponseStatus<Unit>{

        return when(val signInResult=authRepo.login(email, password)){
            is ResponseStatus.Loading-> signInResult

            is ResponseStatus.Error-> ResponseStatus.Error(signInResult.error)

            is ResponseStatus.Success-> {
                val getUserResult=userRepo.getUser(signInResult.data)
                if (getUserResult is ResponseStatus.Success){
                    try {
                        roomRepo.insertUser(getUserResult.data)

                        val token=FirebaseMessaging.getInstance().token.await()

                        userRepo.updateRemoteToken(uid = signInResult.data, token = Token(token = token))
                    }finally {
                        Log.i("localUser","success")
                    }
                    ResponseStatus.Success(Unit)
                }else{
                    ResponseStatus.Error((getUserResult as ResponseStatus.Error).error)
                }
            }
        }
    }
}