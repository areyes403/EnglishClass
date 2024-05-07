package mx.edu.itm.link.englishclass.call_feature.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.edu.itm.link.englishclass.call_feature.data.repository.CallRepositoryImp
import mx.edu.itm.link.englishclass.call_feature.domain.repository.CallRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CallModule {

    @Provides
    @Singleton
    fun provideCallModule(
        fireb:FirebaseFirestore,
        realtime:FirebaseDatabase
    ):CallRepository=CallRepositoryImp(firestore = fireb, realtimeDatabase = realtime)
}