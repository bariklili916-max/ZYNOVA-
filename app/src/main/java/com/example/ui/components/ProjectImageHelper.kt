package com.example.ui.components

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import java.io.File
import java.io.FileOutputStream

object ProjectImageHelper {
    fun getDrawableForProject(imageName: String): Int {
        return when (imageName) {
            "proj_water_filter" -> R.drawable.proj_water_filter
            "proj_periscope" -> R.drawable.proj_periscope
            "proj_solar_cooker" -> R.drawable.proj_solar_cooker
            "proj_digestive_model" -> R.drawable.proj_digestive_model
            "proj_electric_circuit" -> R.drawable.proj_electric_circuit
            else -> R.drawable.proj_water_filter
        }
    }

    fun saveImageToInternalStorage(context: Context, uri: Uri): String {
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val fileName = "custom_project_img_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, fileName)
            val outputStream = FileOutputStream(file)
            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }
            file.absolutePath
        } catch (e: Exception) {
            e.printStackTrace()
            uri.toString()
        }
    }
}

@Composable
fun ProjectImage(
    imageSource: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    if (imageSource.startsWith("/") ||
        imageSource.startsWith("content://") ||
        imageSource.startsWith("file://") ||
        imageSource.startsWith("http://") ||
        imageSource.startsWith("https://")
    ) {
        val model = if (imageSource.startsWith("/")) File(imageSource) else imageSource
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(model)
                .crossfade(true)
                .error(R.drawable.proj_water_filter)
                .placeholder(R.drawable.proj_water_filter)
                .build(),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    } else {
        Image(
            painter = painterResource(id = ProjectImageHelper.getDrawableForProject(imageSource)),
            contentDescription = contentDescription,
            modifier = modifier,
            contentScale = contentScale
        )
    }
}

