package edu.ucne.reparacion.ui.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.reparacion.ui.Inicio.IncioScreen
import edu.ucne.reparacion.ui.Login.LoginScreen
import edu.ucne.reparacion.ui.Login.RegistroUsiario
import edu.ucne.reparacion.ui.Agenda.ConsultaAgenda
import edu.ucne.reparacion.ui.Agenda.RegistroAgenda

@Composable
fun App() {
    val navHostController = rememberNavController()

    NavHost(navController = navHostController, startDestination = Screen.LoginScreen.route) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navHostController = navHostController)
        }
        composable(Screen.RegistroUsuarios.route) {
            RegistroUsiario(navHostController = navHostController)
        }
        composable(Screen.InicioScreen.route) {
            IncioScreen(navHostController = navHostController)
        }
        composable(Screen.RegistroAgenda.route){
           RegistroAgenda(navHostController = navHostController)
        }
        composable(Screen.ConsultaAgenda.route){
           ConsultaAgenda(navHostController = navHostController)
        }

    }
}