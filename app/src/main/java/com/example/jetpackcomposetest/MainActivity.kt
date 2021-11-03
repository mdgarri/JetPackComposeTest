package com.example.jetpackcomposetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetest.models.Account
import com.example.jetpackcomposetest.models.HistoryItem
import androidx.compose.ui.layout.ContentScale
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import com.example.jetpackcomposetest.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTestTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val scaffoldState = rememberScaffoldState()
                    val sharpEdgePercent = -50f
                    val roundEdgePercent = 45f
                    val animatedProgress = remember { Animatable(sharpEdgePercent) }
                    val coroutineScope = rememberCoroutineScope()
                    val progress = animatedProgress.value.roundToInt()


                    val fabShape = when {
                        progress < 0 -> {
                            CutCornerShape(abs(progress))
                        }
                        progress == roundEdgePercent.toInt() -> {
                            CircleShape
                        }
                        else -> {
                            RoundedCornerShape(progress)
                        }
                    }

                    val changeShape: () -> Unit = {
                        val target = animatedProgress.targetValue
                        val nextTarget = if (target == roundEdgePercent) sharpEdgePercent else roundEdgePercent
                        coroutineScope.launch {
                            animatedProgress.animateTo(
                                targetValue = nextTarget,
                                animationSpec = TweenSpec(durationMillis = 600)
                            )
                        }
                    }

                    Scaffold(
                        scaffoldState = scaffoldState,
                        drawerContent = { Text("Drawer content") },
                        topBar = { TopAppBar(title = { Text("Scaffold with bottom cutout") }) },
                        bottomBar = {
                            BottomAppBar(cutoutShape = fabShape) {
                                IconButton(
                                    onClick = {
                                        coroutineScope.launch { scaffoldState.drawerState.open() }
                                    }
                                ) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Localized description")
                                }
                            }
                        },
                        floatingActionButton = {
                            ExtendedFloatingActionButton(
                                text = { Text("Change shape") },
                                onClick = changeShape,
                                shape = fabShape
                            )
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        isFloatingActionButtonDocked = true,
                        content = { innerPadding ->
                            LazyColumn(contentPadding = innerPadding) {
                                items(count = 100) {
                                    Box(
                                        Modifier
                                            .fillMaxWidth()
                                            .height(50.dp)
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    @ExperimentalFoundationApi
    @Composable
    fun BankScreen(){
        Column {
            BankToolBar()
            History(
                historyItems = listOf(
                    HistoryItem.TITLE(true),
                    HistoryItem.PAYMENT(accepted = true, incoming = true),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.TITLE(false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.TITLE(false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = true, incoming = false),
                    HistoryItem.PAYMENT(accepted = false, incoming = true),
                    HistoryItem.PAYMENT(accepted = true, incoming = true),
                )
            )
        }


    }

    @ExperimentalFoundationApi
    @Composable
    @Preview(device = Devices.PIXEL)
    fun PreviewBankScreen(){
        JetPackComposeTestTheme {
            Surface(color = MaterialTheme.colors.background) {
                BankScreen()
            }
        }
    }

    @Composable
    fun BankToolBar() {
        Column(
            modifier = Modifier
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Total balance:",
                    fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f),
                    color = Black_23262C,
                    textAlign = TextAlign.Start,
                )

                Text(
                    text = "Your cards".uppercase(),
                    fontFamily = FontFamily( Font(R.font.gt_america_mono_medium) ),
                    fontSize = 10.sp,
                    modifier = Modifier.weight(1f),
                    color = Black_23262C,
                    textAlign = TextAlign.End,
                )
            }

            Text(
                text = "£0.00",
                fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                fontSize = 32.sp,
                color = Black_23262C,
            )
        }
    }

    @Composable
    fun AccountsCircles(accounts: List<Account>) {
        LazyRow(
            modifier = Modifier
                .padding(top = 40.dp, bottom = 48.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        ) {
            items(items = accounts) { account ->
                AccountCircle(account)
            }
        }
    }

    @Composable
    fun AccountCircle(account: Account) {
        Column(
            modifier = Modifier
                .width(220.dp)
                .height(220.dp)
                .background(shape = CircleShape, color = Green_00FFD1),
        ) {
            Text(
                text = account.percent,
                fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                fontSize = 20.sp,
                color = Black_0F1011,
                modifier = Modifier
                    .alpha(0.4f)
                    .padding(top = 42.dp, start = 52.dp)
                    .height(26.dp)
            )

            Text(
                text = account.balance,
                fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                fontSize = 20.sp,
                color = Black_23262C,
                modifier = Modifier
                    .padding(top = 40.dp, start = 52.dp)
                    .height(26.dp)
            )

            Text(
                text = account.currency,
                fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                fontSize = 20.sp,
                color = Black_23262C,
                modifier = Modifier
                    .alpha(0.4f)
                    .padding(start = 52.dp)
                    .height(26.dp)
            )

            Text(
                text = account.mainAccount,
                fontFamily = FontFamily( Font(R.font.suisse_intl_regular) ),
                fontSize = 14.sp,
                color = Black_23262C,
                modifier = Modifier
                    .alpha(0.4f)
                    .padding(start = 52.dp)
                    .height(18.dp)
            )
        }
    }

    @ExperimentalFoundationApi
    @Composable
    fun History(historyItems: List<HistoryItem>) {
        LazyColumn {
            item {
                AccountsCircles(listOf(Account(), Account()))
            }

            stickyHeader {
                Divider(
                    thickness = 1.dp,
                    color = Black_23262C,
                    modifier = Modifier.padding(top = 0.5.dp, start = 16.dp, end = 16.dp)
                )
            }

            items(historyItems) { item ->
                when(item){
                    is HistoryItem.TITLE -> HistoryItemTitle(item)
                    is HistoryItem.PAYMENT -> HistoryItemPayment(item)
                }
            }
        }
    }

    @Composable
    fun HistoryItemTitle(historyItemTitle: HistoryItem.TITLE) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 17.dp, bottom = 8.dp)
        ) {
            val titleText = if (historyItemTitle.isToday){
                "History for today"
            } else {
                "History for " + historyItemTitle.date
            }

            Text(
                text = titleText,
                fontFamily = FontFamily(Font(R.font.suisse_intl_regular)),
                fontSize = 14.sp,
                color = Black_23262C,
                textAlign = TextAlign.Start
            )
        }
    }

    @Composable
    fun HistoryItemPayment(historyItemPayment: HistoryItem.PAYMENT) {
        Row() {
            val arrowImage = if (historyItemPayment.incoming) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up
            val acceptedText = if(historyItemPayment.accepted) "Accepted, ${historyItemPayment.localDateTime}" else "Declined, ${historyItemPayment.localDateTime}"
            val (mainColor, secondaryColor) = if (historyItemPayment.accepted) {
                Black_23262C to Grey_85878A
            } else {
                Red_FF6231 to Red_FF6231
            }

            Image(
                painterResource(arrowImage),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(
                    mainColor,
                    BlendMode.SrcAtop
                ),
                modifier = Modifier
                    .padding(
                        top = 14.83.dp,
                        start = 22.67.dp,
                        bottom = 14.83.dp,
                        end = 16.67.dp
                    )
            )

            Column {

                Row {
                    Text(
                        text = "from ${historyItemPayment.name}",
                        color = mainColor,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.suisse_intl_regular)),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .height(18.dp)
                            .weight(1f)
                    )

                    Text(
                        text = "+£15.00",
                        color = secondaryColor,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.suisse_intl_regular)),
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .height(16.dp)
                            .weight(1f)
                            .padding(end = 16.dp)
                    )
                }

                Row {

                    Text(
                        text = "to your ${historyItemPayment.toAccount} account",
                        color = secondaryColor,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.suisse_intl_regular)),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .height(16.dp)
                            .weight(1f)
                    )

                    Text(
                        text = acceptedText,
                        color = secondaryColor,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.suisse_intl_regular)),
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .height(16.dp)
                            .weight(1f)
                            .padding(end = 16.dp)
                    )
                }
            }
        }
    }
}