package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.listatareas

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.iessochoa.danielvitaltorres.tareasv01.R
import net.iessochoa.danielvitaltorres.tareasv01.ui.components.AppBar
import net.iessochoa.danielvitaltorres.tareasv01.ui.theme.LightBlue80

@Preview
@Composable
fun ListaTareasScreen(
    viewModel: ListaTareasViewModel = viewModel(),
    onClickNuevaTarea: () -> Unit = {},
    onClickModificarTarea: (pos: Long?) -> Unit = {},
    onClickVerTarea: (pos: Long?) -> Unit = {}
){
    val uiStateTarea by viewModel.listaTareasUiState.collectAsState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClickNuevaTarea,
                containerColor = LightBlue80
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tarea_nueva)
                )
            }
        },
        topBar ={
            AppBar(
                tituloPantallaActual = stringResource(R.string.lista_tareas),
                puedeNavegarAtras = false
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            uiStateTarea.forEach{
                    tarea ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = stringResource(R.string.mostrar),
                        modifier = Modifier
                            .clickable {
                                onClickVerTarea(tarea.id)
                            }
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = tarea.tecnico,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .clickable {
                                onClickModificarTarea(tarea.id)
                            }
                    )
                }
            }
        }
    }
}


