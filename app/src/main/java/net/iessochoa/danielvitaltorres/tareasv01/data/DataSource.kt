package net.iessochoa.danielvitaltorres.tareasv01.data

import androidx.compose.runtime.internal.liveLiteral

object DataSource {
    val listaPalabras = mutableListOf("Hola", "Mundo", "Jetpack", "Compose")

    fun nuevaPalabra(palabra: String){
        if(!listaPalabras.contains(palabra))
            listaPalabras.add(palabra)
    }
    fun actualizaPalabra(pos: Int, palabra: String){
        listaPalabras[pos] = palabra;
    }
    fun buscarPalabra(cod: Int?) = if(cod == null) "" else listaPalabras[cod]
}