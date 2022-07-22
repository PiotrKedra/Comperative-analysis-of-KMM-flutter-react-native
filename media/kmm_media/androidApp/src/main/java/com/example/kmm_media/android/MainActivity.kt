package com.example.kmm_media.android

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.VideoCapture
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.kmm_media.Greeting
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors

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
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED -> {
                println("PERMISSION PREVIOUSLY GRANTED")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.RECORD_AUDIO
            ) -> println("SHOW CAMERA PERMISSION DIALOG")

            else -> requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    private fun getOutputDirectory(): File {
        return filesDir
    }
}

@SuppressLint("RestrictedApi")
@Composable
fun Main(outputDirectory: File) {
    val imageCapture by remember { mutableStateOf(ImageCapture.Builder().build()) }
    val videoCapture by remember { mutableStateOf(VideoCapture.Builder().build()) }

    val photo = remember { mutableStateOf("") }
    val isRecording = remember { mutableStateOf(false) }

    val playVideo = remember { mutableStateOf("") }

    val context = LocalContext.current

    fun handleImageCapture(uri: Uri) {
        println("IMAGE: $uri")
        photo.value = uri.toString()
    }

    @SuppressLint("RestrictedApi")
    fun startRecording() {
        val videoFile = File(
            outputDirectory,
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS", Locale.US
            ).format(System.currentTimeMillis()) + ".mp4"
        )
        val outputOptions = VideoCapture.OutputFileOptions.Builder(videoFile).build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("RETURNING")
            return
        }
        println("VIDEO STATR RECORDING")
        videoCapture.startRecording(
            outputOptions, Executors.newSingleThreadExecutor(),
            object : VideoCapture.OnVideoSavedCallback {
                override fun onError(videoCaptureError: Int, message: String, cause: Throwable?) {
                    println("VIDEO RECORD FAILED: $message")
                }

                override fun onVideoSaved(outputFileResults: VideoCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(videoFile)
                    val msg = "Video capture succeeded: $savedUri"
                    println(msg)
                    playVideo.value = savedUri.toString()
                }
            }
        )
    }

    @SuppressLint("RestrictedApi")
    fun stopRecording() {
        videoCapture.stopRecording()
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
        Button(onClick = {
            if (!isRecording.value) {
                isRecording.value = true
                startRecording()
            } else {
                isRecording.value = false
                stopRecording()
            }
        }) {
            if (!isRecording.value) {
                Text("Record video")
            } else {
                Text("Stop recording")
            }
        }
        if (photo.value == "" && playVideo.value == "") {
            CameraPreview(modifier = Modifier.fillMaxSize(), imageCapture = imageCapture, videoCapture = videoCapture)
        } else if (photo.value != "") {
            Text(text = photo.value)
            Image(
                painter = rememberImagePainter(Uri.parse(photo.value)),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        } else if (playVideo.value != "") {
            VideoPlayer(uri = playVideo.value)
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
