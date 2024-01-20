package com.rodrigobraz.restspringboot.controllers

import com.rodrigobraz.restspringboot.models.Person
import com.rodrigobraz.restspringboot.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() : List<Person> {
        return service.findAll()
    }

    @GetMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPersonById(@PathVariable(value = "id") id: Long) : Person {
        return service.findById(id)
    }

    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE],
                consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person) : Person {
        return service.createPerson(person)
    }

    @PutMapping(produces = [MediaType.APPLICATION_JSON_VALUE],
                consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person) : Person {
        return service.updatePerson(person)
    }

    @DeleteMapping(value = ["/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePersonById(@PathVariable(value = "id") id: Long) : ResponseEntity<*> {
        service.deletePerson(id)

        return ResponseEntity.noContent().build<Any>()
    }

}