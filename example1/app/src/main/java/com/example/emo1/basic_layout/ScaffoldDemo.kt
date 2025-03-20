import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.emo1.basic_layout.LazyListDemo
import com.example.emo1.ui.theme.Emo1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Text("Title")
        }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "")
            }
        }, actions = {
            IconButton(onClick = {}){
                Icon(Icons.AutoMirrored.Filled.List, contentDescription = "")
            }
        }
    )
}

@Composable
fun ScaffoldDemo(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CustomAppBar()
        },
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(onClick = {}, selected = false, icon = {
                    Icon(Icons.Default.Star, contentDescription = "")
                })
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}){
                Icon(Icons.Default.Add, contentDescription = "")
            }
        },
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun ScaffoldDemoPreview() {
    Emo1Theme {

        ScaffoldDemo()
    }

}
