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
			Component("textField", "This is a text field", ""),
			Component("button", "This is a button", "nil"),
			Component("list", "This is a list", ""),
			Component("button", "This is a aa", ""),
			Component("textField", "This is a ddd", ""),
			Component("button", "This is a fff", ""),
			Component("list", "This is a rrr", ""),
			Component("button", "This is a eee", ""),
			Component("button", "This is a yyyy", ""),
			Component("list", "This is a hhh", ""),
			Component("textField", "This is a bvvv", ""),
			Component("textField", "This is a 222", ""),
			Component("list", "This is a lisee2e2t", ""),

			))
		return layout
	}


	data class Component(val variant: String, val data: String?, val action: String?)
	data class Layout(val pageID: Int, val components: List<Component>)


}



data class Button(val id : String, val variant: String, val itemDataModel: String )

@RestController
class LayoutTest02Resource {
	@GetMapping(value = ["/LayoutTest02"])
	fun sampleLayout(): String {
		var hard = "{\n" +
				"    \"pageID\": \"1\",\n" +
				"    \"expiry\": \"6000\",\n" +
				"    \"components\": [\n" +
				"        {\n" +
				"            \"id\":\"???\",\n" +
				"            \"variant\": \"label\",\n" +
				"            \"expiry\": \"6000\",\n" +
				"            \"itemDataModel\":\n" +
				"            {\n" +
				"                \"text\": \"label\",\n" +
				"                \"color\": \"green\",\n" +
				"\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\":\"???\",\n" +
				"            \"variant\": \"button\",\n" +
				"            \"expiry\": \"6000\",\n" +
				"            \"itemDataModel\":\n" +
				"            {\n" +
				"                \"text\": \"tap me\",\n" +
				"                \"color\": \"green\",\n" +
				"                \"action\": \"1\"\n" +
				"\n" +
				"            }\n" +
				"        }\n" +
				"    ]\n" +
				"    \n" +
				"}"

		return hard
	}
}


@RestController
class EchoPayloadResource {
	@PostMapping(value = ["/EchoPayload"])
	fun echo(@RequestBody payload: String): String{
		return payload
	}
}

