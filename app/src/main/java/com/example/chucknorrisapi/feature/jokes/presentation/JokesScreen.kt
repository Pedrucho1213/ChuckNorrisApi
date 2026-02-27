package com.example.chucknorrisapi.feature.jokes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chucknorrisapi.R

@Composable
fun JokesRoute(viewModel: JokesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    JokesScreen(
        uiState = uiState,
        onRefreshClick = viewModel::loadRandomJoke,
        onQueryChanged = viewModel::onQueryChanged,
        onSearchClick = viewModel::onSearchClick,
        onCategorySelected = viewModel::onCategorySelected
    )
}

@Composable
private fun JokesScreen(
    uiState: JokesUiState,
    onRefreshClick: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onSearchClick: () -> Unit,
    onCategorySelected: (String?) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(R.string.title_jokes), style = MaterialTheme.typography.headlineSmall)
        if (uiState.isLoading) CircularProgressIndicator()

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                AssistChip(
                    onClick = { onCategorySelected(null) },
                    label = { Text(stringResource(R.string.category_all), color = MaterialTheme.colorScheme.onPrimary) }
                )
            }
            items(uiState.categories) { category ->
                AssistChip(
                    onClick = { onCategorySelected(category) },
                    label = { Text(category) }
                )
            }
        }

        Button(
            onClick = onRefreshClick,
            enabled = !uiState.isLoading
        ) {
            Text(stringResource(R.string.action_random))
        }

        uiState.joke?.let { joke ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(joke.text, modifier = Modifier.padding(16.dp))
            }
        }
        OutlinedTextField(
            value = uiState.query,
            onValueChange = onQueryChanged,
            label = { Text(stringResource(R.string.search_hint)) },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSearchClick,
            enabled = !uiState.isLoading
        ) {
            Text(stringResource(R.string.action_search))
        }
        uiState.errorRes?.let {
            Text(stringResource(it), color = MaterialTheme.colorScheme.error)
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.searchResults, key = {it.id}) { joke ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(joke.text, modifier = Modifier.padding(12.dp))
                }
        }
    }

}
}

@Preview(showBackground = true)
@Composable
private fun showScreen() {
    JokesScreen(
        uiState = JokesUiState(),
        onRefreshClick = {},
        onQueryChanged = {},
        onSearchClick = {},
        onCategorySelected = {}
    )
}