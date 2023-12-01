package com.covalent.locationfinderapp.ui.screens.deliveryApp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.covalent.locationfinderapp.R
import com.covalent.locationfinderapp.data.model.CardDetails
import com.covalent.locationfinderapp.ui.screens.main.Greeting
import com.covalent.locationfinderapp.ui.theme.LocationFinderAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    val dataList = listOf(
        CardDetails(
            amount = "47 $",
            orderNo = "No. F15306",
            status = "Processed",
            toAddressLine1 = "88 Zurab Gorgiladze St",
            toAddressLine2 = "Georgia, Batumi",
            fromAddress1 = "5 Noe Zhordania St",
            fromAddress2 = "Georgia, Batumi"
        ),
        CardDetails(
            amount = "100 $",
            orderNo = "No. F15306",
            status = "Processed",
            toAddressLine1 = "88 Zurab Gorgiladze St",
            toAddressLine2 = "Georgia, Batumi",
            fromAddress1 = "5 Noe Zhordania St",
            fromAddress2 = "Georgia, Batumi"
        ),
        CardDetails(
            amount = "36 $",
            orderNo = "No. F15306",
            status = "Processed",
            toAddressLine1 = "88 Zurab Gorgiladze St",
            toAddressLine2 = "Georgia, Batumi",
            fromAddress1 = "5 Noe Zhordania St",
            fromAddress2 = "Georgia, Batumi"
        )
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Orders", style = TextStyle(
                            fontSize = 19.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight(500),
                            letterSpacing = 0.15.sp,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.magnifyingglass),
                            contentDescription = "search",
                            tint = colorResource(
                                id = R.color.background_color
                            ),
                            modifier = Modifier.size(24.dp),
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.faders),
                            contentDescription = "search",
                            tint = colorResource(
                                id = R.color.background_color
                            ),
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(start = 8.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.wallet_svg),
                                contentDescription = "Wallet",
                                modifier = Modifier.size(28.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "6,730 $", style = TextStyle(
                                    fontStyle = FontStyle.Normal,
                                    fontWeight = FontWeight(500),
                                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                                )
                            )
                        }

                        Icon(
                            painterResource(id = R.drawable.chevron),
                            contentDescription = "Wallet",
                            modifier = Modifier.size(28.dp),
                            tint = colorResource(id = R.color.background_color)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                LazyColumn() {
                    items(dataList) {
                        com.covalent.locationfinderapp.ui.screens.deliveryApp.CardDetails(
                            cardDetails = it
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun CardDetails(cardDetails: CardDetails) {
    Card(
        modifier = Modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = cardDetails.amount,
                    style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(500),
                        fontSize = 17.sp,
                        color = MaterialTheme.colorScheme.onSecondary
                    ),
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(text = cardDetails.orderNo, style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                        color = Color(0xFFB8B8B8)
                    )
                    )
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painterResource(id = R.drawable.icon_navigation),
                            contentDescription = "Wallet",
                            modifier = Modifier.size(24.dp),
                            tint = colorResource(id = R.color.background_color)
                        )
                    }
                }
            }

            Surface(
                shape = RoundedCornerShape(4.dp),
                color = Color(0xFFFEF7EC)
            ) {
                Text(
                    text = cardDetails.status,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp),
                    color = Color(0xFFF2AB58),
                    style = TextStyle(
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight(400),
                        fontSize = 14.sp,
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                color = Color(0xFFD9D9D9).copy(alpha = 0.4f)
            )
        }
    }
}
