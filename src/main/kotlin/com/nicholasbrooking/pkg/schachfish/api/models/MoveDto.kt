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

import com.nicholasbrooking.pkg.schachfish.api.models.PieceName
import com.nicholasbrooking.pkg.schachfish.api.models.PositionDto

import com.squareup.moshi.Json
/**
 * 
 * @param to 
 * @param from 
 * @param takenPiece 
 * @param promoteTo 
 */

data class MoveDto (
    @Json(name = "to")
    val to: PositionDto? = null,
    @Json(name = "from")
    val from: PositionDto? = null,
    @Json(name = "takenPiece")
    val takenPiece: PositionDto? = null,
    @Json(name = "promoteTo")
    val promoteTo: PieceName? = null
)

