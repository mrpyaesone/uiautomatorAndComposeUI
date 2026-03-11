package com.practical.assessment.ui.pin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practical.assessment.ui.components.InputTextField
import com.practical.assessment.ui.constants.pinFieldTest
import com.practical.assessment.ui.theme.PracticalAssessmentTheme

@Composable
internal fun PinScreen() {
    PinContent()
}

@Composable
private fun PinContent() {
    var pin by rememberSaveable { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            InputTextField(
                inputText = pin,
                textChanges = { text -> pin = text },
                label = "Enter Pin",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                ),
                keyboardActions = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(pinFieldTest)
                    .semantics {
                        testTag = pinFieldTest
                        contentDescription = pinFieldTest
                    })
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PinScreenPreview() {
    PracticalAssessmentTheme {
        Surface { PinContent() }
    }
}