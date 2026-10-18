package com.ktp.questtugasuserinput

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputData(modifier: Modifier = Modifier, onDateSelected: (Long?) -> Unit = {}, onDismiss: () -> Unit = {}) {
    //Variable untuk menyimpan apa yang diketik oleh KeyBoard
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateText by remember { mutableStateOf("Tgl Lahir") }
    var expanded by remember { mutableStateOf(false) }
    var selectedExperience by remember { mutableStateOf("Experience") }
    var checkedAndroid by remember { mutableStateOf(false) }
    var checkedUiUx by remember { mutableStateOf(false) }
    var checkedWeb by remember { mutableStateOf(false) }
    val experienceOptions = listOf("Beginner (0-1 Tahun)", "Intermediate (1-3 Tahun)", "Expert (>3 Tahun)")
    val datePickerState = rememberDatePickerState()

    //Variable untuk menyimpan data hasil input dari komponen UI
    var hasilNamaDepan by remember { mutableStateOf("") }
    var hasilNamaBelakang by remember { mutableStateOf("") }
    var hasilEmail by remember { mutableStateOf("") }
    var hasilTglLahir by remember { mutableStateOf("") }
    var hasilExperience by remember { mutableStateOf("") }
    var hasilSkills by remember { mutableStateOf(listOf<String>()) }
    var showResultCard by remember { mutableStateOf(false) }

    // Custom monochrome textField colors dengan font text warna hitam
    val customTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        disabledTextColor = Color.Black,
        focusedBorderColor = Color.Black,
        unfocusedBorderColor = Color.Gray,
        disabledBorderColor = Color.Gray,
        focusedLabelColor = Color.Black,
        unfocusedLabelColor = Color.Gray,
        disabledLabelColor = Color.Gray,
        cursorColor = Color.Black
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(bottomStart = 25.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gamabarbackground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topEnd = 25.dp))
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Title & Subtitle agar lebih mudah dibaca
                Text(
                    text = "Welcome",
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "You Can Start With Register",
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(20.dp))

                // 2 & 3. Input fields tersusun rapi dengan text warna hitam
                Row {
                    OutlinedTextField(
                        value = namaDepan,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(152.dp),
                        label = { Text(text = "Nama Depan") },
                        onValueChange = { namaDepan = it },
                        colors = customTextFieldColors
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    OutlinedTextField(
                        value = namaBelakang,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(152.dp),
                        label = { Text(text = "Nama Belakang") },
                        onValueChange = { namaBelakang = it },
                        colors = customTextFieldColors
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier.width(310.dp),
                    label = { Text(text = "Email") },
                    onValueChange = { email = it },
                    colors = customTextFieldColors
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row {
                    Box {
                        OutlinedTextField(
                            value = selectedDateText,
                            onValueChange = { },
                            readOnly = true,
                            enabled = false,
                            modifier = Modifier.width(152.dp),
                            shape = MaterialTheme.shapes.extraLarge,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Pilih Tanggal",
                                    tint = Color.Gray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = if (selectedDateText == "Tgl Lahir") Color.Gray else Color.Black,
                                disabledBorderColor = Color.Gray,
                                disabledLabelColor = Color.Gray,
                                disabledTrailingIconColor = Color.Gray
                            )
                        )
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clickable { showDatePicker = true }
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.width(152.dp)
                    ) {
                        OutlinedTextField(
                            value = selectedExperience,
                            onValueChange = {},
                            readOnly = true,
                            shape = MaterialTheme.shapes.extraLarge,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = if (selectedExperience == "Experience") Color.Gray else Color.Black,
                                unfocusedTextColor = if (selectedExperience == "Experience") Color.Gray else Color.Black,
                                disabledTextColor = Color.Black,
                                focusedBorderColor = Color.Black,
                                unfocusedBorderColor = Color.Gray,
                                disabledBorderColor = Color.Gray,
                                focusedLabelColor = Color.Black,
                                unfocusedLabelColor = Color.Gray,
                                disabledLabelColor = Color.Gray,
                                cursorColor = Color.Black
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(Color.White)
                        ) {
                            experienceOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = option,
                                            color = Color(0xFF1E1E1E),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    },
                                    onClick = {
                                        selectedExperience = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Pilih Skill Anda :",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.width(310.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                // 4. Checkbox dengan warna monokrom
                Row(
                    modifier = Modifier.width(310.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val checkboxColors = CheckboxDefaults.colors(
                        checkedColor = Color.Black,
                        uncheckedColor = Color.Gray,
                        checkmarkColor = Color.White
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedAndroid,
                            onCheckedChange = { checkedAndroid = it },
                            colors = checkboxColors
                        )
                        Text(text = "Android", fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedUiUx,
                            onCheckedChange = { checkedUiUx = it },
                            colors = checkboxColors
                        )
                        Text(text = "UI/UX", fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedWeb,
                            onCheckedChange = { checkedWeb = it },
                            colors = checkboxColors
                        )
                        Text(text = "Web", fontSize = 12.sp, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // 4. Tombol Register dengan tema monokrom
                Button(
                    modifier = Modifier.width(310.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color(0xFFCCCCCC),
                        disabledContentColor = Color.White
                    ),
                    enabled = namaDepan.isNotBlank() && namaBelakang.isNotBlank() && email.isNotBlank() && selectedDateText != "Tgl Lahir" && selectedExperience != "Experience",
                    onClick = {
                        hasilNamaDepan = namaDepan
                        hasilNamaBelakang = namaBelakang
                        hasilEmail = email
                        hasilTglLahir = selectedDateText
                        hasilExperience = selectedExperience
                        hasilSkills = listOfNotNull(
                            if (checkedAndroid) "Android" else null,
                            if (checkedUiUx) "UI/UX" else null,
                            if (checkedWeb) "Web" else null
                        )
                        showResultCard = true
                    }
                ) {
                    Text(text = "Register", fontWeight = FontWeight.Bold)
                }

                // 5 & 6. Bayangan pemisah + Nougat snack tersembunyi diawal
                AnimatedVisibility(visible = showResultCard) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))
                        HorizontalDivider(
                            modifier = Modifier
                                .width(310.dp)
                                .shadow(2.dp),
                            color = Color(0xFFE0E0E0)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFF222222)),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.width(310.dp)
                        ) {
                            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(
                                    text = "Hasil Registration Data",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(text = "Nama : $hasilNamaDepan $hasilNamaBelakang", color = Color(0xFFE0E0E0), fontSize = 13.sp)
                                Text(text = "Email : $hasilEmail", color = Color(0xFFE0E0E0), fontSize = 13.sp)
                                Text(text = "Tanggal Lahir : $hasilTglLahir", color = Color(0xFFE0E0E0), fontSize = 13.sp)
                                Text(text = "Experience : $hasilExperience", color = Color(0xFFE0E0E0), fontSize = 13.sp)
                                Text(
                                    text = "Skills : " + (if (hasilSkills.isEmpty()) "-" else hasilSkills.joinToString(", ")),
                                    color = Color(0xFFE0E0E0),
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
                onDismiss()
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    val millis = datePickerState.selectedDateMillis
                    onDateSelected(millis)

                    millis?.let {
                        val formattedDate = Instant.ofEpochMilli(it)
                            .atZone(ZoneId.of("UTC"))
                            .toLocalDate()
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                        selectedDateText = formattedDate
                    }
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    onDismiss()
                }) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}