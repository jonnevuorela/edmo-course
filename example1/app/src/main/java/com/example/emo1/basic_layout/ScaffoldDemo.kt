import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.emo1.ui.theme.Emo1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldDemo(modifier: Modifier = Modifier) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },

        // telineessä on valmis paikka topbarille
        topBar = {

            TopAppBar(title = {
                Text("title")
            }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(
                        // automirrored kääntää nuolen autom.
                        // toisinpäin, jos lukusuunta vaihtuu
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            })
        }, bottomBar = {
            BottomAppBar {
                NavigationBarItem(selected = true, onClick = {}, icon = {
                    Icon(imageVector = Icons.Default.Star, contentDescription = null)
                }, label = {
                    Text("Suosikit")
                })
                NavigationBarItem(selected = false, onClick = {}, icon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                }, label = {
                    Text("Hae")
                })


            }
        }

    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
private fun ScaffoldDemoPreview() {
    Emo1Theme {

        ScaffoldDemo()
    }

}
