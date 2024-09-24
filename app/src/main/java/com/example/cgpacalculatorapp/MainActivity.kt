package com.example.cgpacalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculatorapp.ui.theme.CgpaCalculatorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CgpaCalculatorAppTheme {
                CGPA()
            }
        }
    }
}

@Composable
fun CGPA(){

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Hi, this is your app which will calculate your grade into cgpa",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            style = TextStyle(
                textAlign = TextAlign.Center,
                fontSize =25.sp,
                color = Color.DarkGray,
                fontFamily = FontFamily(Font(R.font.poppins_semibold))
            )
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))

        subjectText(Subject = "Subject 1")

        Spacer(modifier = Modifier.padding(top = 10.dp))

        subjectNameText(grade = "Enter your grade", onValueChange = {})

    }
}


@Preview(showBackground = true)
@Composable
fun CGPAPreview(){
    CgpaCalculatorAppTheme {
      CGPA()
    }
}

//Subject Number
@Composable
fun subjectText( Subject:String){
    Text(
        text = Subject,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
           // textAlign = TextAlign.Right,
            fontSize =18.sp,
            color = Color.DarkGray,
            fontFamily = FontFamily(Font(R.font.poppins_medium))
        )
    )
}

//Subject Name
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun subjectNameText(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade,
        onValueChange = { text -> onValueChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        label = { Text(text = "Enter grade", color = Color.White) },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent, // new color scheme for the container background
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White, // Label color when focused
            unfocusedLabelColor = Color.LightGray // Label color when unfocused
        ),
    )
}




//Subject Grade

//@Composable
//fun textField(){
//    var valueOfField by remember {
//        mutableStateOf(TextFieldValue(""))
//    }
//    Box (contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ){
//        TextField(value =valueOfField, onValueChange = {
//            valueOfField=it
//        },
//            label = {Text(text = "Name:")} ,
//            placeholder = { Text(text = "Enter your name:")},//placeholder
//            leadingIcon = { Icon(Icons.Default.Person,contentDescription=null) },//icon in starting
//            trailingIcon = { Icon(Icons.Default.Info, contentDescription = null) }, //icon in ending
//            singleLine = true, //the text will contain single line
//        )
//    }
//}

