package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.ZynovaStrings
import com.example.model.AppLanguage
import com.example.model.MaterialItem
import com.example.model.ProcedureStep
import com.example.model.ScienceProject
import com.example.model.VivaQuestion
import com.example.ui.components.ClassBadge
import com.example.ui.components.DifficultyBadge
import com.example.ui.components.LanguagePillButton
import com.example.ui.components.ProjectImage
import com.example.ui.components.ProjectImageHelper
import com.example.ui.components.SubjectBadge
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
import com.example.ui.theme.PurpleCyanGradient
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.viewmodel.Screen
import com.example.viewmodel.ZynovaViewModel

@Composable
fun ProjectDetailsScreen(
    viewModel: ZynovaViewModel,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val project = uiState.selectedProject

    if (project == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(DarkBackground),
            contentAlignment = Alignment.Center
        ) {
            Text("No project selected", color = TextWhite)
        }
        return
    }

    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val isBookmarked = bookmarkedIds.contains(project.id)

    var showWalkthroughModal by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .testTag("project_details_screen")
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Hero Image Header with Gradient Overlay & Action Icons
            item {
                HeroImageHeader(
                    project = project,
                    language = currentLanguage,
                    isBookmarked = isBookmarked,
                    onBackClick = { onNavigate(Screen.ProjectList) },
                    onLanguageClick = { onNavigate(Screen.LanguageSelect) },
                    onBookmarkToggle = { viewModel.toggleBookmark(project.id) }
                )
            }

            // Project Title & Subtitle Info
            item {
                ProjectMainInfoCard(
                    project = project,
                    language = currentLanguage,
                    onStartWalkthrough = { showWalkthroughModal = true }
                )
            }

            // 1. Objective Card
            item {
                SectionCard(
                    title = "🎯 ${ZynovaStrings.projectObjective(currentLanguage)}",
                    accentColor = NeonCyan
                ) {
                    Text(
                        text = project.getLocalizedObjective(currentLanguage),
                        fontSize = 14.sp,
                        color = TextWhite,
                        lineHeight = 22.sp
                    )
                }
            }

            // 2. Materials Required with interactive checklist
            item {
                MaterialsChecklistCard(
                    project = project,
                    language = currentLanguage,
                    viewModel = viewModel
                )
            }

            // 3. Step-by-Step Procedure
            item {
                ProcedureSectionCard(
                    procedure = project.procedure,
                    language = currentLanguage,
                    accentColor = NeonPurple
                )
            }

            // 4. Working Principle
            item {
                SectionCard(
                    title = "🔬 ${ZynovaStrings.workingPrinciple(currentLanguage)}",
                    accentColor = NeonPurpleLight
                ) {
                    val scientificLaw = project.getLocalizedScientificLaw(currentLanguage)
                    if (scientificLaw.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(DarkSurfaceVariant)
                                .border(1.dp, NeonPurple.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                .padding(12.dp)
                        ) {
                            Column {
                                Text(
                                    text = ZynovaStrings.scientificLaw(currentLanguage) + ":",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NeonPurpleLight
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = scientificLaw,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextWhite
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Text(
                        text = project.getLocalizedWorkingPrinciple(currentLanguage),
                        fontSize = 14.sp,
                        color = TextWhite,
                        lineHeight = 22.sp
                    )
                }
            }

            // 5. Result & Observations
            item {
                SectionCard(
                    title = "📊 ${ZynovaStrings.result(currentLanguage)}",
                    accentColor = NeonGreen
                ) {
                    Text(
                        text = project.getLocalizedResult(currentLanguage),
                        fontSize = 14.sp,
                        color = TextWhite,
                        lineHeight = 22.sp
                    )
                }
            }

            // 6. Conclusion
            item {
                SectionCard(
                    title = "💡 ${ZynovaStrings.conclusion(currentLanguage)}",
                    accentColor = NeonAmber
                ) {
                    Text(
                        text = project.getLocalizedConclusion(currentLanguage),
                        fontSize = 14.sp,
                        color = TextWhite,
                        lineHeight = 22.sp
                    )
                }
            }

            // 7. Viva Questions & Answers
            item {
                VivaQuestionsCard(
                    vivaList = project.vivaQuestions,
                    language = currentLanguage
                )
            }
        }

        // Fullscreen Walkthrough Mode Dialog
        if (showWalkthroughModal) {
            WalkthroughDialog(
                project = project,
                language = currentLanguage,
                onDismiss = { showWalkthroughModal = false }
            )
        }
    }
}

@Composable
private fun HeroImageHeader(
    project: ScienceProject,
    language: AppLanguage,
    isBookmarked: Boolean,
    onBackClick: () -> Unit,
    onLanguageClick: () -> Unit,
    onBookmarkToggle: () -> Unit
) {
    val title = project.getLocalizedTitle(language)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .background(DarkSurfaceVariant)
    ) {
        ProjectImage(
            imageSource = project.imageDrawableName,
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            DarkBackground.copy(alpha = 0.7f),
                            Color.Transparent,
                            DarkBackground.copy(alpha = 0.95f)
                        )
                    )
                )
        )

        // Top Navigation Action Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(DarkBackground.copy(alpha = 0.75f))
                    .clickable { onBackClick() }
                    .testTag("details_back_button"),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextWhite,
                    modifier = Modifier.size(22.dp)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                LanguagePillButton(
                    currentLanguage = language,
                    onClick = onLanguageClick
                )

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(DarkBackground.copy(alpha = 0.75f))
                        .clickable { onBookmarkToggle() }
                        .testTag("details_bookmark_button"),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) NeonAmber else TextWhite,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectMainInfoCard(
    project: ScienceProject,
    language: AppLanguage,
    onStartWalkthrough: () -> Unit
) {
    val title = project.getLocalizedTitle(language)
    val subtitle = project.getLocalizedSubtitle(language)
    val subject = ZynovaStrings.getLocalizedSubject(project.subject, language)
    val diffText = when (project.difficulty) {
        "Easy" -> ZynovaStrings.easy(language)
        "Medium" -> ZynovaStrings.medium(language)
        "Hard" -> ZynovaStrings.hard(language)
        else -> project.difficulty
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        // Tag row
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClassBadge(classLevel = project.classLevel)
            SubjectBadge(subject = subject)
            DifficultyBadge(difficulty = project.difficulty)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Black,
            color = TextWhite,
            lineHeight = 30.sp,
            modifier = Modifier.testTag("details_project_title")
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = TextMuted,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Metric info row: Time, Cost, Difficulty
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MetricCard(
                label = ZynovaStrings.timeRequired(language),
                value = project.estimatedTime,
                accentColor = NeonCyan,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                label = ZynovaStrings.estimatedCost(language),
                value = project.estimatedCost,
                accentColor = NeonAmber,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                label = ZynovaStrings.difficulty(language),
                value = diffText,
                accentColor = NeonGreen,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Walkthrough action button
        Button(
            onClick = onStartWalkthrough,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("start_walkthrough_button"),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(PurpleCyanGradient, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = TextWhite,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ZynovaStrings.interactiveWalkthrough(language),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    label: String,
    value: String,
    accentColor: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(68.dp),
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor,
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                fontSize = 10.sp,
                color = TextDark,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = accentColor
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun MaterialsChecklistCard(
    project: ScienceProject,
    language: AppLanguage,
    viewModel: ZynovaViewModel
) {
    val collectedSet by viewModel.collectedMaterials.collectAsState()
    val totalCount = project.materials.size
    val collectedCount = project.materials.count {
        collectedSet.contains("${project.id}:${it.name}")
    }

    SectionCard(
        title = "🧪 ${ZynovaStrings.materialsRequired(language)} ($collectedCount/$totalCount)",
        accentColor = NeonCyan
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            // Progress Bar for collected items
            if (totalCount > 0) {
                LinearProgressIndicator(
                    progress = { collectedCount.toFloat() / totalCount.toFloat() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = NeonGreen,
                    trackColor = DarkSurfaceVariant,
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            project.materials.forEach { item ->
                val isCollected = collectedSet.contains("${project.id}:${item.name}")
                val localizedItemName = when (language) {
                    AppLanguage.ENGLISH -> item.name
                    AppLanguage.HINDI -> item.nameHi ?: item.name
                    AppLanguage.ODIA -> item.nameOr ?: item.name
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isCollected) DarkSurfaceVariant.copy(alpha = 0.6f) else DarkSurfaceVariant)
                        .clickable { viewModel.toggleMaterial(project.id, item.name) }
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = if (isCollected) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                            contentDescription = "Toggle item",
                            tint = if (isCollected) NeonGreen else TextDark,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = localizedItemName,
                            fontSize = 13.sp,
                            fontWeight = if (isCollected) FontWeight.Normal else FontWeight.Medium,
                            color = if (isCollected) TextMuted else TextWhite
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(DarkBackground)
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = item.quantity,
                            fontSize = 11.sp,
                            color = NeonCyan
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProcedureSectionCard(
    procedure: List<ProcedureStep>,
    language: AppLanguage,
    accentColor: Color
) {
    SectionCard(
        title = "📋 ${ZynovaStrings.procedure(language)}",
        accentColor = accentColor
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            procedure.forEach { step ->
                val title = when (language) {
                    AppLanguage.ENGLISH -> step.title
                    AppLanguage.HINDI -> step.titleHi ?: step.title
                    AppLanguage.ODIA -> step.titleOr ?: step.title
                }
                val instruction = when (language) {
                    AppLanguage.ENGLISH -> step.instruction
                    AppLanguage.HINDI -> step.instructionHi ?: step.instruction
                    AppLanguage.ODIA -> step.instructionOr ?: step.instruction
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(NeonPurple),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${step.stepNumber}",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )
                            }
                            Text(
                                text = title,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = instruction,
                            fontSize = 13.sp,
                            color = TextMuted,
                            lineHeight = 20.sp
                        )

                        if (!step.proTip.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(NeonAmber.copy(alpha = 0.1f))
                                    .border(1.dp, NeonAmber.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .padding(8.dp)
                            ) {
                                Row(verticalAlignment = Alignment.Top) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = "Pro Tip",
                                        tint = NeonAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "${ZynovaStrings.proTip(language)}: ${step.proTip}",
                                        fontSize = 11.sp,
                                        color = NeonAmber,
                                        lineHeight = 16.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun VivaQuestionsCard(
    vivaList: List<VivaQuestion>,
    language: AppLanguage
) {
    val expandedMap = remember { mutableStateMapOf<Int, Boolean>() }

    SectionCard(
        title = "❓ ${ZynovaStrings.vivaQuestions(language)}",
        accentColor = NeonPink
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = ZynovaStrings.tapToRevealAnswer(language),
                fontSize = 12.sp,
                color = TextMuted
            )

            vivaList.forEachIndexed { index, viva ->
                val isExpanded = expandedMap[index] == true
                val question = when (language) {
                    AppLanguage.ENGLISH -> viva.question
                    AppLanguage.HINDI -> viva.questionHi ?: viva.question
                    AppLanguage.ODIA -> viva.questionOr ?: viva.question
                }
                val answer = when (language) {
                    AppLanguage.ENGLISH -> viva.answer
                    AppLanguage.HINDI -> viva.answerHi ?: viva.answer
                    AppLanguage.ODIA -> viva.answerOr ?: viva.answer
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedMap[index] = !isExpanded },
                    shape = RoundedCornerShape(14.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isExpanded) NeonPink.copy(alpha = 0.5f) else DarkBorder
                    ),
                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Q${index + 1}. $question",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextWhite,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                contentDescription = "Expand",
                                tint = NeonPink,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        AnimatedVisibility(
                            visible = isExpanded,
                            enter = fadeIn() + expandVertically(),
                            exit = fadeOut() + shrinkVertically()
                        ) {
                            Column {
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(DarkBackground.copy(alpha = 0.8f))
                                        .border(1.dp, NeonPink.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                        .padding(10.dp)
                                ) {
                                    Column {
                                        Text(
                                            text = "${ZynovaStrings.answer(language)}:",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = NeonPink
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = answer,
                                            fontSize = 13.sp,
                                            color = TextWhite,
                                            lineHeight = 19.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun WalkthroughDialog(
    project: ScienceProject,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    var currentStep by remember { mutableStateOf(0) }
    val totalSteps = project.procedure.size
    val title = project.getLocalizedTitle(language)

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBackground),
            color = DarkBackground
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = ZynovaStrings.interactiveWalkthrough(language),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = NeonCyan
                        )
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            color = TextMuted,
                            maxLines = 1
                        )
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.testTag("close_walkthrough_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = TextWhite
                        )
                    }
                }

                // Step Progress Indicator
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${ZynovaStrings.step(language)} ${currentStep + 1} / $totalSteps",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = NeonPurpleLight
                        )
                        Text(
                            text = "${((currentStep + 1) * 100) / totalSteps}% ${ZynovaStrings.completed(language)}",
                            fontSize = 12.sp,
                            color = TextDark
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    LinearProgressIndicator(
                        progress = { (currentStep + 1).toFloat() / totalSteps.toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = NeonPurple,
                        trackColor = DarkSurfaceVariant,
                    )
                }

                // Step Content Card
                if (currentStep < project.procedure.size) {
                    val step = project.procedure[currentStep]
                    val stepTitle = when (language) {
                        AppLanguage.ENGLISH -> step.title
                        AppLanguage.HINDI -> step.titleHi ?: step.title
                        AppLanguage.ODIA -> step.titleOr ?: step.title
                    }
                    val stepInstruction = when (language) {
                        AppLanguage.ENGLISH -> step.instruction
                        AppLanguage.HINDI -> step.instructionHi ?: step.instruction
                        AppLanguage.ODIA -> step.instructionOr ?: step.instruction
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(vertical = 16.dp),
                        shape = RoundedCornerShape(24.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            Brush.linearGradient(listOf(NeonPurple, NeonCyan))
                        ),
                        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(20.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp)
                                        .clip(CircleShape)
                                        .background(PurpleCyanGradient),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${step.stepNumber}",
                                        fontSize = 22.sp,
                                        fontWeight = FontWeight.Black,
                                        color = TextWhite
                                    )
                                }

                                Spacer(modifier = Modifier.height(14.dp))

                                Text(
                                    text = stepTitle,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextWhite
                                )

                                Spacer(modifier = Modifier.height(12.dp))

                                Text(
                                    text = stepInstruction,
                                    fontSize = 15.sp,
                                    color = TextWhite.copy(alpha = 0.9f),
                                    lineHeight = 24.sp
                                )
                            }

                            if (!step.proTip.isNullOrBlank()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(NeonAmber.copy(alpha = 0.15f))
                                        .border(1.dp, NeonAmber.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
                                        .padding(12.dp)
                                ) {
                                    Row(verticalAlignment = Alignment.Top) {
                                        Icon(
                                            imageVector = Icons.Default.Lightbulb,
                                            contentDescription = "Tip",
                                            tint = NeonAmber,
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "${ZynovaStrings.proTip(language)}: ${step.proTip}",
                                            fontSize = 12.sp,
                                            color = NeonAmber,
                                            lineHeight = 18.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Step Navigation Controls (Previous / Next)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (currentStep > 0) currentStep-- },
                        enabled = currentStep > 0,
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkSurfaceVariant,
                            disabledContainerColor = DarkSurfaceVariant.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier.testTag("walkthrough_prev_button")
                    ) {
                        Text("← ${ZynovaStrings.previous(language)}", color = if (currentStep > 0) TextWhite else TextDark)
                    }

                    if (currentStep < totalSteps - 1) {
                        Button(
                            onClick = { currentStep++ },
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                            modifier = Modifier.testTag("walkthrough_next_button")
                        ) {
                            Text("${ZynovaStrings.next(language)} →", color = TextWhite, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Button(
                            onClick = onDismiss,
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = NeonGreen),
                            modifier = Modifier.testTag("walkthrough_finish_button")
                        ) {
                            Text(ZynovaStrings.completeWalkthrough(language), color = DarkBackground, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

