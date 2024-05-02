package mx.edu.itm.link.englishclass.core.di


import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    fun provideAuthRef() = Firebase.auth

    @Provides
    fun provideFirestoreRef() = Firebase.firestore
}