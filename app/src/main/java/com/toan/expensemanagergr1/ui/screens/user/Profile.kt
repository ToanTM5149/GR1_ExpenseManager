package com.toan.expensemanagergr1.ui.screens.user


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.toan.expensemanagergr1.R
import com.toan.expensemanagergr1.ui.theme.Zinc
import com.toan.expensemanagergr1.ui.theme.Zinc1
import com.toan.expensemanagergr1.widget.ExpenseTextView
import com.toan.expensemanagergr1.widget.Ultis

@Composable
fun Profile(navController: NavController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, card, list, topBar) = createRefs()
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
            ) {
                ExpenseTextView(
                    text = "Personal Profile",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
            CardItem(modifier = Modifier.constrainAs(card) {
                top.linkTo(nameRow.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            SettingOptionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                })
            Column(modifier = Modifier.fillMaxSize()) {
                Button(
                    onClick = { navController.navigate("/login") },
                    colors = ButtonDefaults.buttonColors(Zinc),
                    contentPadding = PaddingValues(
                        start = 60.dp,
                        end = 60.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ),
                    modifier = Modifier
                        .padding(top = 700.dp)
                        .align(Alignment.CenterHorizontally)
                )
                {
                    ExpenseTextView(text = "Đăng xuất")
                }
            }
        }
    }
}

@Composable
fun CardItem(modifier: Modifier) {
    Column (modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(IntrinsicSize.Min)
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_profile),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.size(2.dp))
        ExpenseTextView(text = "Trần Mạnh Toàn", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Zinc,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        ExpenseTextView(text = "@toan", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Zinc,
            modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun SettingOptionList(modifier: Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Column {
                CustomRow(icon = R.drawable.ic_invite, desc = "Mời bạn bè")
                Spacer(modifier = Modifier.height(12.dp))
                Divider(color = Zinc, thickness = 1.dp)
                Spacer(modifier = Modifier.height(15.dp))
                CustomRow(icon = R.drawable.ic_personalprofile, desc = "Thông tin cá nhân")
                Spacer(modifier = Modifier.height(15.dp))
                CustomRow(icon = R.drawable.ic_securityandprivacy, desc = "Chính sách và bảo mật")
                Spacer(modifier = Modifier.height(15.dp))
                CustomRow(icon = R.drawable.baseline_settings_24, desc = "Cài đặt chung")
            }

        }


    }
}

@Composable
fun CustomRow(icon: Int, desc: String) {
    Row {
        Image(painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .width(45.dp)
                .height(45.dp)
        )
        ExpenseTextView(text = desc, fontSize = 20.sp, modifier = Modifier.padding(top = 8.dp, start = 5.dp))
    }
}
@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    Profile(rememberNavController())
}