package com.example.cs554server

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.random.Random


@SpringBootApplication
class Cs554serverApplication


data class Component(val id: String, val variant: String, val expiry: String, val itemDataModel: ItemDataModel)
data class Layout(val pageID: String, val expiry: String, val components: List<Component>)
data class ItemDataModel(val id: String, var text: String, val color:String, val itemName: String, val imageURL:String,
						 val iconKey: String, val action: String, val payload: List<String>)
data class DataModelUpdateRequest(val pageID: String, val id: String)


var layouts = listOf<Layout>()
val json_dict: MutableMap<String, String> = mutableMapOf()
val layouts_json: MutableList<String> = mutableListOf()
var page01 = Layout("1", "6000", listOf(
	Component("3265", "label", "6000",
		ItemDataModel("3244","this is a label","red","","","","",
			listOf("payload1","payload2"))
	),
	Component("5165", "label", "6000",
		ItemDataModel("4243","this is another label","green","","","","",
			listOf())
	),
	Component("4765", "button", "6000",
		ItemDataModel("5249","tap me","red","","","","action1",
			listOf())
	),
	Component("4327", "button", "6000",
		ItemDataModel("3876","tap me","blue","","","","action2",
			listOf())
	),
	Component("1239", "list", "6000",
		ItemDataModel("7654","","red","","","","",
			listOf("item1","item2"))
	),
	Component("6645", "icon", "6000",
		ItemDataModel("9344","sun rise","","","","sunrise","",
			listOf())
	)
))

fun main(args: Array<String>) {
	json_dict.put("1", page01.toString())
	runApplication<Cs554serverApplication>(*args)

}


@RestController
class LayoutTest01Resource {
	@GetMapping(value = ["/LayoutTest01"])
	fun sampleLayout(): Layout {
		return page01
	}
}





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
				"				 \"id\":\"449\",\n" +
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
				"				 \"id\":\"149\",\n" +
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


@RestController
class AvailableResouseGetter {
	@GetMapping(value = ["/AvailableResouses"])
	fun sampleLayout(): String {
		return json_dict.keys.toString()
	}
}

@RestController
class LayoutUpload {
	@PostMapping(value = ["/LayoutUpload"])
	fun echo(@RequestBody payload: String): String{
		var i = Random.nextInt(0, 9999)
		while (json_dict.containsKey(""+i))
			i = Random.nextInt(0, 9999)
		var mappingEndpoint: String = "/LayoutResourse/"+ i
		json_dict.put(""+i, payload)
		return mappingEndpoint
	}
}

@RestController
class LayoutResourceGetter {
	@GetMapping(value = ["/LayoutResourse/{requested_resource}"])
	fun sampleLayout(@PathVariable(value="requested_resource") k: String): String {
		if (json_dict.containsKey(k))
			return json_dict.get(k).toString()
		else
			return "{\n" +
					"    \"pageID\": \"0000\",\n" +
					"    \"expiry\": \"6000\",\n" +
					"    \"components\": [\n" +
					"    ]\n" +
					"    \n" +
					"}"
	}
}

@RestController
class ItemDataModelUpdate {
	@PostMapping(value = ["/DataModelUpdate"])
	fun echo(@RequestBody payload: String): Layout{
		val jrequest = Json.decodeFromString<DataModelUpdateRequest>(payload)
		if(json_dict.containsKey(jrequest.pageID)) {
			var updated = Json.decodeFromString<Layout>(json_dict[jrequest.pageID].toString())
			for (c in updated.components)
				if (c.itemDataModel.id == jrequest.id)
					c.itemDataModel.text += " updated"
			return updated
		} else
			return page01
	}
}