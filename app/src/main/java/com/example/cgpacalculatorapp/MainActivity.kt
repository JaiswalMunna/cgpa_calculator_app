package com.example.cgpacalculatorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpacalculatorapp.ui.theme.CgpaCalculatorAppTheme

data class Semester(val grade: String,val credit: Int)

class MainActivity : ComponentActivity() {
    private var semesters:MutableList<Semester> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            CgpaCalculatorAppTheme {
                CGPA(semesters)
            }
        }
    }
}

@Composable
fun CGPA(semesters: MutableList<Semester>){

val scrollState= rememberScrollState()

    var grade1 by remember { mutableStateOf("") }
    var credit1 by remember { mutableStateOf<Int?>(null) }
    var grade2 by remember { mutableStateOf("") }
    var credit2 by remember { mutableStateOf<Int?>(null) }
    var grade3 by remember { mutableStateOf("") }
    var credit3 by remember { mutableStateOf<Int?>(null) }
    var grade4 by remember { mutableStateOf("") }
    var credit4 by remember { mutableStateOf<Int?>(null) }
    var grade5 by remember { mutableStateOf("") }
    var credit5 by remember { mutableStateOf<Int?>(null) }
    var grade6 by remember { mutableStateOf("") }
    var credit6 by remember { mutableStateOf<Int?>(null) }
    var cgpa by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .verticalScroll(scrollState)
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
        subjectText(Subject = "Subject 1")
        subjectGrade(grade1) {grade1=it}
        subjectCreditPoint(credit1) {credit1=it}

        subjectText(Subject = "Subject 2")
        subjectGrade(grade2) {grade2=it}
        subjectCreditPoint(credit2) {credit2=it}

        subjectText(Subject = "Subject 3")
        subjectGrade(grade3) {grade3=it}
        subjectCreditPoint(credit3) {credit3=it}

        subjectText(Subject = "Subject 4")
        subjectGrade(grade4) {grade4=it}
        subjectCreditPoint(credit4) {credit4=it}

        subjectText(Subject = "Subject 5")
        subjectGrade(grade5) {grade5=it}
        subjectCreditPoint(credit5) {credit5=it}

        subjectText(Subject = "Subject 6")
        subjectGrade(grade6) {grade6=it}
        subjectCreditPoint(credit6) {credit6=it}

        Row() {
           Column(
               modifier = Modifier.fillMaxHeight(),
               verticalArrangement = Arrangement.SpaceBetween
           ) {
               Button(onClick = {
                val semester=Semester(grade1,credit1?:0)
                   semesters.add(semester)
                   val totalCredit=semesters.sumOf{it.credit}
                   val totalGrade=semesters.sumOf{calculateGradePoint(it.grade,it.credit)}
                   if(totalCredit>0){
                       cgpa=totalGrade/totalCredit.toDouble()
                   }
                   else{
                       cgpa=0.0
                   }

                   grade1=""
                   credit1=null
                   grade2=""
                   credit2=null
                   grade3=""
                   credit3=null
                   grade4=""
                   credit4=null
                   grade5=""
                   credit5=null
                   grade6=""
                   credit6=null
               }, colors = ButtonDefaults.buttonColors(
                   Color(0xFF7E57C2)), shape = RoundedCornerShape(15.dp)
               )
               {
                   Text(text = "Calculate CGPA", color = Color.White)
               }
               Spacer(modifier = Modifier.padding(10.dp))
               Surface(
                   modifier = Modifier
                       .width(150.dp)
                       .wrapContentHeight(),
                   color = Color(0xFF263238),
                   shape = RoundedCornerShape(15.dp)
               )
               {
                   Text(
                       modifier = Modifier.padding(start = 10.dp),
                       text = "Your all time\n CGPA: $cgpa",
                       style = TextStyle(
                           fontFamily = FontFamily(Font(R.font.poppins_medium)),
                           fontSize = 16.sp,
                           color = Color.White
                       )
                   )
               }

           }
           // Spacer(modifier = Modifier.padding(start = 10.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp)
                        .height(200.dp),
                    color = Color(0xFF263238),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Column() {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            text = "Previouse Semester:",
                            style = TextStyle(
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        )
                        if(semesters.isNotEmpty()){
                            for ( semester in semesters){
                                Text(
                                    text = "Grade:${semester.grade} \n Credit:${semester.credit} ",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                                        fontSize = 16.sp,
                                    ),
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                )
                            }

                        }

                    }

                }

        }

//        subjectText(Subject = "Subject 1")
//        subjectInput(
//            input = "Enter your grade",
//            label = "Enter Grade",
//            onValueChange = {}
//            )
//        subjectInput(
//            input = 3.0,  // Float input for credit points
//            label = "Enter Credit Points",
//            onValueChange = {}
//        )
    }
}

