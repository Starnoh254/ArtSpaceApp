package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(){

   var imageResource by remember { mutableStateOf(R.drawable.ic_launcher_foreground) }
    var counter by remember { mutableStateOf(0) }
    var title by remember { mutableStateOf("Artwork Title") }
    var artist by remember { mutableStateOf("Artwork artist ") }
    val imageList = mutableListOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )
     val MAX_IMAGES = imageList.size
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
            .fillMaxSize()
    ) {
        ImageView(modifier = Modifier
            .padding(48.dp)
            .fillMaxWidth(), imageResource)
        Spacer(Modifier.size(36.dp))
        TextView(
            title, artist
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Buttons("Previous") {

                counter = (counter - 1 + MAX_IMAGES) % MAX_IMAGES
                title = "Title${counter + 1}"
                artist = "Artist ${counter + 1}"
                imageResource = imageList[counter]

            }
            Buttons("Next") {
                counter = (counter + 1) % MAX_IMAGES
                title = "Title${counter + 1}"
                artist = "Artist ${counter + 1}"
                imageResource = imageList[counter]

            }
        }

    }
}

@Composable
fun ImageView(
    modifier: Modifier = Modifier,
    imageRource : Int
){
    Column(modifier = Modifier.padding(20.dp)) {
        Image(
            modifier = Modifier.size(480.dp),
            painter = painterResource(imageRource),
            contentDescription = null)
    }


}
@Composable
fun TextView (
    title : String,
    artist: String
){
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color(161, 168, 224, 102)),


    ) {
        Text(text = title,
            modifier = Modifier.padding(top = 16.dp, start = 12.dp))
        Text(text = artist,
            modifier = Modifier.padding(bottom = 16.dp, start = 12.dp))
    }

}


@Composable
fun Buttons(
    name: String,
    onClick : ()-> Unit
){
    Button(modifier = Modifier.padding(bottom = 48.dp, end = 20.dp , start = 20.dp), onClick = onClick){
        Text(text = name)
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceAppTheme {
        AppScreen()

    }
}