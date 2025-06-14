package com.cv_aziz.tampilan

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityScreen() {
    val context = LocalContext.current
    var selectedHari by rememberSaveable { mutableStateOf("") }
    var jamKegiatan by rememberSaveable { mutableStateOf("") }
    var namaKegiatan by rememberSaveable { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val hariList = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

    val daftarKegiatan = remember {
        mutableStateListOf(
            Triple("Senin", "18:20", "Komputer dan Masyarakat"),
            Triple("Senin", "20:00", "Mobile Computing"),
            Triple("Selasa", "18:00", "Desain dan pemograman Berorientasi Objek"),
            Triple("Selasa", "20:00", "Bahasa Inggrisn Lanjutan"),
            Triple("Rabu", "18:20", "Komputer Grafik"),
            Triple("Rabu", "20:00", "Sistem Kontrol"),
            Triple("Kamis", "18:20", "Machine Learning")
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Header seperti di SkillScreen
        CenterAlignedTopAppBar(
            title = { Text("Jadwal Kegiatan") },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White
            )
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Dropdown Hari
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = selectedHari,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Hari") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        hariList.forEach { hari ->
                            DropdownMenuItem(
                                text = { Text(hari) },
                                onClick = {
                                    selectedHari = hari
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Input Jam
                OutlinedTextField(
                    value = jamKegiatan,
                    onValueChange = { newValue ->
                        val filtered = newValue.filter { it.isDigit() }.take(4)
                        jamKegiatan = when {
                            filtered.length >= 3 -> filtered.substring(0, 2) + ":" + filtered.substring(2)
                            filtered.isNotEmpty() -> filtered
                            else -> ""
                        }
                    },
                    label = { Text("Waktu") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = namaKegiatan,
                onValueChange = { namaKegiatan = it },
                label = { Text("Nama Kegiatan") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (selectedHari.isNotBlank() &&
                        jamKegiatan.matches(Regex("\\d{2}:\\d{2}")) &&
                        namaKegiatan.isNotBlank()
                    ) {
                        daftarKegiatan.add(Triple(selectedHari, jamKegiatan, namaKegiatan))
                        selectedHari = ""
                        jamKegiatan = ""
                        namaKegiatan = ""
                        Toast.makeText(context, "Jadwal berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Lengkapi semua data dengan benar ⚠️", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah")
                Spacer(modifier = Modifier.width(4.dp))
                Text("Tambah Kegiatan")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Daftar Jadwal Kegiatan",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(daftarKegiatan) { (hari, jam, nama) ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("Hari: $hari", style = MaterialTheme.typography.bodyLarge)
                            Text("Jam : $jam", style = MaterialTheme.typography.bodyLarge)
                            Text("Kegiatan: $nama", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}
