package de.dhbw.ex.locationdirection.util

import android.content.Context
import de.dhbw.ex.locationdirection.model.LocationModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.*
import kotlin.collections.ArrayList

class LocationModelPersistence {

    companion object {
        private val file_name = "locations.json"

        private fun JSONArray.toList(): ArrayList<JSONObject> {
            val result = ArrayList<JSONObject>()
            for (i in 0 until this.length()) {
                result.add(this.getJSONObject(i))
            }
            return result
        }

        fun loadAllLocations(context: Context): ArrayList<LocationModel> {
            // TODO implement method
            // load all locations from a file, called "locations.json"
            return ArrayList()
        }

        fun saveLocationModel(context: Context, locationModel: LocationModel) {
            // TODO implement method
            // save all locations in a file, called "locations.json"
            // use the FileStorage.writeText()-method
        }

        fun loadLocationModel(context: Context, id: UUID): LocationModel? {
            return loadAllLocations(context).find { it.id?.equals(id) ?: false }
        }

        fun loadLocationModel(context: Context, id: String?): LocationModel? {
            id?.let {
                return loadLocationModel(context, UUID.fromString(id))
            }
            return null
        }

    }
}