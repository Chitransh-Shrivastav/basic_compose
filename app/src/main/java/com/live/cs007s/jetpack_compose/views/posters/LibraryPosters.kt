package com.live.cs007s.jetpack_compose.views.posters

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.statusBarsPadding
import com.live.cs007s.jetpack_compose.model.Poster
import com.live.cs007s.jetpack_compose.utils.NetworkImage
import com.live.cs007s.jetpack_compose.views.custom.StaggeredVerticalGrid
import com.live.cs007s.jetpack_compose.views.theme.JetpackComposeTheme

@Composable
fun LibraryPosters(
  modifier: Modifier = Modifier,
  posters: List<Poster>,
  selectPoster: (Long) -> Unit = {},
) {
  Column(
    modifier = modifier
      .verticalScroll(rememberScrollState())
      .statusBarsPadding()
      .background(MaterialTheme.colors.background)
  ) {
    StaggeredVerticalGrid(
      maxColumnWidth = 330.dp,
      modifier = Modifier.padding(4.dp)
    ) {
      posters.forEach { poster ->
        LibraryPoster(poster = poster, selectPoster = selectPoster)
      }
    }
  }
}

@Composable
private fun LibraryPoster(
  modifier: Modifier = Modifier,
  poster: Poster,
  selectPoster: (Long) -> Unit = {},
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(4.dp)
      .clickable(
        onClick = { selectPoster(poster.id) }
      ),
    color = MaterialTheme.colors.onBackground,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp)
  ) {
    ConstraintLayout(modifier = Modifier.padding(16.dp)) {
      val (image, title, content) = createRefs()
      NetworkImage(
        url = poster.poster,
        modifier = Modifier
          .constrainAs(image) {
            centerHorizontallyTo(parent)
            top.linkTo(parent.top)
            bottom.linkTo(title.top)
          }
          .height(112.dp)
          .aspectRatio(1.0f)
          .clip(CircleShape)
      )

      Text(
        text = poster.name,
        style = MaterialTheme.typography.h2,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .constrainAs(title) {
            centerHorizontallyTo(parent)
            top.linkTo(image.bottom)
          }
          .padding(8.dp)
      )

      Text(
        text = poster.playtime,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Center,
        modifier = Modifier
          .constrainAs(content) {
            centerHorizontallyTo(parent)
            top.linkTo(title.bottom)
          }
          .padding(horizontal = 8.dp)
      )
    }
  }
}

@Composable
@Preview(name = "LibraryPoster Light")
private fun LibraryPosterPreviewLight() {
  JetpackComposeTheme(darkTheme = false) {
    LibraryPoster(
      poster = Poster.mock()
    )
  }
}

@Composable
@Preview(name = "LibraryPoster Dark")
private fun LibraryPosterPreviewDark() {
  JetpackComposeTheme(darkTheme = true) {
    LibraryPoster(
      poster = Poster.mock()
    )
  }
}
