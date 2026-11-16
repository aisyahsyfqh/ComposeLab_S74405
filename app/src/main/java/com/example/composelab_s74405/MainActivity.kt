package com.example.composelab_s74405

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Scaffold { innerPadding ->
                    BoxChallenge(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// EXERCISE 7 - REGISTER SCREEN

@Composable
fun RegisterScreen(modifier: Modifier = Modifier) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Create Account",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = {
                    name = ""
                    email = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancel")
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}

// EXERCISE 7 - STUDENT LIST

data class Student(
    val name: String,
    val score: Int
)

@Composable
fun StudentList(modifier: Modifier = Modifier) {

    var grid by rememberSaveable { mutableStateOf(false) }

    val students = remember {
        List(50) {
            Student("Student ${it + 1}", 50 + it)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = { grid = !grid },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (grid) "Switch to List" else "Switch to Grid")
        }

        if (grid) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.weight(1f)
            ) {
                items(students) { student ->
                    StudentCard(student)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(students) { student ->
                    StudentCard(student)
                }
            }
        }
    }
}

@Composable
fun StudentCard(student: Student) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(
                Color(0xFFEEEEEE),
                RoundedCornerShape(8.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = student.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = "Marks: ${student.score}")
    }
}

// EXERCISE 7 - BOX CHALLENGE

@Composable
fun BoxChallenge(modifier: Modifier = Modifier) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Blue background box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color(0xFF1565C0))
        )

        // Orange box in the top center
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 70.dp)
                .size(160.dp)
                .background(Color(0xFFEF6C00))
                .zIndex(1f)
        )

        // Red box in the top right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(80.dp)
                .background(Color(0xFFC62828))
                .zIndex(2f)
        )
    }
}
