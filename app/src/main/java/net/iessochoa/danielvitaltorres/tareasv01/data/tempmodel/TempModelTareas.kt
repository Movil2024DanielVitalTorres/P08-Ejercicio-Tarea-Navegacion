package net.iessochoa.danielvitaltorres.tareasv01.data.tempmodel

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import net.iessochoa.danielvitaltorres.tareasv01.R
import net.iessochoa.danielvitaltorres.tareasv01.data.db.entities.Tarea
import kotlin.random.Random

object TempModelTareas {
    //lista de tareas
    val listaTareas = ArrayList<Tarea>()
    //StateFlow observable
    private val _tareasStateFlow =
        MutableStateFlow<List<Tarea>>(listaTareas)

    /**
     * devuelve un Flow como sucede en la base de datos
     */
    fun getAllTareas(): Flow<List<Tarea>> {
        return _tareasStateFlow
    }
    /**
     * añade una tarea, si existe (id iguales) la sustituye
     * y si no la añade.
     */
    fun addTarea(tarea: Tarea) {
        val pos = listaTareas.indexOf(tarea)
        if (pos < 0) {//si no existe
            listaTareas.add(tarea)
        } else {
            //si existe se sustituye
            listaTareas.set(pos, tarea)
        }
        //actualizamos el StateFlow
        _tareasStateFlow.value = listaTareas
    }
    fun delTarea(tarea: Tarea) {
        listaTareas.remove(tarea)
        _tareasStateFlow.value = listaTareas
    }
    //obtiene una tarea a partir de su id
    fun getTarea(id: Long): Tarea? {
        return listaTareas.find { it.id == id }
    }

    /**
     * Crea unas Tareas de prueba de forma aleatoria.
     */
    fun iniciaPruebaTareas() {
        val tecnicos = listOf(
            "Pepe Gotero",
            "Sacarino Pómez",
            "Mortadelo Fernández",
            "Filemón López",
            "Zipi Climent",
            "Zape Gómez",
            "Pepito Grillo"
        )
        val fotos=
            listOf(R.drawable.demio_ej1,R.drawable.semaforo_ej2,R.drawable.monitor_ej3,R.drawable.palmera_ej4)
        lateinit var tarea: Tarea
        (1..10).forEach({
            tarea = Tarea(
               categoria = (0..4).random(),
                prioridad = (0..2).random(),
                img = fotos.random().toString(),
                pagado = Random.nextBoolean(),
                estado = (0..2).random(),
                valoracionCliente = (0..5).random(),
                tecnico = tecnicos.random(),
                descripcion = "tarea $it : Lorem \n ipsum dolor sit amet, consectetur adipiscing elit. Mauris consequat ligula et vehicula mattis. \n Etiam tristique ornare lacinia. \nVestibulum lacus magna, dignissim et tempor id, convallis sed augue"
            )
            listaTareas.add(tarea)
        })
        _tareasStateFlow.value = listaTareas
    }

    //Permite iniciar el objeto Singleton
    operator fun invoke(){
        //this.application= context.applicationContext as Application
        iniciaPruebaTareas()
    }


}