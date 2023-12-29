package br.com.rodrigobraz.restspringbootkotlin.services

import br.com.rodrigobraz.restspringbootkotlin.model.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)


    fun findAll() : List<Person> {
        logger.info("Finding all people...")

        val persons: MutableList<Person> = ArrayList()

        for (i in 0..7) {
            val person = mockPerson(i)
            persons.add(person)
        }

        return persons
    }

    fun findPersonById(id: Long) : Person {
        logger.info("Finding one person...")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Rodrigo"
        person.lastName = "Braz"
        person.address = "Rua A"
        person.gender = "M"

        return person
    }

    fun createNewPerson(person: Person) = person

    fun updatePerson(person: Person) = person

    fun deletePerson(id: Long) {}



    private fun mockPerson(i: Int): Person {
        val person = Person()

        person.id = counter.incrementAndGet()
        person.firstName = "Person Name: $i"
        person.lastName = "Last Name: $i"
        person.address = "Some address"
        person.gender = "Some gender"

        return person
    }
}