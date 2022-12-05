package edu.ucne.reparacion.ui.navegacion

sealed class Screen(
    val route: String
){
    object RegistroUsuarios: Screen("RegistroUsuariosScreen")
    object LoginScreen: Screen("LoginScreen")
    object InicioScreen: Screen("InicioScreen")
    object RegistroAgenda: Screen("RegistroAgenda")
    object  CalendarioView: Screen("CalendarioView")
    object ConsultaAgenda: Screen("ConsultaAgenda")
}