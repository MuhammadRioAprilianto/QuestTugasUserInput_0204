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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputData(
    modifier: Modifier = Modifier,
    onDateSelected: (Long?) -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    // Variable string resources untuk placeholder & pilihan
    val dobPlaceholder = stringResource(R.string.label_dob_placeholder)
    val experiencePlaceholder = stringResource(R.string.label_experience_placeholder)
    val androidSkill = stringResource(R.string.skill_android)
    val uiUxSkill = stringResource(R.string.skill_ui_ux)
    val webSkill = stringResource(R.string.skill_web)
    val dateFormat = stringResource(R.string.date_format)

    val experienceOptions = listOf(
        stringResource(R.string.exp_beginner),
        stringResource(R.string.exp_intermediate),
        stringResource(R.string.exp_expert)
    )

    // Variable untuk menyimpan apa yang diketik oleh Keyboard
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDateText by remember { mutableStateOf(dobPlaceholder) }
    var expanded by remember { mutableStateOf(false) }
    var selectedExperience by remember { mutableStateOf(experiencePlaceholder) }
    var checkedAndroid by remember { mutableStateOf(false) }
    var checkedUiUx by remember { mutableStateOf(false) }
    var checkedWeb by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    // Variable untuk menyimpan data hasil input dari komponen UI
    var hasilNamaDepan by remember { mutableStateOf("") }
    var hasilNamaBelakang by remember { mutableStateOf("") }
    var hasilEmail by remember { mutableStateOf("") }
    var hasilTglLahir by remember { mutableStateOf("") }
    var hasilExperience by remember { mutableStateOf("") }
    var hasilSkills by remember { mutableStateOf(listOf<String>()) }
    var showResultCard by remember { mutableStateOf(false) }

    // Color resources
    val charcoalDark = colorResource(R.color.charcoal_dark)
    val textDarkGray = colorResource(R.color.text_dark_gray)
    val dividerGray = colorResource(R.color.divider_gray)
    val disabledButtonGray = colorResource(R.color.disabled_button_gray)
    val resultTextGray = colorResource(R.color.result_text_gray)

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
                // Title & Subtitle
                Text(
                    text = stringResource(R.string.welcome_title),
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.register_subtitle),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(20.dp))

                // Input fields
                Row {
                    OutlinedTextField(
                        value = namaDepan,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(152.dp),
                        label = { Text(text = stringResource(R.string.label_first_name)) },
                        onValueChange = { namaDepan = it },
                        colors = customTextFieldColors
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    OutlinedTextField(
                        value = namaBelakang,
                        singleLine = true,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier.width(152.dp),
                        label = { Text(text = stringResource(R.string.label_last_name)) },
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
                    label = { Text(text = stringResource(R.string.label_email)) },
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
                                    contentDescription = stringResource(R.string.content_desc_choose_date),
                                    tint = Color.Gray
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                disabledTextColor = if (selectedDateText == dobPlaceholder) Color.Gray else Color.Black,
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
                                focusedTextColor = if (selectedExperience == experiencePlaceholder) Color.Gray else Color.Black,
                                unfocusedTextColor = if (selectedExperience == experiencePlaceholder) Color.Gray else Color.Black,
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
                                            color = textDarkGray,
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
                    text = stringResource(R.string.label_select_skills),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.width(310.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Checkbox
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
                        Text(text = androidSkill, fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedUiUx,
                            onCheckedChange = { checkedUiUx = it },
                            colors = checkboxColors
                        )
                        Text(text = uiUxSkill, fontSize = 12.sp, color = Color.Black)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = checkedWeb,
                            onCheckedChange = { checkedWeb = it },
                            colors = checkboxColors
                        )
                        Text(text = webSkill, fontSize = 12.sp, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                // Tombol Register
                Button(
                    modifier = Modifier.width(310.dp),
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = disabledButtonGray,
                        disabledContentColor = Color.White
                    ),
                    enabled = namaDepan.isNotBlank() && namaBelakang.isNotBlank() && email.isNotBlank() && selectedDateText != dobPlaceholder && selectedExperience != experiencePlaceholder,
                    onClick = {
                        hasilNamaDepan = namaDepan
                        hasilNamaBelakang = namaBelakang
                        hasilEmail = email
                        hasilTglLahir = selectedDateText
                        hasilExperience = selectedExperience
                        hasilSkills = listOfNotNull(
                            if (checkedAndroid) androidSkill else null,
                            if (checkedUiUx) uiUxSkill else null,
                            if (checkedWeb) webSkill else null
                        )
                        showResultCard = true
                    }
                ) {
                    Text(text = stringResource(R.string.btn_register), fontWeight = FontWeight.Bold)
                }

                // Nougat snack (ElevatedCard)
                AnimatedVisibility(visible = showResultCard) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Spacer(modifier = Modifier.height(20.dp))
                        HorizontalDivider(
                            modifier = Modifier
                                .width(310.dp)
                                .shadow(2.dp),
                            color = dividerGray
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        ElevatedCard(
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                            colors = CardDefaults.cardColors(containerColor = charcoalDark),
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.width(310.dp)
                        ) {
                            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                                Text(
                                    text = stringResource(R.string.result_title),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.White
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = stringResource(R.string.result_name_format, hasilNamaDepan, hasilNamaBelakang),
                                    color = resultTextGray,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = stringResource(R.string.result_email_format, hasilEmail),
                                    color = resultTextGray,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = stringResource(R.string.result_dob_format, hasilTglLahir),
                                    color = resultTextGray,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = stringResource(R.string.result_experience_format, hasilExperience),
                                    color = resultTextGray,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = stringResource(
                                        R.string.result_skills_format,
                                        if (hasilSkills.isEmpty()) "-" else hasilSkills.joinToString(", ")
                                    ),
                                    color = resultTextGray,
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
                            .format(DateTimeFormatter.ofPattern(dateFormat))
                        selectedDateText = formattedDate
                    }
                }) {
                    Text(stringResource(R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    onDismiss()
                }) {
                    Text(stringResource(R.string.btn_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}