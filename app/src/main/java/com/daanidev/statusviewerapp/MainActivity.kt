package com.daanidev.statusviewerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.daanidev.statusviewer.StatusViewer
import com.daanidev.statusviewerapp.ui.theme.StatusViewerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        val list = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )
        setContent {
            StatusViewerAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StatusViewer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(innerPadding),
                        imagesList = list,
                        progressColor = Color.Red
                    )
                }
            }
        }
    }
}
