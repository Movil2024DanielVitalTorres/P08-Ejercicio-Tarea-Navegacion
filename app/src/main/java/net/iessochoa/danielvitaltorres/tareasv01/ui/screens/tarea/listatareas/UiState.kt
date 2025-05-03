package net.iessochoa.danielvitaltorres.tareasv01.ui.screens.tarea.listatareas

import net.iessochoa.danielvitaltorres.tareasv01.data.db.entities.Tarea

data class ListUiState(
    val listaPalabra: List<Tarea> = listOf()
)