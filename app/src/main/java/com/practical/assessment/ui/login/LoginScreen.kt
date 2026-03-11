package com.practical.assessment.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practical.assessment.R
import com.practical.assessment.ui.components.InputTextField
import com.practical.assessment.ui.constants.loginBtnTest
import com.practical.assessment.ui.constants.pwdFieldTest
import com.practical.assessment.ui.theme.PracticalAssessmentTheme

@Composable
internal fun LoginScreen(
    onNavigateToCheckScreen: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle(LoginUiState.Idle)

    when (uiState) {
        LoginUiState.Idle -> LoginContent(onLoginClick = viewModel::login)
        LoginUiState.Success -> onNavigateToCheckScreen()
        is LoginUiState.Error -> LoginContent(
            errorMsg = (uiState as LoginUiState.Error).message,
            onLoginClick = viewModel::login
        )
    }
}

@Composable
private fun LoginContent(errorMsg: String = "", onLoginClick: (password: String) -> Unit) {
    var pwdVisibility by rememberSaveable { mutableStateOf(false) }
    val pwdIcon =
        if (pwdVisibility) R.drawable.ic_pwd_visibility_on else R.drawable.ic_pwd_visibility_off
    var password by rememberSaveable { mutableStateOf("") }

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
                inputText = password,
                textChanges = { text ->
                    password = text
                },
                label = "Password",
                endIcon = pwdIcon,
                endIconAction = {
                    pwdVisibility = !pwdVisibility
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, imeAction = ImeAction.Done
                ),
                isError = errorMsg.isNotEmpty(),
                supportingText = errorMsg,
                keyboardActions = { },
                visualTransformation = if (pwdVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(pwdFieldTest)
                    .semantics {
                        testTag = pwdFieldTest

                        contentDescription = pwdFieldTest }
            )

            Button(
                onClick = { onLoginClick(password) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp)
                    .testTag(loginBtnTest)
                    .semantics{
                        testTag = loginBtnTest
                        contentDescription = loginBtnTest
                    }
            ) {
                Text("Login")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    PracticalAssessmentTheme {
        Surface { LoginScreen(onNavigateToCheckScreen = {}) }
    }
}