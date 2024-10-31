package com.es.pimercomponente.controller

import com.es.pimercomponente.model.Saludo
import com.es.pimercomponente.model.Usuario
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

// Las peiciones qu entren, el dispatcher las va a repartir al controller adecuado.
@RestController // Con la anotación restController Spring ya sabe que esta clase es un controlador
class HolaMundoController {

    val mapaUsuarios:MutableMap<String, String> = mutableMapOf("diego" to "1234", "pepe" to "qwerty")

    @GetMapping("/saludo")
    fun getHolaMundo(
        @RequestParam(name = "nombre") nombre:String
    ): Saludo {
        return Saludo("Hola", nombre)
    }

    @GetMapping("/user")
    fun getCredentials(@RequestParam(name = "nombre") nombre:String):Usuario? {
        if (nombre in mapaUsuarios) {
            val p = mapaUsuarios[nombre]
            if(p != null) return Usuario(nombre, p)
        }
        return null
    }


    @PostMapping("/insert")
    fun postUser(
        @RequestParam nombre:String,
        @RequestParam pass:String
    ) :Usuario? {
        if (nombre !in mapaUsuarios) {
            mapaUsuarios[nombre] = pass
            return Usuario(nombre, pass)
        }
        return null
    }

    @DeleteMapping("/delete")
    fun deleteUser(
        @RequestParam nombre:String
    ):String {
        if (nombre in mapaUsuarios){
            mapaUsuarios.remove(nombre)
            return "Se ha eliminado el usuario correctamente"
        }
        return "No se ha podido eliminar el usuario"
    }


}