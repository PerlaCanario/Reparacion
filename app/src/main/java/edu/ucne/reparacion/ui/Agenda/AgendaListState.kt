package edu.ucne.reparacion.ui.Agenda

import edu.ucne.reparacion.data.Remote.Dto.AgendaDto

data class AgendaListState(
    val isLoading: Boolean = false,
    val agenda: List<AgendaDto> = emptyList(),
    val error: String = ""
)
