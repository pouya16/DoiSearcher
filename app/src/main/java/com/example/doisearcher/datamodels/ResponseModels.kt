package com.example.doisearcher.datamodels

data class ResponseModel(
    val data: List<DataClass>
)

data class DataClass(
    val id: String,
    val attributes: AttributeClass,
    val relationships: Relationships
)

data class AttributeClass(
    val language:String,
    val url:String,
    val descriptions: List<Descriptions>
)

data class Descriptions(
    val description:String
)


data class Client(
    val data:DataClient
)

data class DataClient(
    val id:String
)


data class Relationships(
    val client: Client
)


data class Item(
    val name:String,
    val count:Int
)
