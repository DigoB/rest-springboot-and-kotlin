package com.rodrigobraz.restspringboot.services

import com.rodrigobraz.restspringboot.models.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll() : List<Person> {
        logger.info("Finding all persons...")

        val persons: MutableList<Person> = ArrayList()

        for (i in 0..7) {
            val person = mockPerson(i)
            persons.add(person)
        }

        return persons
    }

    private fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $i"
        person.lastName = "Last Name $i"
        person.address = "Address $i"
        person.gender = "Male"

        return person
    }

    fun findById(id: Long) : Person {
        logger.info("Searching person...")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Rodrigo"
        person.lastName = "Braz"
        person.address = "São Paulo"
        person.gender = "Male"

        return person
    }

    fun createPerson(person: Person) = person

    fun updatePerson(person: Person) = person

    fun deletePerson(id: Long) {}
}