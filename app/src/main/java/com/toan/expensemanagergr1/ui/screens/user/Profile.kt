package com.toan.expensemanagergr1.ui.screens.user


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.toan.expensemanagergr1.ui.theme.Zinc
import com.toan.expensemanagergr1.widget.ExpenseTextView

@Composable
fun Profile(navController: NavController) {
    Column (modifier = Modifier.fillMaxSize()) {
        Button(onClick = { navController.navigate("/login") },
            colors = ButtonDefaults.buttonColors(Zinc),
            contentPadding = PaddingValues(start = 60.dp, end = 60.dp, top = 8.dp, bottom = 8.dp),
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.CenterHorizontally)
        )
        {
            ExpenseTextView(text = "Đăng xuất")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    Profile(rememberNavController())
}