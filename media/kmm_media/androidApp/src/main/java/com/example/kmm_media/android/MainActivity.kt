package com.example.kmm_media.android

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.kmm_media.Greeting
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            println("GRANTED")
        } else {
            println("NOT GRANTED")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Main(getOutputDirectory())
        }

        requestCameraPermission()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                println("PERMISSION PREVIOUSLY GRANTED")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> println("SHOW CAMERA PERMISSION DIALOG")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun getOutputDirectory(): File {
        return filesDir
    }
}

@Composable
fun Main(outputDirectory: File) {
    val imageCapture by remember { mutableStateOf(ImageCapture.Builder().build()) }

    val photo = remember { mutableStateOf("") }


    fun handleImageCapture(uri: Uri) {
        println("IMAGE: $uri")
        photo.value = uri.toString()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            takePhoto(
                imageCapture = imageCapture,
                onImageCaptured = { uri -> handleImageCapture(uri) },
                outputDirectory = outputDirectory
            )
        }) {
            Text("Take photo")
        }
        if (photo.value == "") {
            CameraPreview(modifier = Modifier.fillMaxSize(), imageCapture = imageCapture)
        } else {
            Text(text = photo.value)
            Image(
                painter = rememberImagePainter(Uri.parse(photo.value)),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

fun takePhoto(imageCapture: ImageCapture, onImageCaptured: (Uri) -> Unit, outputDirectory: File) {

    val photoFile = File(
        outputDirectory,
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions, Executors.newSingleThreadExecutor(),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                println("PHOTO TAKEN")
                println(exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                onImageCaptured(savedUri)
            }
        }
    )
}
