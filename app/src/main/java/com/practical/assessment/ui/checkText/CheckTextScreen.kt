package com.practical.assessment.ui.checkText

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practical.assessment.ui.constants.notTestBtnTest
import com.practical.assessment.ui.constants.testBtnTest
import com.practical.assessment.ui.constants.testTextTest
import com.practical.assessment.ui.theme.PracticalAssessmentTheme

@Composable
internal fun CheckTextScreen(onNavToNext: () -> Unit) {
    val context = LocalContext.current

    BackHandler { (context as? Activity)?.finish() }

    CheckTextContent(
        onTestTextClick = onNavToNext,
        onNotTestTextClick = { (context as? Activity)?.finish() })
}

@Composable
private fun CheckTextContent(onTestTextClick: () -> Unit, onNotTestTextClick: () -> Unit) {
    Scaffold { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(text = "test text 1", modifier = Modifier
                .testTag(testTextTest)
                .semantics {
                    testTag = testTextTest
                    contentDescription = testTextTest
                })
            Button(
                onClick = onTestTextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .testTag(testBtnTest)
                    .semantics{
                        testTag = testBtnTest
                        contentDescription = testBtnTest
                    }
            ) {
                Text("Test text")
            }

            Button(
                onClick = onNotTestTextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .testTag(notTestBtnTest)
                    .semantics{
                        testTag = notTestBtnTest
                        contentDescription = notTestBtnTest
                    }
            ) {
                Text("Not test text")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CheckTextScreenPreview() {
    PracticalAssessmentTheme {
        Surface { CheckTextContent({}, {}) }
    }
}