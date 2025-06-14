package com.cv_aziz.tampilan

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ✅ Data class Skill
data class Skill(val title: String, val level: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkillScreen() {
    val skills = remember {
        mutableStateListOf(
            Skill("Video Editing (CapCut, Filmora, After Effect)", "Lanjutan"),
            Skill("Desain Grafis & UI/UX (Figma, Canva, Photoshop)", "Lanjutan"),
            Skill("Pemrograman Web (HTML, CSS, JavaScript, PHP)", "Menengah"),
            Skill("Manajemen Jaringan (Cisco Packet Tracer, Mikrotik)", "Lanjutan"),
            Skill("Database (MySQL / MariaDB)", "Menengah"),
            Skill("Python", "Menengah"),
            Skill("SEO & Optimasi Konten", "Menengah"),
            Skill("Pengembangan Aplikasi Mobile (Android Studio)", "Pemula")
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Skill") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            skills.forEachIndexed { index, skill ->
                SkillCard(skill.title, skill.level) { newLevel ->
                    skills[index] = skill.copy(level = newLevel)
                }
            }
        }
    }
}

@Composable
fun SkillCard(title: String, level: String, onLevelSelected: (String) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            SkillBadge(level) {
                showDialog = true
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {},
            title = {
                Text("Tingkat Keahlian", fontWeight = FontWeight.Bold)
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Pemula", "Menengah", "Lanjutan", "Ahli").forEach {
                        OutlinedButton(
                            onClick = {
                                onLevelSelected(it)
                                showDialog = false
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(it)
                        }
                    }
                }
            },
            shape = RoundedCornerShape(20.dp),
            containerColor = Color.White
        )
    }
}

@Composable
fun SkillBadge(level: String, onClick: () -> Unit) {
    val badgeColor = when (level) {
        "Pemula" -> Color(0xFFBDBDBD)
        "Menengah" -> Color(0xFF64B5F6)
        "Lanjutan" -> Color(0xFF4CAF50)
        "Ahli" -> Color(0xFFFF9800)
        else -> Color.Gray
    }

    Surface(
        color = badgeColor,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(start = 8.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = level,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}
