package com.ktp.questtugasuserinput

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.platform.LocalContext
import java.time.ZoneId

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

    //Variale untuk menyimpan data hasil input dari kompnen UI
    var hasilNamaDepan by remember { mutableStateOf("") }
    var hasilNamaBelakang by remember { mutableStateOf("") }
    var hasilEmail by remember { mutableStateOf("") }
    var hasilTglLahir by remember { mutableStateOf("") }
    var hasilExperience by remember { mutableStateOf("") }
    var hasilSkills by remember { mutableStateOf(listOf<String>()) }

    val context = LocalContext.current


    Column(modifier = modifier
        .fillMaxSize()
        .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
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
        Box(modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(topEnd = 25.dp))
            .background(Color.White),
            contentAlignment = Alignment.Center) {
            Column(modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Welcome",
                    fontSize = 25.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Text(
                    text = "You Can Start With Register",
                    fontSize = 12.sp,
                    fontFamily = FontFamily.Default,
                    color = Color.Black
                )
                Row {
                    OutlinedTextField(
                        value = namaDepan,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(155.dp),
                        label = {Text(text = "Nama Depan")},
                        onValueChange = {namaDepan = it}
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    OutlinedTextField(
                        value = namaBelakang,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(155.dp),
                        label = {Text(text = "Nama Belakang")},
                        onValueChange = {namaBelakang = it}
                    )
                }
                OutlinedTextField(
                    value = email,
                    singleLine = true,
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier.width(310.dp),
                    label = {Text(text = "Email")},
                    onValueChange = {email = it}
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Box {
                        OutlinedTextField(
                            value = selectedDateText,
                            onValueChange = { },
                            readOnly = true,
                            enabled = false,
                            modifier = Modifier.width(155.dp),
                            shape = MaterialTheme.shapes.extraLarge,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Pilih Tanggal",
                                    tint = Color.Gray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = Color.Gray,
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
                    Spacer(modifier = Modifier.width(5.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                        modifier = Modifier.width(155.dp)
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
                                disabledLabelColor = Color.Gray,
                                disabledTextColor = Color.Gray,
                                focusedBorderColor = Color.Gray,
                                unfocusedBorderColor = Color.Gray
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            experienceOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(text = option) },
                                    onClick = {
                                        selectedExperience = option
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pilih Skill Anda :",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Checkbox(
                            checked = checkedAndroid,
                            onCheckedChange = { checkedAndroid = it }
                        )
                        Text(text = "Android", fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Checkbox(
                            checked = checkedUiUx,
                            onCheckedChange = { checkedUiUx = it }
                        )
                        Text(text = "UI/UX", fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material3.Checkbox(
                            checked = checkedWeb,
                            onCheckedChange = { checkedWeb = it }
                        )
                        Text(text = "Web", fontSize = 12.sp, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(12.dp).padding(start = 8.dp, end = 8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(1f),
                    //Button bisa digunakan ketika semua kolom sudah terisi
                    enabled = hasilNamaDepan.isNotEmpty() && hasilNamaBelakang.isNotEmpty() && hasilEmail.isNotEmpty() && hasilTglLahir.isNotEmpty() && hasilExperience.isNotEmpty(),
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
                    }
                ) {
                    Text(text = "Register")
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
                        val formattedDate = java.time.Instant.ofEpochMilli(it)
                            .atZone(ZoneId.of("UTC"))
                            .toLocalDate()
                            .format(java.time.format.DateTimeFormatter.ofPattern("dd MMM yyyy"))
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