package com.toan.expensemanagergr1.ui.screens.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import com.toan.expensemanagergr1.widget.ExpenseTextView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
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
import com.toan.expensemanagergr1.data.model.ExpenseEntity
import com.toan.expensemanagergr1.ui.theme.Zinc
import com.toan.expensemanagergr1.ui.theme.Zinc1
import com.toan.expensemanagergr1.viewmodel.TotalInfoViewModel
import com.toan.expensemanagergr1.viewmodel.TotalInfoViewModelFactory


@Composable
fun TotalInfo(navController: NavController) {
    val viewModel: TotalInfoViewModel =
        TotalInfoViewModelFactory(LocalContext.current).create(TotalInfoViewModel::class.java)
    Surface(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (nameRow, list, card, topBar, add) = createRefs()
            Image(painter = painterResource(id = R.drawable.ic_topbar), contentDescription = null,
                colorFilter = ColorFilter.tint(Zinc1),
                modifier = Modifier.constrainAs(topBar) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
            )
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, start = 16.dp, end = 16.dp)
                    .constrainAs(nameRow) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }){
                Column {
                    ExpenseTextView(text = "Xin chào!", fontSize = 16.sp, color = Color.White)
                    ExpenseTextView(text = "Expense Manager App!", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_notify),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
            val state = viewModel.expenses.collectAsState(initial = emptyList())
            val expenses = viewModel.getTotalExpense(state.value)
            val income = viewModel.getTotalIncome(state.value)
            val balance = viewModel.getBalance(state.value)

            CardItem(modifier = Modifier.constrainAs(card) {
                top.linkTo(nameRow.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, balance, income, expenses)

            TransactionList(modifier = Modifier
                .fillMaxWidth()
                .constrainAs(list) {
                    top.linkTo(card.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }, list = state.value, viewModel)

        }
    }
}

@Composable
fun CardItem(modifier: Modifier, balance: String, income: String, expenses: String) {
    Column (modifier = modifier
        .padding(16.dp)
        .fillMaxWidth()
        .height(200.dp)
        .padding(16.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(Zinc)
        .padding(16.dp)
    )
    {
        Box (modifier = Modifier
            .fillMaxWidth()
            .weight(1f)) {
            Column (modifier = Modifier.align(Alignment.CenterStart)){
                ExpenseTextView(text = "Số dư hện tại", fontSize = 16.sp, color = Color.White)
                ExpenseTextView(text = balance,
                    fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Bold)
            }
            Image(
                painter = painterResource(id = R.drawable.ic_dotmenu),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd))
        }

        Box (modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            ){
            CardRowItem(modifier = Modifier.align(Alignment.CenterStart),
                title = "Thu nhập",
                amount = income,
                image = R.drawable.ic_income
            )
            CardRowItem(modifier = Modifier.align(Alignment.CenterEnd),
                title = "Chi phí",
                amount = expenses,
                image = R.drawable.ic_outcome
            )
        }
    }
}

@Composable
fun TransactionList(modifier: Modifier, list: List<ExpenseEntity>, viewModel: TotalInfoViewModel) {
    LazyColumn (modifier = modifier.padding(horizontal = 16.dp)) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                ExpenseTextView(text = "Giao dịch gần đây", fontSize = 20.sp)
                ExpenseTextView(text = "Xem tất cả", fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
        items(list) {item ->
            TransactionItem(
                title = item.title,
                amount = item.amount.toString(),
                icon = viewModel.getItemIcon(item),
                date = item.date.toString(),
                color = if (item.type == "Thu nhập") Color.Green else Color.Red)
        }

    }
}

@Composable
fun CardRowItem(modifier: Modifier, title: String, amount: String, image: Int) {
    Column (modifier = modifier) {
        Row {
            ExpenseTextView(text = title, fontSize = 16.sp, color = Color.White)
            Spacer(modifier = Modifier.size(8.dp))
            Image(painter = painterResource(id = image), contentDescription = null)
        }
        ExpenseTextView(text = amount, fontSize = 16.sp, color = Color.White)
    }
}

@Composable
fun TransactionItem(title: String, amount: String, icon: Int, date: String, color: Color) {
    Box(modifier = Modifier.fillMaxWidth()){
        Row {
            Image(painter = painterResource(id = icon),
                contentDescription = null)
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                ExpenseTextView(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
                ExpenseTextView(text = date, fontSize = 12.sp)

            }
        }
        ExpenseTextView(text = amount, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterEnd),
            color = color, fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen(){
    TotalInfo(rememberNavController())
}