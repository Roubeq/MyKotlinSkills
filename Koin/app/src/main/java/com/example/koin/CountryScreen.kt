package com.example.koin

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.koin.models.CountriesItem

@Composable
fun CountryScreen(viewModel: MainViewModel) {
    val countries by viewModel.countries.collectAsState()

    LazyColumn() {
        items(countries) { country ->
            CountryItem(country)
        }
    }
}


@Composable
fun CountryItem(country: CountriesItem) {
    Card(Modifier.padding(8.dp), elevation = CardDefaults.cardElevation(4.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = country.flags.png,
                contentDescription = "${country.name} Flag",
                modifier = Modifier.size(80.dp)
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(text = country.name, fontWeight = FontWeight.Bold)
                Text(text = "Capital: ${country.capital}")
                Text(text = "Population: ${country.population}")
            }
        }
    }

}
