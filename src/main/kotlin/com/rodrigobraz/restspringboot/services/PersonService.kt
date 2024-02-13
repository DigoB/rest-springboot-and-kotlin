package com.rodrigobraz.restspringboot.services

import com.rodrigobraz.restspringboot.data.vo.v1.PersonVO
import com.rodrigobraz.restspringboot.exceptions.ResourceNotFoundException
import com.rodrigobraz.restspringboot.mapper.Mapper
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

    fun findAll() : List<PersonVO> {
        logger.info("Finding all persons...")

        val persons = repository.findAll()

        return Mapper.parseListObjects(persons, PersonVO::class.java)
    }

    fun findById(id: Long) : PersonVO {
        logger.info("Searching person...")

        var person = repository.findById(id)
            .orElseThrow{ ResourceNotFoundException("No records found for this id.")}

        return Mapper.parseObject(person, PersonVO::class.java)
    }

    fun createPerson(person: PersonVO) : PersonVO {
        logger.info("Creating one person with name ${person.firstName}")

        var entity: Person = Mapper.parseObject(person, Person::class.java)
        return Mapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun updatePerson(person: PersonVO) : PersonVO{

        logger.info("Updating one person with id $person.id")

        val entity = repository.findById(person.id)
            .orElseThrow{ ResourceClosedException("No records found for this id.")}
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return Mapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun deletePerson(id: Long) {

        logger.info("Deleting one person with id $id")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this id.")}
        repository.delete(entity)
    }
}