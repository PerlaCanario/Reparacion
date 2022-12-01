package edu.ucne.reparacion.data.Remote

import edu.ucne.reparacion.data.Remote.Dto.UsuarioDto
import retrofit2.http.*

interface UsuarioApi {
    @GET("/Usuario/GetUsuario")
    suspend fun getAll(): List<UsuarioDto>

    @GET("/Usuario/GetUsuario{id}")
    suspend fun getById(@Path("id") id: String): UsuarioDto

    @PUT("/Usuario/PutUsuario{id}")
    suspend fun update(@Path("id") id: String, @Body usuario: UsuarioDto): UsuarioDto

    @POST("/Usuario/PostUsuario")
    suspend fun insert(@Body usuario: UsuarioDto): UsuarioDto
}