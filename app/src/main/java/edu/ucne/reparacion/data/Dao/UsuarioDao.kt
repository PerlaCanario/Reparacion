package edu.ucne.reparacion.data.Dao

import androidx.room.*
import edu.ucne.reparacion.model.Usuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UsuarioDao  {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(usuario: Usuario)

    @Delete
    suspend fun Eliminar(usuario: Usuario)

    @Query(
        "SELECT * FROM usuario ORDER BY nombre"
    )
    fun Lista(): Flow<List<Usuario>>
}