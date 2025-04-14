package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.tarea

import androidx.compose.ui.graphics.Color

data class UiStateTarea(
    var categoria: String = "",
    val prioridad: String = "",
    val pagado: Boolean = false,
    val estado: String = "",
    var valoracion: Int = 0,
    val tecnico: String = "",
    val descripcion: String = "",
    val colorFondo: Color = Color.Transparent,
    val esFormularioValido: Boolean = false,
    val mostrarDialogo: Boolean = false,
    val esTareaNueva: Boolean = true
)