fun calculateGradePoint(grade: String, credit: Int):Double {
return when (grade.uppercase()){
     "A"->4.0
     "B"->3.2
     "C"->2.6
     "D"->1.8
     else->0.0
 } * credit
}

//@Composable
//fun Spacer8dp(){
//    Spacer(modifier = Modifier.padding(top = 10.dp))
//}

//@Preview(showBackground = true)
//@Composable
//fun CGPAPreview(){
//    CgpaCalculatorAppTheme {
//      CGPA()
//    }
//}

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
fun subjectGrade(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade,
        onValueChange = { text -> onValueChange(text) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        label = { Text(text = "Enter grade", color = Color.White) },
        colors = TextFieldDefaults.textFieldColors(
            //containerColor = Color.Transparent, // new color scheme for the container background
            containerColor=Color(0xFF7E57C2),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White, // Label color when focused
            unfocusedLabelColor = Color.LightGray // Label color when unfocused

        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
    )
    Spacer(modifier = Modifier.padding(10.dp))
}

//Subject Name
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun subjectCreditPoint(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString()?:"",
        onValueChange = { text -> onValueChange(text.toIntOrNull()) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        label = { Text(text = "Enter Credit Points", color = Color.Black, fontFamily = FontFamily(Font(R.font.poppins_semibold))) },
        colors = TextFieldDefaults.textFieldColors(
            //containerColor = Color.Transparent, // new color scheme for the container background
            containerColor=Color(0xFF7D8CCED),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White, // Label color when focused
            unfocusedLabelColor = Color.LightGray // Label color when unfocused

        ), shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black, fontFamily = FontFamily(Font(R.font.poppins_semibold)))
    )
    Spacer(modifier = Modifier.padding(10.dp))
}

