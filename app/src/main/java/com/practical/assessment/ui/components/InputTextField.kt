package com.practical.assessment.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputTextField(
    inputText: String,
    textChanges: (String) -> Unit,
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    @DrawableRes endIcon: Int? = null,
    endIconAction: () -> Unit = {},
    @DrawableRes startIcon: Int? = null,
    startIconAction: () -> Unit = {},
    modifier: Modifier = Modifier,
    textStyle: TextStyle = LocalTextStyle.current,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    maxLine: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Default
    ),
    keyboardActions: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    bgColor: Color = Color.Transparent,
    focusTxtColor: Color = Color.Unspecified,
    disableBgColor: Color = Color.Unspecified,
    disableTxtColor: Color = Color.Unspecified,
    disableIconColor: Color = Color.Unspecified,
    errTxtColor: Color = Color.Unspecified,     // coming soon
    contextDescription: String? = null,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = inputText,
        onValueChange = textChanges,
        label = { label?.let { Text(label, modifier = Modifier.padding(bottom = 4.dp)) } },
        placeholder = { placeholder?.let { Text(placeholder) } },
        supportingText = { supportingText?.let { Text(supportingText) } },
        trailingIcon = endIcon?.let {
            {
                IconButton(onClick = endIconAction) {
                    Icon(
                        painter = painterResource(endIcon),
                        contentDescription = null
                    )
                }
            }
        },
        leadingIcon = if (startIcon != null) {
            {
                IconButton(onClick = startIconAction) {
                    Icon(painter = painterResource(startIcon), contentDescription = null)
                }
            }
        } else null,
        textStyle = textStyle,
        enabled = enabled,
        readOnly = readOnly,
        isError = isError,
        maxLines = maxLine,
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions {
            keyboardActions()
            keyboardController?.hide()
        },
        visualTransformation = visualTransformation,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusTxtColor,
            unfocusedContainerColor = bgColor,
            focusedContainerColor = bgColor,
            disabledContainerColor = disableBgColor,
            disabledTextColor = disableTxtColor,
            disabledLeadingIconColor = disableIconColor,
            disabledTrailingIconColor = disableIconColor,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.outlineVariant,
            unfocusedLabelColor = MaterialTheme.colorScheme.outline,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.outline
        )
    )
}

@Preview(showBackground = true)
@Composable
fun InputTextFieldPreview() {
    InputTextField(
        inputText = "",
        textChanges = { text -> print(text) },
        label = "User Name",
        endIcon = null,
        startIcon = null,
        modifier = Modifier.padding(20.dp)
    )
}