package com.example.firstcomposeproject.recyclerviewexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirdcomposeproject.R

//video link https://www.youtube.com/watch?v=YhD2VLP7xWM&t=339s
class MyRecyclerViewComposeActivty : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserList()
        }
    }

    data class User(
        val id: Int
    )

    val users = listOf(
        User(1),
        User(1),
        User(1),
        User(1),
        User(1),
        User(1),
        User(1),
        User(1),
    )

    @Preview
    @Composable
    fun UserList() {
        //below listing will not re-use list elements
     /*   Column(modifier = Modifier.verticalScroll(rememberScrollState())
            .background(color = colorResource(R.color.teal_200),)) {
            for (i in 1..10) {
                userCard()
            }
        }*/

        //below listing will re-use list elements and its recommended
        //alternative to Recycler view
        LazyColumn {
            items(users) { user ->
                userCard()
            }
        }
    }
    @Preview
    @Composable
    fun userCard() {
        Card(
            elevation = 10.dp,
            modifier = Modifier
                .padding(top = 8.dp, start = 12.dp, end = 12.dp)//margin
                .fillMaxWidth()
                .wrapContentHeight()

        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()){
                Image(painter = painterResource(id = R.drawable.abc2), contentDescription = "",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(90.dp)
                        .clip(CircleShape))
                Column {
                    Text(text = stringResource(id = R.string.dummy_text),
                        modifier = Modifier
                            .padding(top = 15.dp, start = 5.dp, end = 5.dp)
                            .//margin.
                            wrapContentHeight()
                            .wrapContentWidth())
                    Button(
                        modifier = Modifier.background(color = colorResource(id = R.color.purple_200)),
                        onClick = {}){
                        Text(text = stringResource(id = R.string.view_profile))
                    }
                }
            }
        }
    }
}