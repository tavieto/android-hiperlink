package com.tavieto.textsections

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.tavieto.textsections.textsection.Section
import com.tavieto.textsections.textsection.SectionType
import com.tavieto.textsections.textsection.TextSection
import com.tavieto.textsections.ui.theme.TextSectionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            var counter by remember { mutableStateOf(0) }
            LaunchedEffect(counter) {
                Toast.makeText(context, "Click $counter", Toast.LENGTH_SHORT).show()
            }

            TextSectionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        TextSection(
                            text = "Texto com {#0/Hiperlink#} no {#0/Android#}",
                            modifier = Modifier.align(Alignment.Center),
                            sections = listOf(
                                Section(
                                    type = SectionType.Link { counter++ },
                                    color = Color.Blue,
                                    style = TextStyle(
                                        fontSize = 24.sp,
                                        textDecoration = TextDecoration.Underline
                                    )
                                ),
                                Section(
                                    type = SectionType.Bold,
                                    color = Color.Green,
                                    style = TextStyle(
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            )
                        )
                    }
                }
            }
        }
    }
}
