package com.drackmord.chilahgenerator.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.drackmord.chilahgenerator.R
import com.drackmord.chilahgenerator.presentation.model.FormModel
import com.drackmord.chilahgenerator.presentation.model.LeftRight

@Composable
fun GeneratorForm() {
    var formState by remember { mutableStateOf(FormModel()) }

    with(formState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Section {
                TitleEntry(
                    placeHolder = "Client name",
                    value = clientName,
                    onValueChange = { formState = copy(clientName = it) })
                NumericEntry(
                    name = "Weight",
                    value = weight,
                    onValueChange = { formState = copy(weight = it) })
                NumericEntry(
                    name = "Height",
                    value = height,
                    onValueChange = { formState = copy(height = it) })
                NumericEntry(
                    name = "Fat Percentage",
                    value = fatPercent,
                    onValueChange = { formState = copy(fatPercent = it) })
                NumericEntry(
                    name = "Visceral Fat",
                    value = visceralFat,
                    onValueChange = { formState = copy(visceralFat = it) })
                PosturesEntry(
                    selected = posture,
                    onValueChange = { formState = copy(posture = it) })
            }

            Section(title = "Overhead Squat") {
                LeftRightCheckboxEntry(
                    name = "Feet Cave in",
                    checked = feetCaveIn,
                    onCheckedChange = { formState = copy(feetCaveIn = it) }
                )
                LeftRightCheckboxEntry(
                    name = "Knee Cave in",
                    checked = squatKneeCaveIn,
                    onCheckedChange = { formState = copy(squatKneeCaveIn = it) }
                )
                CheckboxEntry(
                    name = "Butwink",
                    checked = butwink,
                    onCheckedChange = { formState = copy(butwink = it) })
                CheckboxEntry(
                    name = "Hyperextension",
                    checked = hyperextension,
                    onCheckedChange = { formState = copy(hyperextension = it) })
                CheckboxEntry(
                    name = "Forward Lean",
                    checked = forwardLean,
                    onCheckedChange = { formState = copy(forwardLean = it) })
            }

            Section(title = "Step up") {
                LeftRightCheckboxEntry(
                    name = "Knee Cave in",
                    checked = stepUpKneeCaveIn,
                    onCheckedChange = { formState = copy(stepUpKneeCaveIn = it) }
                )
                LeftRightCheckboxEntry(
                    name = "Hip shift",
                    checked = hipShift,
                    onCheckedChange = { formState = copy(hipShift = it) }
                )
            }

            Section {
                CheckboxEntry(
                    name = "Hip Hinge",
                    checked = hipHinge,
                    onCheckedChange = { formState = copy(hipHinge = it) })
                CheckboxEntry(
                    name = "Wall Slide",
                    checked = wallSlide,
                    onCheckedChange = { formState = copy(wallSlide = it) })
            }

            Section(title = "Push") {
                LeftRightCheckboxEntry(
                    name = "Shrugged",
                    checked = pushShrugged,
                    onCheckedChange = { formState = copy(pushShrugged = it) }
                )
                CheckboxEntry(
                    name = "Retraction",
                    checked = pushRetraction,
                    onCheckedChange = { formState = copy(pushRetraction = it) })
                CheckboxEntry(
                    name = "Hyperextension",
                    checked = pushHyperextension,
                    onCheckedChange = { formState = copy(pushHyperextension = it) })
            }

            Section(title = "Pull") {
                LeftRightCheckboxEntry(
                    name = "Shrugged",
                    checked = pullShrugged,
                    onCheckedChange = { formState = copy(pullShrugged = it) }
                )
                CheckboxEntry(
                    name = "Retraction",
                    checked = pullRetraction,
                    onCheckedChange = { formState = copy(pullRetraction = it) })
                CheckboxEntry(
                    name = "Hyperextension",
                    checked = pullHyperextension,
                    onCheckedChange = { formState = copy(pullHyperextension = it) })
            }

            Box(modifier = Modifier.padding(15.dp)) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Constants.rowHeight),
                    onClick = { /*TODO*/ }) {
                    Text(text = "Generate Excel")
                }
            }
        }
    }
}

@Composable
fun Section(title: String? = null, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Column {
            if (title != null) {
                Text(text = title)
            }
            Column(modifier = Modifier.padding(15.dp)) {
                content()
            }
        }
    }
}

@Composable
fun TitleEntry(placeHolder: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(Constants.rowHeight),
        placeholder = {
            Text(
                text = placeHolder,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        textStyle = TextStyle(textAlign = TextAlign.Center)
    )
}

@Composable
fun RowEntry(name: String, content: @Composable () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(Constants.rowHeight)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(fraction = Constants.titleWidth)
                .fillMaxHeight()
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = name, textAlign = TextAlign.Center)
        }
        content()
    }
}

@Composable
fun NumericEntry(name: String, value: String, onValueChange: (String) -> Unit) {
    RowEntry(name = name) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxSize(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            textStyle = TextStyle(textAlign = TextAlign.Center)
        )
    }
}

@Composable
fun PosturesEntry(selected: Int, onValueChange: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.posture), contentDescription = "Posture")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            RadioButton(selected = selected == 0, onClick = { onValueChange(0) })
            RadioButton(selected = selected == 1, onClick = { onValueChange(1) })
            RadioButton(selected = selected == 2, onClick = { onValueChange(2) })
            RadioButton(selected = selected == 3, onClick = { onValueChange(3) })
        }
    }
}

@Composable
fun LeftRightCheckboxEntry(
    name: String,
    checked: LeftRight,
    onCheckedChange: (LeftRight) -> Unit
) {
    RowEntry(name = name) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            LabelledCheckbox(
                label = "Left",
                checked = checked.left,
                onCheckedChange = { onCheckedChange(checked.copy(left = it)) })
            LabelledCheckbox(
                label = "Right",
                checked = checked.right,
                onCheckedChange = { onCheckedChange(checked.copy(right = it)) })
        }
    }
}

@Composable
fun LabelledCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label)
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
    }
}

@Composable
fun CheckboxEntry(name: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    RowEntry(name = name) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        }
    }
}

object Constants {
    val rowHeight = 50.dp
    const val titleWidth = 0.5f
    val postures = listOf(
        "Ideal alignment",
        "Kyphotic-lordotic posture",
        "Flat-back posture",
        "Sway-back posture"
    )
}