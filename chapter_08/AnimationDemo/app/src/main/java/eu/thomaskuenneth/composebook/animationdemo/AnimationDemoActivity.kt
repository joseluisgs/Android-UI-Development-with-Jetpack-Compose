package eu.thomaskuenneth.composebook.animationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
class AnimationDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // AnimatedVisibilityDemo()
            SingleValueAnimationDemo()
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun AnimatedVisibilityDemo() {
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            visible = !visible
        }) {
            Text(
                stringResource(
                    id = if (visible)
                        R.string.hide
                    else
                        R.string.show
                )
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(),
            exit = slideOutVertically()
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 32.dp)
                    .background(color = Color.Red)
                    .size(128.dp)
            )
        }
    }
}

@Composable
@Preview
fun SingleValueAnimationDemo() {
    var toggled by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (toggled)
            Color.White
        else
            Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            toggled = !toggled
        }) {
            Text(
                stringResource(R.string.toggle)
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 32.dp)
                .background(color = color)
                .size(128.dp)
        )
    }
}