package com.toan.expensemanagergr1.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.toan.expensemanagergr1.R
import com.toan.expensemanagergr1.ui.screens.user.DataForm
import com.toan.expensemanagergr1.ui.theme.Zinc
import com.toan.expensemanagergr1.ui.theme.Zinc
import com.toan.expensemanagergr1.ui.theme.Zinc1
import com.toan.expensemanagergr1.widget.ExpenseTextView
import kotlinx.coroutines.launch


@Composable
fun Login(navController: NavController) {


    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                colorFilter = ColorFilter.tint(Zinc1),
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                })
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 16.dp, end = 16.dp)
                .constrainAs(nameRow) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
            ){
                Column {
                    ExpenseTextView(text = "Xin chào!", fontSize = 30.sp, color = Color.White,
                        modifier = Modifier.padding(top = 30.dp).shadow(1.dp))
                    ExpenseTextView(text = "Chúc bạn một ngày tốt lành!", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .shadow(1.dp)
                            .align(Alignment.CenterHorizontally))
                }
            }

            LoginForm(modifier = Modifier
                .padding(top = 110.dp)
                .constrainAs(card) {
                    top.linkTo(nameRow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        }
    }
}

@Composable
fun LoginForm(modifier: Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .padding(16.dp)
        .shadow(8.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Color.White)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
        ExpenseTextView(text = "Đăng nhập", fontSize = 30.sp, fontWeight = FontWeight.Bold,
            color = Zinc,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp))
        OutlinedTextField(
            value = username, onValueChange = {username = it},
            label = { Text(text = "Tên người dùng")},
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
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Username")
            },
            modifier = Modifier
                .fillMaxWidth()
        )

        OutlinedTextField(
            value = password, onValueChange = {password = it},
            label = { Text(text = "Mật khẩu")},
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
            ), leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Password")
            },
            modifier = Modifier
                .fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(Zinc),
            contentPadding = PaddingValues(start = 60.dp, end = 60.dp, top = 8.dp, bottom = 8.dp),
            modifier = Modifier
                .padding(top = 18.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Login")
        }
        Row (modifier = Modifier.align(Alignment.CenterHorizontally)) {
            ExpenseTextView(text = "Bạn chưa có tài khoản?", fontSize = 16.sp, color = Zinc, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 10.dp, end = 5.dp))
            Spacer(modifier = Modifier.size(2.dp))
            ExpenseTextView(text = "Đăng ký ngay", fontSize = 16.sp, color = Zinc, fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(top = 10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    Login(rememberNavController())
}