package com.scheduleapp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScheduleAppApplication

fun main(args: Array<String>) {
	runApplication<ScheduleAppApplication>(*args)
}
