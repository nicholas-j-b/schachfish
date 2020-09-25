/**
* Schachfish-Api
* Schachfish is a chess engine for making/querying moves and board evaluations
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.nicholasbrooking.pkg.schachfish.api.models

import com.nicholasbrooking.pkg.schachfish.api.models.PieceName
import com.nicholasbrooking.pkg.schachfish.api.models.PositionDto

import com.squareup.moshi.Json
/**
 * 
 * @param position 
 * @param name 
 */

data class PieceDto (
    @Json(name = "position")
    val position: PositionDto? = null,
    @Json(name = "name")
    val name: PieceName? = null
)

