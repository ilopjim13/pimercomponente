package com.es.pimercomponente.controller

import com.es.pimercomponente.model.Libro
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LibroController {

    private val libros:MutableList<Libro> = mutableListOf(Libro(1, "El señor de  los anillos", "Fantasía", "J.R.R Tolkien"))

    // vamos a hacer un CRUD de libros
    // vamos a hacer un métoodo que acepte C (crear) -> POST
    // vamos a hacer un métoodo que acepte R (leer) -> GET
    // vamos a hacer un métoodo que acepte U (actualizar) -> PUT
    // vamos a hacer un métoodo que acepte D (eliminar) -> DELETE

    @PostMapping("/libros")
    fun insert(
        @RequestBody libroNuevo:Libro
    ):Libro? {
        if (libros.find { libroNuevo.id == it.id } == null) {
            libros.add(libroNuevo)
            return libros.find { libroNuevo.id == it.id }
        }
        return null
    }

    @GetMapping("/libros/{id}")
    fun getById( @PathVariable id:String ) :Libro? = libros.find { id.toLongOrNull() == it.id }


    @PutMapping("/libros")
    fun update(
        @RequestBody libroAct:Libro
    ):Libro? {
        val libro = libros.find { libroAct.id == it.id }
        if (libro != null) {
            libro.titulo = libroAct.titulo
            libro.genero = libroAct.genero
            libro.autor = libroAct.autor
            return libro
        }
        return null
    }

    @DeleteMapping("/libros/{id}")
    fun deleteById(
        @PathVariable id:String
    ):String {
        val libro = libros.find { id.toLongOrNull() == it.id }
         return if (libro != null) {
            libros.remove(libro)
            "Se ha eliminado el libro correctamente"
        } else "No se ha podido eliminar el libro"
    }

}