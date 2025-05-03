package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.vistatarea

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import net.iessochoa.danielvitaltorres.tareasv01.data.tempmodel.TempModelTareas

class VistaTareaViewModel : ViewModel() {
    val _uiStateVistaTarea = MutableStateFlow(UiStateVistaTareas())


    fun findTarea(destinoTarea: Long?) {
        val tarea = destinoTarea?.let { TempModelTareas.getTarea(it) }
        _uiStateVistaTarea.update {
            it.copy(tarea = tarea)
        }
    }
}