package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AllInclusive
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ZynovaStrings
import com.example.model.AppLanguage
import com.example.ui.components.LanguagePillButton
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkBorderGlow
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonBlue
import com.example.ui.theme.NeonCoral
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonGreen
import com.example.ui.theme.NeonPink
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonPurpleLight
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.viewmodel.Screen
import com.example.viewmodel.ZynovaViewModel

data class ClassInfo(
    val classNumber: Int,
    val gradeLevelEn: String,
    val gradeLevelHi: String,
    val gradeLevelOr: String,
    val popularTopicsEn: String,
    val popularTopicsHi: String,
    val popularTopicsOr: String,
    val accentColor: Color,
    val gradient: Brush
)

@Composable
fun ClassSelectionScreen(
    viewModel: ZynovaViewModel,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val allProjects by viewModel.allProjects.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()

    val classList = listOf(
        ClassInfo(1, "Primary", "प्राथमिक", "ପ୍ରାଥମିକ", "Shadows & Nature", "छाया और प्रकृति", "ଛାଇ ଓ ପ୍ରକୃତି", NeonPurple, Brush.linearGradient(listOf(Color(0xFF2E1065), Color(0xFF141B2D)))),
        ClassInfo(2, "Primary", "प्राथमिक", "ପ୍ରାଥମିକ", "Plants & Senses", "पौधे और इंद्रियां", "ଗଛ ଓ ଇନ୍ଦ୍ରିୟ", NeonCoral, Brush.linearGradient(listOf(Color(0xFF4C0519), Color(0xFF141B2D)))),
        ClassInfo(3, "Primary", "प्राथमिक", "ପ୍ରାଥମିକ", "Liquids & Density", "तरल और घनत्व", "ତରଳ ଓ ଘନତ୍ୱ", NeonGreen, Brush.linearGradient(listOf(Color(0xFF064E3B), Color(0xFF141B2D)))),
        ClassInfo(4, "Upper Primary", "उच्च प्राथमिक", "ଉଚ୍ଚ ପ୍ରାଥମିକ", "Air, Battery & Sound", "हवा, बैटरी और ध्वनि", "ବାୟୁ, ବ୍ୟାଟେରୀ ଓ ଧ୍ୱନି", NeonPink, Brush.linearGradient(listOf(Color(0xFF500724), Color(0xFF141B2D)))),
        ClassInfo(5, "Upper Primary", "उच्च प्राथमिक", "ଉଚ୍ଚ ପ୍ରାଥମିକ", "Circuits & Magnetism", "सर्किट और चुंबकत्व", "ସର୍କିଟ ଓ ଚୁମ୍ବକତ୍ୱ", NeonCyan, Brush.linearGradient(listOf(Color(0xFF083344), Color(0xFF141B2D)))),
        ClassInfo(6, "Middle School", "माध्यमिक", "ମଧ୍ୟମ ଶ୍ରେଣୀ", "Water, Optics & Motion", "जल, प्रकाश और गति", "ଜଳ, ଆଲୋକ ଓ ଗତି", NeonPurpleLight, Brush.linearGradient(listOf(Color(0xFF2E1065), Color(0xFF141B2D)))),
        ClassInfo(7, "Middle School", "माध्यमिक", "ମଧ୍ୟମ ଶ୍ରେଣୀ", "Digestive System & Heat", "पाचन तंत्र और ऊष्मा", "ପାଚନ ତନ୍ତ୍ର ଓ ଉତ୍ତାପ", NeonAmber, Brush.linearGradient(listOf(Color(0xFF451A03), Color(0xFF141B2D)))),
        ClassInfo(8, "Middle School", "माध्यमिक", "ମଧ୍ୟମ ଶ୍ରେଣୀ", "Solar Energy & Cells", "सौर ऊर्जा और कोशिकाएं", "ସୌର ଶକ୍ତି ଓ କୋଷ", NeonCoral, Brush.linearGradient(listOf(Color(0xFF4C0519), Color(0xFF141B2D)))),
        ClassInfo(9, "High School", "उच्च विद्यालय", "ଉଚ୍ଚ ବିଦ୍ୟାଳୟ", "Hydraulics & Gravitation", "हाइड्रोलिक्स और गुरुत्वाकर्षण", "ହାଇଡ୍ରୋଲିକ୍ସ ଓ ମାଧ୍ୟାକର୍ଷଣ", NeonCyan, Brush.linearGradient(listOf(Color(0xFF083344), Color(0xFF141B2D)))),
        ClassInfo(10, "High School", "उच्च विद्यालय", "ଉଚ୍ଚ ବିଦ୍ୟାଳୟ", "Smart Automation & Optics", "स्मार्ट ऑटोमेशन और प्रकाशिकी", "ସ୍ମାର୍ଟ ଅଟୋମେସନ ଓ ଆଲୋକ ବିଜ୍ଞାନ", NeonBlue, Brush.linearGradient(listOf(Color(0xFF172554), Color(0xFF141B2D))))
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .testTag("class_selection_screen")
    ) {
        // Top Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp, top = 12.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onNavigate(Screen.Home) },
                modifier = Modifier.testTag("classes_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to Home",
                    tint = TextWhite
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = ZynovaStrings.selectClass(currentLanguage),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
                Text(
                    text = ZynovaStrings.selectClassSubtitle(currentLanguage),
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }

            LanguagePillButton(
                currentLanguage = currentLanguage,
                onClick = { onNavigate(Screen.LanguageSelect) }
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // View All Projects Card
            item(span = { GridItemSpan(2) }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(76.dp)
                        .testTag("select_all_classes_card")
                        .clickable {
                            viewModel.selectClass(null)
                            onNavigate(Screen.ProjectList)
                        },
                    shape = RoundedCornerShape(18.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        Brush.horizontalGradient(listOf(NeonPurple, NeonCyan))
                    ),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(Color(0xFF2E1065).copy(alpha = 0.6f), Color(0xFF083344).copy(alpha = 0.6f))
                                )
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(NeonPurple.copy(alpha = 0.3f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AllInclusive,
                                    contentDescription = "All Classes",
                                    tint = NeonCyan,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            Column(modifier = Modifier.padding(start = 12.dp)) {
                                Text(
                                    text = ZynovaStrings.allClassesRange(currentLanguage),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                                Text(
                                    text = ZynovaStrings.exploreModelsCount(allProjects.size, currentLanguage),
                                    fontSize = 12.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Go",
                            tint = NeonCyan,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Grid cards for each class 1 to 10
            items(classList) { item ->
                val projectCount = allProjects.count { it.classLevel == item.classNumber }
                ClassGridCard(
                    info = item,
                    language = currentLanguage,
                    projectCount = projectCount,
                    onClick = {
                        viewModel.selectClass(item.classNumber)
                        onNavigate(Screen.ProjectList)
                    }
                )
            }
        }
    }
}

@Composable
private fun ClassGridCard(
    info: ClassInfo,
    language: AppLanguage,
    projectCount: Int,
    onClick: () -> Unit
) {
    val gradeLabel = when (language) {
        AppLanguage.ENGLISH -> info.gradeLevelEn
        AppLanguage.HINDI -> info.gradeLevelHi
        AppLanguage.ODIA -> info.gradeLevelOr
    }
    val topicsLabel = when (language) {
        AppLanguage.ENGLISH -> info.popularTopicsEn
        AppLanguage.HINDI -> info.popularTopicsHi
        AppLanguage.ODIA -> info.popularTopicsOr
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(148.dp)
            .testTag("class_grid_card_${info.classNumber}")
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, info.accentColor.copy(alpha = 0.4f)),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(info.gradient)
                .padding(14.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top row: Class Number & Grade Level Badge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${info.classNumber}",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        color = info.accentColor
                    )

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(DarkBackground.copy(alpha = 0.6f))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = gradeLabel,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextMuted
                        )
                    }
                }

                // Center & Bottom: Class Name & Topics
                Column {
                    Text(
                        text = ZynovaStrings.classLabel(info.classNumber, language),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = topicsLabel,
                        fontSize = 11.sp,
                        color = TextMuted,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (projectCount > 0) "$projectCount ${ZynovaStrings.projects(language)}" else ZynovaStrings.projects(language),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = info.accentColor
                    )
                }
            }
        }
    }
}

