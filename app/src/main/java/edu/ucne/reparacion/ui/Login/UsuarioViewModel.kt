package edu.ucne.reparacion.ui.Login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.reparacion.data.repository.UsuarioRepository
import edu.ucne.reparacion.model.Usuario
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UsuarioListUiState(
    val lista: List<Usuario> = emptyList()
)
@HiltViewModel
class UsuarioViewModel  @Inject constructor (
    val usuarioRepository: UsuarioRepository
): ViewModel(){

    var nombre by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var usuario = usuarioRepository.Lista()
        private set


    var uiState by mutableStateOf(UsuarioListUiState())
        private set

    init{
        viewModelScope.launch {
            usuarioRepository.Lista().collect(){ list->
                uiState = uiState.copy(list)
            }
        }
    }
    fun Guardar(){
        viewModelScope.launch {
            usuarioRepository.Insertar(
                Usuario(
                    nombre = nombre,
                    email = email,
                    password = password
                )
            )
        }
    }
}