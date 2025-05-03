package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import net.iessochoa.danielvitaltorres.tareasv01.data.repository.Repository

class ListaTareasViewModel() : ViewModel() {

    val listaTareasUiState : StateFlow<ListUiState> =
        //transformamos el flow de tareas en el Stateflow de ListaUiState
        Repository.getAllTareas().map {ListUiState(it)}.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ListUiState()
        )
}