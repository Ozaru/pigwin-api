package com.github.ozaru.pigwin_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PigwinApiApplication

fun main(args: Array<String>) {
	runApplication<PigwinApiApplication>(*args)
}
