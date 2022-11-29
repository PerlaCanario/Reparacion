package edu.ucne.reparacion.data.repository

import edu.ucne.reparacion.data.SweetPlansDb
import edu.ucne.reparacion.model.Usuario
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    val db: SweetPlansDb
) {
    suspend fun Insertar(usuario: Usuario)=db.usuarioDao.Insertar(usuario)

    fun Lista()= db.usuarioDao.Lista()

}