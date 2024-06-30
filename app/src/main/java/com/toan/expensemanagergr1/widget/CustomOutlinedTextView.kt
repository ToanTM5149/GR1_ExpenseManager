package com.toan.expensemanagergr1.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
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
    label: String,
    leadingIcon: @Composable (() -> Unit),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedLeadingIconColor = Zinc,
            unfocusedLeadingIconColor = Zinc,
            focusedLabelColor = Zinc,
            unfocusedLabelColor = Zinc,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Zinc,
            unfocusedIndicatorColor = Zinc,
            unfocusedPlaceholderColor = Zinc
        ),
        leadingIcon = leadingIcon,
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth()
    )
}