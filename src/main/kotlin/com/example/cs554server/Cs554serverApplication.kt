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
		var layout = Layout("1", "6000", listOf(
			Component("3265", "label", "6000",
				ItemDataModel("this is a label","red","","","","",
					listOf("payload1","payload2"))
			),
			Component("5165", "label", "6000",
				ItemDataModel("this is another label","green","","","","",
					listOf())
			),
			Component("4765", "button", "6000",
				ItemDataModel("tap me","red","","","","action1",
					listOf("payload1","payload2"))
			),
			Component("1239", "list", "6000",
				ItemDataModel("","red","","","","",
					listOf("item1","item2"))
			),
			Component("6645", "icon", "6000",
				ItemDataModel("sun rise","","","","sunrise","",
					listOf())
			)
		))


		return layout
	}


	data class Component(val id: String, val variant: String, val expiry: String, val itemDataModel: ItemDataModel)
	data class Layout(val pageID: String, val expiry: String, val components: List<Component>)
	data class ItemDataModel(val text: String, val color:String, val itemName: String, val imageURL:String,
							 val iconKey: String, val action: String, val payload: List<String>)

}



data class Button(val id : String, val variant: String, val itemDataModel: String )

@RestController
class LayoutTest02Resource {
	@GetMapping(value = ["/LayoutTest02"])
	fun sampleLayout(): String {
		var hard = "{\n" +
				"    \"pageID\": \"123\",\n" +
				"    \"expiry\": \"6000\",\n" +
				"    \"components\": [\n" +
				"        {\n" +
				"            \"id\":\"689\",\n" +
				"            \"variant\": \"label\",\n" +
				"            \"expiry\": \"6000\",\n" +
				"            \"itemDataModel\":\n" +
				"            {\n" +
				"                \"text\": \"label\",\n" +
				"                \"color\": \"green\"\n" +
				"\n" +
				"            }\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\":\"231\",\n" +
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

