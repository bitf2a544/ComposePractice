package com.example.firstcomposeproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp

// Example application showcasing how to share a view model between different
// composable functions inside Jetpack Compose Navigation.

// Converts Fahrenheit to Celsius

class MainActivityRecyclerView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyAppToolBarScaffold()

        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyAppToolBarScaffold() {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Blue,//tool bar background color
                        titleContentColor = Color.White
                    ),
                    title = {
                        Text(
                            text = "CountryInfoApp",
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Search",
                                tint = Color.White
                            )
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Filled.MoreVert,
                                contentDescription = "MoreVert",
                                tint = Color.White
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
            bottomBar = {
                BottomAppBar(containerColor = Color.Cyan) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Sort, contentDescription = "Sort")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = Color.Black
                        )
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "MoreVert",
                            tint = Color.Black
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    containerColor = Color.Yellow,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                ) {
                    Icon(imageVector = Icons.Filled.Filter, contentDescription = "Filter")
                }
            }
        ) { innerPaddingValues ->
            MyScreenContent()
            // MainScreen(countryList = countryList,innerPaddingValues)
        }
    }

    @Composable
    fun MyScreenContent() {
        val arrayList = ArrayList<String>()//Creating an empty arraylist
        arrayList.add("Item 21")
        arrayList.add("Item 22")
        arrayList.add("Item 23")
        arrayList.add("Item 24")
        arrayList.add("Item 25")
        arrayList.add("Item 26")
        arrayList.add("Item 27")
        arrayList.add("Item 28")
        arrayList.add("Item 29")
        arrayList.add("Item 30")
        arrayList.add("Item 31")
        arrayList.add("Item 32")
        arrayList.add("Item 33")
        arrayList.add("Item 34")
        arrayList.add("Item 35")
        arrayList.add("Item 36")
        arrayList.add("Item 37")
        arrayList.add("Item 38")
        arrayList.add("Item 39")
        arrayList.add("Item 40")


        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(5.dp)
        ) {
            items(arrayList.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "" + arrayList[it],
                        modifier = Modifier.padding(10.dp),
                    )
                }

            }
        }
    }

}

