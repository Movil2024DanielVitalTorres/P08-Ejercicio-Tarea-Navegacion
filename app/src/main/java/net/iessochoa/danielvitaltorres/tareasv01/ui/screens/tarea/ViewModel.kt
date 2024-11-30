package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.iessochoa.danielvitaltorres.tareasv01.R
import net.iessochoa.danielvitaltorres.tareasv01.ui.theme.ColorPrioridadAlta

class TareaViewModel(application: Application): AndroidViewModel(application){
    private val context = application.applicationContext

    //prioridad
    val listaPrioridad = context.resources.getStringArray(R.array.prioridad).toList()
    val listaCategoria = context.resources.getStringArray(R.array.categoria).toList()
    val PRIORIDAD_ALTA = listaPrioridad[2]

    private val _uiStateTarea = MutableStateFlow(
        UiStateTarea(prioridad = listaPrioridad[0]
        ))
    val uiStateTarea: StateFlow<UiStateTarea> = _uiStateTarea.asStateFlow()

    private val _uiCategoryTarea = MutableStateFlow(
        UiStateTarea(categoria = listaCategoria[0]
        ))
    val uiCategoryTarea: StateFlow<UiStateTarea> = _uiCategoryTarea.asStateFlow()

    fun onValueChangePrioridad(nuevaPrioridad: String){
        val colorFondo: Color
        if(PRIORIDAD_ALTA==nuevaPrioridad)
            colorFondo = ColorPrioridadAlta
        else
            colorFondo = Color.Transparent

        _uiStateTarea.value = _uiStateTarea.value.copy(
            prioridad = nuevaPrioridad,
            colorFondo = colorFondo
        )
    }

    fun onValueChangeCategoria(nuevaCategoria: String) {
        _uiStateTarea.value = _uiStateTarea.value.copy(categoria = nuevaCategoria)
    }

    fun onValueChangePagado(nuevoPagado: Boolean) {
        _uiStateTarea.value = _uiStateTarea.value.copy(
            pagado = nuevoPagado
        )
    }

    fun onValueChangeEstado(nuevoEstado: String){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            estado = nuevoEstado)
    }

    fun onValueChangeValoracion(nuevaValoracion: Int){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            valoracion = nuevaValoracion)
    }

    fun onTecnicoValueChange(nuevoTecnico: String){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            tecnico = nuevoTecnico, esFormularioValido = nuevoTecnico.isNotBlank() && _uiStateTarea.value.descripcion.isNotBlank() )
    }

    fun onDescripcionValueChange(nuevaDescripcion: String){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            descripcion = nuevaDescripcion, esFormularioValido = nuevaDescripcion.isNotBlank() && _uiStateTarea.value.tecnico.isNotBlank())
    }

    //muestra el diálogo
    fun onGuardar(){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = true
        )
    }

    //guardará los cambios, por el momento solo cierra el diálogo
    fun onConfirmarDialogoGuardar(){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }

    //cierra el diálogo
    fun onCancelarDialogoGuardar(){
        _uiStateTarea.value = _uiStateTarea.value.copy(
            mostrarDialogo = false
        )
    }


}