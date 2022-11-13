package de.dhbw.ex.locationdirection.util

import android.content.Context
import android.graphics.Bitmap
import de.dhbw.ex.locationdirection.model.LocationModel
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

class FileStorage {

    companion object {

        fun loadFile(context: Context, fileName: String, create: Boolean = false): File {
            // TODO implement load file method
            // - with the given file name
            // - and create the file if it does not exist and the create-flag is true
            throw NotImplementedError("not yet implemented")
        }

        fun writeText(context: Context, fileName: String, content: String) {
            // TODO implement method
            // - with the given string file content
            throw NotImplementedError("not yet implemented")
        }

        fun writeImage(context: Context, bitmap: Bitmap, fileName: String): String {
            // TODO implement method
            // - write bitmap into the given file with the given name
            // - return the absolute path of the file
            return ""
        }

    }
}