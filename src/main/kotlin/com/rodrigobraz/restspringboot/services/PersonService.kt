package com.rodrigobraz.restspringboot.services

import com.rodrigobraz.restspringboot.exceptions.ResourceNotFoundException
import com.rodrigobraz.restspringboot.models.Person
import com.rodrigobraz.restspringboot.repositories.PersonRepository
import org.hibernate.ResourceClosedException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<Person> {
        logger.info("Finding all persons...")

        return repository.findAll()
    }

    fun findById(id: Long) : Person {
        logger.info("Searching person...")

        return repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("No records found for this id.")}
    }

    fun createPerson(person: Person) : Person {
        logger.info("Creating one person with name ${person.firstName}")
        return repository.save(person)
    }

    fun updatePerson(person: Person) : Person{

        logger.info("Updating one person with id $person.id")

        val entity = repository.findById(person.id)
            .orElseThrow{ ResourceClosedException("No records found for this id.")}
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    fun deletePerson(id: Long) {

        logger.info("Deleting one person with id $id")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this id.")}
        repository.delete(entity)
    }
}