package com.rodrigobraz.restspringboot.controllers

import com.rodrigobraz.restspringboot.models.Person
import com.rodrigobraz.restspringboot.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService

    @RequestMapping(method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll() : List<Person> {
        return service.findAll()
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findPersonById(@PathVariable(value = "id") id: Long) : Person {
        return service.findById(id)
    }

    @RequestMapping(method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody person: Person) : Person {
        return service.createPerson(person)
    }

    @RequestMapping(method = [RequestMethod.PUT],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updatePerson(@RequestBody person: Person) : Person {
        return service.updatePerson(person)
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE],
        produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deletePersonById(@PathVariable(value = "id") id: Long) {
        service.deletePerson(id)
    }

}