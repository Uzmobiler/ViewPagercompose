package uz.mobiler.viewpagercompose.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import uz.mobiler.viewpagercompose.utils.createItems

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerScreen() {
    val pagerState = rememberPagerState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
    ) {
        val items = createItems()

        HorizontalPager(
            count = items.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { currentPage ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = items[currentPage].title,
                    style = MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = items[currentPage].subtitle,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = items[currentPage].description,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }


        LaunchedEffect(Unit) {
            while(true) {
                yield()
                delay(2000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                    animationSpec = tween(600)
                )
            }
        }
    }
}