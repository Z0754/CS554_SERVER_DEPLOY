package com.example.cs554server

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
class Cs554serverApplication

fun main(args: Array<String>) {
	runApplication<Cs554serverApplication>(*args)
}

@RestController
class LayoutTest01Resource {
	@GetMapping(value = ["/LayoutTest01"])
	fun sampleLayout(): Layout {
		var layout = Layout(1, listOf(
			Component("4", "This is a text field", ""),
			Component("1", "This is a button", "nil"),
			Component("2", "This is a list", ""),
			Component("3", "This is a aa", ""),
			Component("3", "This is a ddd", ""),
			Component("4", "This is a fff", ""),
			Component("1", "This is a rrr", ""),
			Component("2", "This is a eee", ""),
			Component("4", "This is a yyyy", ""),
			Component("3", "This is a hhh", ""),
			Component("4", "This is a bvvv", ""),
			Component("1", "This is a 222", ""),
			Component("3", "This is a lisee2e2t", ""),



			))
		return layout
	}

	data class Message(val id: String?, val text: String)
	data class Component(val variant: String, val data: String?, val action: String?)
	data class Layout(val pageID: Int, val components: List<Component>)


}

@RestController
class EchoPayloadResource {
	@PostMapping(value = ["/EchoPayload"])
	fun echo(@RequestBody payload: String): String{
		return payload
	}
}

