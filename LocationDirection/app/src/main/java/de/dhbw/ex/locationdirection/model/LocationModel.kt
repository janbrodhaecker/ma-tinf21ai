package de.dhbw.ex.locationdirection.model

import android.location.Location
import android.location.LocationManager
import com.google.android.gms.maps.model.LatLng
import org.json.JSONObject
import java.time.LocalDate
import java.util.*

data class LocationModel(
    var id: UUID? = UUID.randomUUID(),
    var title: String? = null,
    var imageUrl: String? = null,
    var location: LatLng? = null,
    var updatedAt: LocalDate? = null
) {

    companion object {

        private fun buildLatLng(jsonObject: JSONObject): LatLng? {
            // TODO convert lat/lng doubles to typed LatLng
            return null
        }

        private fun buildDate(jsonObject: JSONObject): LocalDate? {
            // TODO convert date string to typed LocalDate
            return null
        }
    }

    // TODO create LocationModel from json
    constructor(jsonObject: JSONObject) : this()

    fun updateTitle(title: String): LocationModel {
        return LocationModel(id, title, imageUrl, location, updatedAt)
    }

    fun updateLocation(location: LatLng): LocationModel {
        return LocationModel(id, title, imageUrl, location, updatedAt)
    }

    fun updateImageUrl(imageUrl: String): LocationModel {
        return LocationModel(id, title, imageUrl, location, updatedAt);
    }

    fun getLocation(): Location? {
        // TODO convert LatLng to Location
        return null
    }

    fun locationText(): String? {
        // TODO convert LatLng to Coordinates String
        return null
    }

    fun toJsonObject(): JSONObject {
        // TODO write all properties into JSONObject
        return JSONObject()
    }
}