package mx.edu.itm.link.englishclass.authentication_feature.domain.usecase


import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import mx.edu.itm.link.englishclass.authentication_feature.domain.model.Token
import mx.edu.itm.link.englishclass.user_feature.domain.model.User
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.AuthRepository
import mx.edu.itm.link.englishclass.authentication_feature.domain.repository.RoomRepository
import mx.edu.itm.link.englishclass.core.domain.model.ResponseStatus
import mx.edu.itm.link.englishclass.user_feature.domain.repository.UserRepository
import javax.inject.Inject

class SignUp @Inject constructor(
    private val authRepo: AuthRepository,
    private val userRepo:UserRepository,
    private val localRepo:RoomRepository
) {
    suspend operator fun invoke(
        email:String,
        password:String,
        user: User
    ):ResponseStatus<Unit>{
        return when(val authResponse=authRepo.register(email, password)){
            is ResponseStatus.Loading-> authResponse
            is ResponseStatus.Error-> authResponse
            is ResponseStatus.Success->{

                user.id=authResponse.data

                val registerUser=userRepo.setUser(user = user)

                if (registerUser is ResponseStatus.Success){
                    try {
                        localRepo.insertUser(user)

                        val token= FirebaseMessaging.getInstance().token.await()

                        userRepo.updateRemoteToken(uid = user.id, token = Token(token = token))
                    }finally {

                    }

                    ResponseStatus.Success(Unit)
                }else ResponseStatus.Error((registerUser as ResponseStatus.Error).error)
            }
        }
    }
}