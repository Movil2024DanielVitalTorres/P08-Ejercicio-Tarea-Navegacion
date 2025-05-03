package net.iessochoa.danielvitaltorres.tareasv01.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data class TareaDestino(val destinoTarea: Long? = null)

@Serializable
data class VistaTareaDestino(val destinoVista: Long? = null)

val TareaRuta = "tarea"
val ListaTareaRuta = "lista_tareas"
val VistaTareaRuta = "vista_tarea"