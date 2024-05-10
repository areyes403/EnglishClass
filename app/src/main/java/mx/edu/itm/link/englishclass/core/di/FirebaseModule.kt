package mx.edu.itm.link.englishclass.core.di


import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.core.utils.FirestoreCollecions
import mx.edu.itm.link.englishclass.core.utils.UserCollection
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideAuthRef(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirestoreRef():FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseRef():FirebaseDatabase = Firebase.database

    @UserCollection
    @Provides
    @Singleton
    fun provideUsersCollection()= provideFirestoreRef().collection(FirestoreCollecions.USER)

    @Provides
    @Singleton
    fun provideFirebaseMessaging():FirebaseMessaging= Firebase.messaging

}



