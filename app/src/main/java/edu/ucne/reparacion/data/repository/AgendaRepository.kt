package edu.ucne.reparacion.data.repository

import edu.ucne.reparacion.data.SweetPlansDb
import edu.ucne.reparacion.model.Agenda
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AgendaRepository @Inject constructor(
    val db: SweetPlansDb
) {

    suspend fun Insertar(agenda: Agenda) =
        db.AgendaDao.Insertar(agenda)

    suspend fun Eliminar(agenda: Agenda) = db.AgendaDao.Eliminar(agenda)

    fun getList(): Flow<List<Agenda>> = db.AgendaDao.gesList()

}