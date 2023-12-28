package br.com.rodrigobraz.restspringbootkotlin.controllers

import br.com.rodrigobraz.restspringbootkotlin.entities.Greeting
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class GreetingController {

    val counter: AtomicLong = AtomicLong()

    @RequestMapping("/greetings")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String?) : Greeting {
        return Greeting(counter.incrementAndGet(), "Hello, $name!")
    }
}