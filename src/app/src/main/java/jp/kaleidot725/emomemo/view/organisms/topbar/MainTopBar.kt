package jp.kaleidot725.emomemo.view.organisms.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.kaleidot725.emomemo.view.atoms.Texts

@Composable
fun MainTopAppBar(
    title: String, modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior? = null
) {
    CenterAlignedTopAppBar(
        title = { Texts.TitleLarge(text = title) },
        navigationIcon = { Icon(Icons.Filled.Menu, "Menu", Modifier.padding(start = 16.dp)) },
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun MainTopAppBar_Preview() {
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }
    MainTopAppBar(
        title = "お買い物", modifier = Modifier, scrollBehavior = scrollBehavior
    )
}