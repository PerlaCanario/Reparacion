package edu.ucne.reparacion.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.reparacion.data.Remote.AgendaApi
import edu.ucne.reparacion.data.Remote.UsuarioApi
import edu.ucne.reparacion.data.SweetPlansDb
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun ProvideUsuarioDd(@ApplicationContext context: Context): SweetPlansDb {
        val DATABASE_NAME = "SweetPlansDb"
        return Room.databaseBuilder(
            context,
            SweetPlansDb::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Singleton
    @Provides
    fun providesAgendaApi(moshi: Moshi): AgendaApi {
        return Retrofit.Builder()
            .baseUrl("http://www.proyectosEducativos.somee.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AgendaApi::class.java)
    }

    @Singleton
    @Provides
    fun providesUsuarioApi(moshi: Moshi): UsuarioApi {
        return Retrofit.Builder()
            .baseUrl("http://www.proyectosEducativos.somee.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UsuarioApi::class.java)
    }

}