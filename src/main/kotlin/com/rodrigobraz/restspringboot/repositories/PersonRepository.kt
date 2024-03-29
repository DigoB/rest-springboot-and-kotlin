package com.rodrigobraz.restspringboot.repositories

import com.rodrigobraz.restspringboot.models.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : JpaRepository<Person, Long?>