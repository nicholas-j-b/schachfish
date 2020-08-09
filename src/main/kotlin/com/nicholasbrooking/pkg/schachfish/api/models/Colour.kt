/**
* Schachfish-Api
* Schachfish is a chess engine for querying moves and board evaluations
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.nicholasbrooking.pkg.schachfish.api.models


import com.squareup.moshi.Json

/**
* 
* Values: black,white
*/

enum class Colour(val value: kotlin.String){


    @Json(name = "black")
    black("black"),


    @Json(name = "white")
    white("white");



	/**
	This override toString avoids using the enum var name and uses the actual api value instead.
	In cases the var name and value are different, the client would send incorrect enums to the server.
	**/
	override fun toString(): String {
        return value
    }

}
