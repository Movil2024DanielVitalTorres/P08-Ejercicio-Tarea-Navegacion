package net.iessochoa.danielvitaltorres.tareasv01.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.listatareas.ListaTareasScreen
import net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.tarea.TaskScreen
import net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.vistatarea.VistaTareaScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ListaTareaRuta
    ) {
        composable(ListaTareaRuta) {
            ListaTareasScreen(
                onClickNuevaTarea = {
                    navController.navigate("$TareaRuta?destinoTarea=null")
                },
                onClickModificarTarea = { destinoTarea ->
                    navController.navigate("$TareaRuta?destinoTarea=$destinoTarea")
                },
                onClickVerTarea = { destinoTarea ->
                    navController.navigate("$VistaTareaRuta?posicionTarea=$destinoTarea")
                }
            )
        }
        composable("$TareaRuta?destinoTarea={destinoTarea}") { backStackEntry ->
            val destinoTarea = backStackEntry.arguments?.getString("destinoTarea")?.toLongOrNull()
            TaskScreen(idTarea = destinoTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onMostrar = {
                    if (destinoTarea != null)
                        navController.navigate("$VistaTareaRuta?destinoTarea=$destinoTarea")
                }
                )
        }
        composable("$VistaTareaRuta?destinoTarea={destinoTarea}") {
            backStackEntry -> val destinoTarea = backStackEntry.arguments?.getString("destinoTarea")?.toLongOrNull()
            VistaTareaScreen(
                destinoTarea = destinoTarea,
                onVolver = {
                    navController.navigateUp()
                },
                onVolverToInicio = {
                    navController.navigate(ListaTareaRuta)
                }
            )
        }
    }
}