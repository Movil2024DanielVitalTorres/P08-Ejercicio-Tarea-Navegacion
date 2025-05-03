package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.listatareas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import net.iessochoa.danielvitaltorres.tareasv01.data.tempmodel.TempModelTareas

class ListaTareasViewModel() : ViewModel() {

    val listaTareasUiState = TempModelTareas.getAllTareas().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
}