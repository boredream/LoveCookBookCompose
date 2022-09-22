package com.boredream.lovecookbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.boredream.lovecookbook.entity.BottomNavItemInfo
import com.boredream.lovecookbook.entity.TheDay
import com.boredream.lovecookbook.ui.theme.LoveCookBookTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            content()
        }
    }
}

@Composable
fun content() {
    var selectedItem = 0
    val navItems = listOf(
        BottomNavItemInfo(name = "纪念日", icon = Icons.Filled.Notifications),
        BottomNavItemInfo(name = "日记", icon = Icons.Filled.DateRange),
        BottomNavItemInfo(name = "清单", icon = Icons.Filled.List),
        BottomNavItemInfo(name = "我的", icon = Icons.Filled.Person),
    )

    LoveCookBookTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text("纪念日")
                })
            },
            bottomBar = {
                BottomNavigation {
                    navItems.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = { Icon(item.icon, contentDescription = null) },
                            label = { Text(item.name) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index }
                        )
                    }
                }
            }
        ) {
            BodyContent(modifier = Modifier
                .padding(it)
                .padding(8.dp))
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier) {
    Surface(modifier = modifier) {
        TheDayList(theDayList = listOf(
            TheDay(name = "节日", theDayDate = "2022-12-21"),
            TheDay(name = "生日", theDayDate = "2022-12-21"),
        ))
    }
}

@Composable
fun TheDayList(theDayList: List<TheDay>) {
    LazyColumn {
        items(theDayList) { theDay ->
            TheDayItem(theDay)
        }
    }
}

@Composable
fun TheDayItem(theDay: TheDay) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(theDay.name)
                Text(theDay.theDayDate, style = MaterialTheme.typography.caption)
            }
            Spacer(modifier = Modifier.weight(weight = 1f))
            Text("第")
            Text("第", style = MaterialTheme.typography.h5)
            Text("天")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    content()
}