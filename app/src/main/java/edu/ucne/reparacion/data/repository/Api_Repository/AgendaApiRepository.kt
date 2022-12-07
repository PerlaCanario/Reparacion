package edu.ucne.reparacion.data.repository.Api_Repository

import edu.ucne.reparacion.data.Remote.AgendaApi
import edu.ucne.reparacion.data.Remote.Dto.AgendaDto
import edu.ucne.reparacion.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class AgendaApiRepository @Inject constructor(
    private val api : AgendaApi
) {
    fun getAgenda(): Flow<Resource<List<AgendaDto>>> = flow {
        try {
            emit(Resource.Loading())
            val agenda = api.getAll()
            emit(Resource.Success(agenda))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    suspend fun postAgenda(agendaDto: AgendaDto): AgendaDto {
        return api.insert(agendaDto)
    }
}
