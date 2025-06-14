package com.cv_aziz.tampilan

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cv_aziz.R

@Composable
fun HomeScreen() {
    LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Foto Profil",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Paturohman Abdul Aziz",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Mahasiswa Ilmu Komputer | Guru Kejuruan",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F1F1))
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tentang Saya", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Mahasiswa Ilmu Komputer Universitas Yatsi Madani dengan keahlian di bidang editing visual, desain UI/UX, dan web development dasar. Berpengalaman menggunakan After Effect, Filmora, Capcut, dan Photoshop, serta menguasai Networking, simulasi dan manajemen jaringan dengan Cisco dan Winbox.",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        MediaSosialSection()
    }
}

@SuppressLint("UseKtx")
@Composable
fun MediaSosialSection() {
    val context = LocalContext.current

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F0FE))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Contact Information", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(12.dp))

            // Email
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:paturohmanabdulaziz01@gmail.com")
                    }
                    context.startActivity(intent)
                }
            ) {
                Icon(Icons.Default.Email, contentDescription = "Email")
                Spacer(modifier = Modifier.width(8.dp))
                Text("paturohmanabdulaziz01@gmail.com", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Instagram
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val url = "https://instagram.com/ell_zizz"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ig_icon),
                    contentDescription = "Instagram Icon",
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("@ell_zizz", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // GitHub
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    val url = "https://github.com/ellzizz"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.github_icon),
                    contentDescription = "GitHub Icon",
                    modifier = Modifier.size(20.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("github.com/ellzizz", fontSize = 14.sp)
            }
        }
    }
}
