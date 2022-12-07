package edu.ucne.reparacion.ui.Agenda

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.reparacion.data.Remote.Dto.AgendaDto
import edu.ucne.reparacion.data.repository.Api_Repository.AgendaApiRepository
import edu.ucne.reparacion.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModelApi @Inject constructor(
    private val agendaApiRepository: AgendaApiRepository
) : ViewModel() {
    var NombreAgenda by mutableStateOf("")
    var descripcion by mutableStateOf("")
    var fecha by mutableStateOf("")

    private var _state = mutableStateOf(AgendaListState())
    val state: State<AgendaListState> = _state

    init {
        agendaApiRepository.getAgenda().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = AgendaListState(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = AgendaListState(agenda = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AgendaListState(error = result.message ?: "Error desconocido")
                }
            }
        }.launchIn(viewModelScope)
    }
    fun Guardar() {
        viewModelScope.launch {
            agendaApiRepository.postAgenda(
                AgendaDto(
                    agendaId = 0,
                    nombreAgenda = NombreAgenda,
                    descripcion = descripcion,
                    fecha = fecha
                )
            )
        }
    }
}