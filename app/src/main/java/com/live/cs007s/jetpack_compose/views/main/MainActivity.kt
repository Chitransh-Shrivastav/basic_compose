package com.live.cs007s.jetpack_compose.views.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.CompositionLocalProvider
import com.live.cs007s.jetpack_compose.views.root.RootViewModel
import com.live.cs007s.jetpack_compose.views.theme.JetpackComposeTheme
import com.skydoves.landscapist.coil.LocalCoilImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  @VisibleForTesting
  internal val viewModel: RootViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      CompositionLocalProvider(LocalCoilImageLoader provides viewModel.imageLoader) {

        JetpackComposeTheme {

          JetpackMainScreen()
        }
      }
    }
  }
}
