package edu.ucne.reparacion.data.Dao

import androidx.room.*
import edu.ucne.reparacion.model.Agenda
import kotlinx.coroutines.flow.Flow

@Dao
interface AgendaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar (agenda: Agenda)

    @Delete
    suspend fun Eliminar(agenda: Agenda)

    @Query("SELECT * FROM Agendas ORDER BY agendaId")
    fun gesList(): Flow<List<Agenda>>

    @Update
    suspend fun Modificar(agenda: Agenda)


}