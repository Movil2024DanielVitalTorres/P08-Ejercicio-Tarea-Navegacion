package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import kotlinx.coroutines.launch
import net.iessochoa.danielvitaltorres.tareasv01.R
import net.iessochoa.danielvitaltorres.tareasv01.ui.components.DialogoDeConfirmacion
import net.iessochoa.danielvitaltorres.tareasv01.ui.components.DynamicSelectTextField
import net.iessochoa.danielvitaltorres.tareasv01.ui.theme.ColorPrioridadAlta
import net.iessochoa.danielvitaltorres.tareasv01.ui.theme.TareasV01Theme


val imageVector = Icons.Filled.Star



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..maxRating) {
            IconButton(onClick = { onRatingChanged(i) }) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (i <= currentRating) Color.Yellow else Color.Gray
                )
            }
        }
    }
}



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TareasV01Theme {
                TaskScreen()
            }
        }
    }
}

@Composable
fun TaskScreen(
    viewModel: TareaViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiStateTarea by viewModel.uiStateTarea.collectAsState()

    var selectedCategory by remember { mutableStateOf("Reparación") }

    var isPaid by remember { mutableStateOf(false) }
    var taskStatus by remember { mutableStateOf("Abierta") }
    var rating by remember { mutableStateOf(3) } // Valoración de cliente
    var technicianName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var listaPrioridad = stringArrayResource(id = R.array.prioridad).toList()
    var selectedPriority by remember { mutableStateOf(listaPrioridad[0]) }

    //var colorFondo=if(listaPrioridad[2] == selectedPriority) ColorPrioridadAlta else Color.Transparent

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost =  {SnackbarHost(snackbarHostState)},
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (uiStateTarea.esFormularioValido)
                    viewModel.onGuardar()
                else {
                    scope.launch{
                        snackbarHostState.showSnackbar(
                            message = "Hay que rellenar todos los campos",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            }
            ){
                Icon(painter = painterResource(android.R.drawable.ic_menu_save),
                    contentDescription = "guardar")
            }},
        modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
            color = uiStateTarea.colorFondo
        ) {



            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(uiStateTarea.colorFondo)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Row(modifier = Modifier.padding(16.dp)) {
                    Column(
                        modifier = Modifier
                            .padding(2.dp)
                            .weight(1F)
                    ) {
                        DynamicSelectTextField(
                            label = stringResource(R.string.categor_a),
                            options = viewModel.listaCategoria,
                            selectedValue = uiStateTarea.categoria,
                            onValueChangedEvent = { viewModel.onValueChangeCategoria(it) }
                        )

                        DynamicSelectTextField(
                            label = stringResource(R.string.prioridad),
                            options = viewModel.listaPrioridad,
                            selectedValue = uiStateTarea.prioridad,
                            onValueChangedEvent = { viewModel.onValueChangePrioridad(it) }
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.nevera_hisense), // Tu imagen aquí
                        contentDescription = stringResource(R.string.imagen_de_nevera),
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxWidth()
                            .weight(1F),
                        contentScale = ContentScale.Crop
                    )
                }


                Row() {
                    Icon(
                        painter = painterResource(
                            id = if (uiStateTarea.pagado) R.drawable.baseline_thumb_up_24 else R.drawable.baseline_thumb_down_24
                        ),
                        contentDescription = stringResource(R.string.icono_de_estado),
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(stringResource(R.string.est_pagado))
                    Switch(
                        checked = uiStateTarea.pagado,
                        onCheckedChange = { isChecked ->
                            viewModel.onValueChangePagado(isChecked)
                        }
                    )

                }

                Row() {
                    Text(stringResource(R.string.estado_de_la_tarea))
                    Icon(
                        painter = painterResource(
                            id = when (taskStatus) {
                                "Abierta" -> R.drawable.baseline_star_border_purple500_24
                                "En Curso" -> R.drawable.baseline_navigate_next_24
                                "Cerrada" -> R.drawable.baseline_close_24
                                else -> {
                                    R.drawable.baseline_star_border_purple500_24
                                }
                            }
                        ),
                        contentDescription = stringResource(R.string.icono_de_estado_de_la_tarea)
                    )
                }

                Row {
                    RadioButton(
                        selected = uiStateTarea.estado == stringResource(R.string.abierta),
                        onClick = { taskStatus = "Abierta" })
                    Text(stringResource(R.string.abierta2))
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = uiStateTarea.estado == stringResource(R.string.en_curso),
                        onClick = { taskStatus = "En Curso" })
                    Text(stringResource(R.string.en_curso2))
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = uiStateTarea.estado == stringResource(R.string.cerrada),
                        onClick = { taskStatus = "Cerrada" })
                    Text(stringResource(R.string.cerrada2))
                }


                Text(stringResource(R.string.valoraci_n_cliente))
                RatingBar(
                    currentRating = uiStateTarea.valoracion,
                    onRatingChanged = { viewModel.onValueChangeValoracion(it) }
                )



                OutlinedTextField(
                    value = uiStateTarea.tecnico,
                    onValueChange = { viewModel.onTecnicoValueChange(it) },
                    label = { Text(text = stringResource(R.string.tecnico)) },
                    placeholder = { Text(text = stringResource(R.string.tecnico)) },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = uiStateTarea.descripcion,
                    onValueChange = { viewModel.onDescripcionValueChange(it) },
                    label = { Text(text = stringResource(R.string.descripcion)) },
                    placeholder = { Text(text = stringResource(R.string.descripcion)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)){

                }
                if (uiStateTarea.mostrarDialogo) {
                    DialogoDeConfirmacion(
                        onDismissRequest = {
                            //cancela el dialogo
                            viewModel.onCancelarDialogoGuardar()
                        },
                        onConfirmation = {
                            //guardaría los cambios
                            viewModel.onConfirmarDialogoGuardar()
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = ("Tarea guardada"),
                                    duration = SnackbarDuration.Short
                                )
                            }
                        },
                        dialogTitle = stringResource(R.string.atenci_n),
                        dialogText = stringResource(R.string.desea_guardar_los_cambios),
                        icon = Icons.Default.Info
                    )
                }

            }
        }
    }

     }



@Preview(showBackground = true)
@Composable
fun Preview() {
    TareasV01Theme {
        TaskScreen()
    }
}
