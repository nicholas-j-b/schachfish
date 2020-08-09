package com.nicholasbrooking.pkg.schachfish

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class SchachfishApplication

fun main(args: Array<String>) {
	runApplication<SchachfishApplication>(*args)
}
