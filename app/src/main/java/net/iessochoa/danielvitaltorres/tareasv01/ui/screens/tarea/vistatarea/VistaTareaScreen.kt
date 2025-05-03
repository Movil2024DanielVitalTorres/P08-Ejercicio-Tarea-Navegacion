package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.vistatarea

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.iessochoa.danielvitaltorres.tareasv01.R
import net.iessochoa.danielvitaltorres.tareasv01.ui.components.AppBar

import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun VistaTareaScreen(
    viewModel: VistaTareaViewModel = viewModel(),
    destinoTarea: Long?,
    onVolver: () -> Unit,
    onVolverToInicio: () -> Unit

) {
    viewModel.findTarea(destinoTarea)
    val uiStateVistaTarea by viewModel._uiStateVistaTarea.collectAsState()
    Scaffold(
        topBar = {
            AppBar(
                tituloPantallaActual = R.string.ver_tarea.toString() + uiStateVistaTarea.tarea,
                puedeNavegarAtras = true,
                navegaAtras = onVolver
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier) {
                Text(text = "Volver")
            }
            Button(onClick = onVolverToInicio) {
                Text(stringResource(R.string.volver_al_inicio))
            }
        }
    }
}