package edu.ucne.reparacion.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.reparacion.data.Dao.AgendaDao
import edu.ucne.reparacion.data.Dao.UsuarioDao
import edu.ucne.reparacion.model.Agenda
import edu.ucne.reparacion.model.Usuario

@Database(
    entities = [
        Usuario ::class ,
        Agenda :: class
    ],
    exportSchema = false,
    version = 1
)
abstract class SweetPlansDb : RoomDatabase() {
    abstract val usuarioDao: UsuarioDao
    abstract val AgendaDao : AgendaDao
}