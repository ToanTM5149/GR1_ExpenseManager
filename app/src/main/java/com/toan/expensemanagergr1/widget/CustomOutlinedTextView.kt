package com.toan.expensemanagergr1.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.toan.expensemanagergr1.ui.theme.Zinc

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(20.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedLeadingIconColor = Zinc,
            unfocusedLeadingIconColor = Zinc,
            focusedLabelColor = Zinc,
            unfocusedLabelColor = Zinc,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = Zinc,
            unfocusedBorderColor = Zinc,
            cursorColor = Zinc,
            errorCursorColor = Color.Red,
            errorLeadingIconColor = Color.Red,
            errorLabelColor = Color.Red,
            errorContainerColor = Color.White,
            errorBorderColor = Color.Red
        ),
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth(),
        isError = isError,
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines
    )
}

