package edu.ucne.reparacion.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.reparacion.data.Dao.UsuarioDao
import edu.ucne.reparacion.data.SweetPlansDb
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

    @Provides
    fun providesUsuarioDao(sweetPlansDb: SweetPlansDb): UsuarioDao {
        return sweetPlansDb.usuarioDao
    }
}