//combination on above two function
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun subjectInput(
//    input: Any,            // Accepts both String and Float types
//    label: String,         // Label for the TextField
//    onValueChange: (String) -> Unit  // Callback for input change
//) {
//    val textValue = when (input) {
//        is String -> input  // Handle String input
//        is Float -> input.toString()  // Handle Float input
//        else -> throw IllegalArgumentException("Unsupported data type")
//    }
//
//    val textStyle = when (input) {
//        is String -> TextStyle(fontSize = 12.sp, color = Color.White)
//        is Float -> TextStyle(fontSize = 12.sp, color = Color.Black, fontFamily = FontFamily(Font(R.font.poppins_semibold)))
//        else -> TextStyle.Default
//    }
//
//    val backgroundColor = when (input) {
//        is String -> Color(0xFF7E57C2)  // Background for grade input
//        is Float -> Color(0xFF7D8CCED)  // Background for credit points input
//        else -> Color.Transparent
//    }
//
//    val labelColor = when (input) {
//        is String -> Color.White  // Label color for grade
//        is Float -> Color.Black    // Label color for credit points
//        else -> Color.LightGray
//    }
//
//    TextField(
//        value = textValue,
//        onValueChange = { text -> onValueChange(text) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(60.dp),
//        label = { Text(text = label, color = labelColor) },
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = backgroundColor,
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            focusedLabelColor = labelColor,
//            unfocusedLabelColor = Color.LightGray
//        ),
//        shape = RoundedCornerShape(15.dp),
//        textStyle = textStyle,
//
//    )
//    Spacer(modifier = Modifier.padding(8.dp))
//}





//package com.example.cgpacalculatorapp
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.material3.TextFieldDefaults
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.cgpacalculatorapp.ui.theme.CgpaCalculatorAppTheme
//
//data class Semester(val grade: String, val credit: Int)
//
//class MainActivity : ComponentActivity() {
//    private var semester: MutableList<Semester> = mutableListOf()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            CgpaCalculatorAppTheme {
//                CGPA(semester)
//            }
//        }
//    }
//}
//
//@Composable
//fun CGPA(semester: MutableList<Semester>) {
//    val scrollState = rememberScrollState()
//
//    var grade1 by remember { mutableStateOf("") }
//    var credit1 by remember { mutableStateOf<Int?>(null) }
//    var cgpa by remember { mutableStateOf(0.0) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 10.dp, vertical = 10.dp)
//            .verticalScroll(scrollState)
//    ) {
//        Text(
//            text = "Hi, this is your app which will calculate your grade into CGPA",
//            modifier = Modifier
//                .fillMaxWidth()
//                .align(Alignment.CenterHorizontally),
//            style = TextStyle(
//                textAlign = TextAlign.Center,
//                fontSize = 25.sp,
//                color = Color.DarkGray,
//                fontFamily = FontFamily(Font(R.font.poppins_semibold))
//            )
//        )
//
//        subjectText(Subject = "Subject 1")
//        subjectGrade(grade1) { grade1 = it }
//        subjectCreditPoint(credit1) { credit1 = it }
//
//        Row {
//            Column(
//                modifier = Modifier.fillMaxHeight(),
//                verticalArrangement = Arrangement.SpaceBetween
//            ) {
//                Button(
//                    onClick = {
//                        val newSemester = Semester(grade1, credit1 ?: 0)
//                        semester.add(newSemester)
//
//                        val totalCredit = semester.sumOf { it.credit }
//                        val totalGrade = semester.sumOf { calculateGradePoint(it.grade, it.credit) }
//
//                        cgpa = if (totalCredit > 0) {
//                            totalGrade / totalCredit.toDouble()
//                        } else {
//                            0.0
//                        }
//
//                        grade1 = ""
//                        credit1 = null
//                    },
//                    colors = ButtonDefaults.buttonColors(Color(0xFF7E57C2))
//                ) {
//                    Text(text = "Calculate CGPA", color = Color.White)
//                }
//
//                Spacer(modifier = Modifier.padding(10.dp))
//
//                Surface(
//                    modifier = Modifier
//                        .width(150.dp)
//                        .wrapContentHeight(),
//                    color = Color(0xFF263238)
//                ) {
//                    Text(
//                        modifier = Modifier.padding(start = 10.dp),
//                        text = "Your CGPA: $cgpa",
//                        style = TextStyle(
//                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                            fontSize = 16.sp,
//                            color = Color.White
//                        )
//                    )
//                }
//            }
//        }
//
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(start = 10.dp)
//                .height(200.dp),
//            color = Color(0xFF263238)
//        ) {
//            Column {
//                Text(
//                    modifier = Modifier.fillMaxWidth(),
//                    textAlign = TextAlign.Center,
//                    text = "Previous Semesters:",
//                    style = TextStyle(
//                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
//                        fontSize = 16.sp,
//                        color = Color.White
//                    )
//                )
//
//                if (semester.isNotEmpty()) {
//                    for (s in semester) {
//                        Text(
//                            text = "Grade: ${s.grade} \n Credit: ${s.credit}",
//                            style = TextStyle(
//                                color = Color.White,
//                                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
//                                fontSize = 16.sp,
//                            ),
//                            modifier = Modifier.fillMaxWidth(),
//                            textAlign = TextAlign.Center,
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//fun calculateGradePoint(grade: String, credit: Int): Double {
//    return when (grade.uppercase()) {
//        "A" -> 4.0
//        "B" -> 3.2
//        "C" -> 2.6
//        "D" -> 1.8
//        else -> 0.0
//    } * credit
//}
//
//@Composable
//fun subjectText(Subject: String) {
//    Text(
//        text = Subject,
//        modifier = Modifier.fillMaxWidth(),
//        style = TextStyle(
//            fontSize = 18.sp,
//            color = Color.DarkGray,
//            fontFamily = FontFamily(Font(R.font.poppins_medium))
//        )
//    )
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun subjectGrade(grade: String, onValueChange: (String) -> Unit) {
//    TextField(
//        value = grade,
//        onValueChange = { text -> onValueChange(text) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(60.dp),
//        label = { Text(text = "Enter grade", color = Color.White) },
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color(0xFF7E57C2),
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            focusedLabelColor = Color.White,
//            unfocusedLabelColor = Color.LightGray
//        ),
//        shape = RoundedCornerShape(15.dp),
//        textStyle = TextStyle(fontSize = 12.sp, color = Color.White)
//    )
//    Spacer(modifier = Modifier.padding(10.dp))
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun subjectCreditPoint(credit: Int?, onValueChange: (Int?) -> Unit) {
//    TextField(
//        value = credit?.toString() ?: "",
//        onValueChange = { text -> onValueChange(text.toIntOrNull()) },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(60.dp),
//        label = { Text(text = "Enter Credit Points", color = Color.Black) },
//        colors = TextFieldDefaults.textFieldColors(
//            containerColor = Color(0xFF7D8CCED),
//            focusedIndicatorColor = Color.Transparent,
//            unfocusedIndicatorColor = Color.Transparent,
//            focusedLabelColor = Color.Black,
//            unfocusedLabelColor = Color.Black
//        ),
//        shape = RoundedCornerShape(15.dp),
//        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black)
//    )
//    Spacer(modifier = Modifier.padding(10.dp))
//}
