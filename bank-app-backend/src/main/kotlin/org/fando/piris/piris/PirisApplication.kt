package org.fando.piris.piris

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PirisApplication

fun main(args: Array<String>) {
	runApplication<PirisApplication>(*args)
}
