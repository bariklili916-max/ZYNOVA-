package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ZynovaStrings
import com.example.model.AppLanguage
import com.example.model.ScienceProject
import com.example.ui.components.ClassBadge
import com.example.ui.components.DifficultyBadge
import com.example.ui.components.LanguagePillButton
import com.example.ui.components.ProjectImage
import com.example.ui.components.ProjectImageHelper
import com.example.ui.components.SubjectBadge
import com.example.ui.components.ZynovaSearchBar
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkBorderGlow
import com.example.ui.theme.DarkSurfaceCard
import com.example.ui.theme.DarkSurfaceVariant
import com.example.ui.theme.NeonAmber
import com.example.ui.theme.NeonCyan
import com.example.ui.theme.NeonPurple
import com.example.ui.theme.NeonPurpleLight
import com.example.ui.theme.PurpleCyanGradient
import com.example.ui.theme.TextDark
import com.example.ui.theme.TextMuted
import com.example.ui.theme.TextWhite
import com.example.viewmodel.Screen
import com.example.viewmodel.ZynovaViewModel

@Composable
fun ProjectListScreen(
    viewModel: ZynovaViewModel,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val filteredProjects by viewModel.filteredProjects.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()

    val rawSubjects = listOf("All", "Physics", "Chemistry", "Biology", "Environment", "Electronics", "Astronomy")
    val rawDifficulties = listOf("All", "Easy", "Medium", "Hard")
    var showAddProjectDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .testTag("project_list_screen")
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top App Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, top = 12.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onNavigate(Screen.Home) },
                    modifier = Modifier.testTag("projects_back_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = TextWhite
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    val headerTitle = if (uiState.selectedClass != null) {
                        "${ZynovaStrings.projects(currentLanguage)} (${ZynovaStrings.classLabel(uiState.selectedClass!!, currentLanguage)})"
                    } else {
                        ZynovaStrings.allScienceProjects(currentLanguage)
                    }
                    Text(
                        text = headerTitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.testTag("project_list_title")
                    )
                    Text(
                        text = "${filteredProjects.size} ${ZynovaStrings.modelsAvailable(currentLanguage)}",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }

                // Add Project Button
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(NeonPurple.copy(alpha = 0.25f))
                        .border(1.dp, NeonPurple, RoundedCornerShape(10.dp))
                        .clickable { showAddProjectDialog = true }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .testTag("add_project_header_button")
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = NeonPurpleLight, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = ZynovaStrings.addProject(currentLanguage),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextWhite
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Class Clear / Switcher
                if (uiState.selectedClass != null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(NeonCyan.copy(alpha = 0.2f))
                            .border(1.dp, NeonCyan.copy(alpha = 0.4f), RoundedCornerShape(10.dp))
                            .clickable { viewModel.selectClass(null) }
                            .padding(horizontal = 8.dp, vertical = 5.dp)
                            .testTag("show_all_classes_chip")
                    ) {
                        Text(
                            text = "${ZynovaStrings.allClasses(currentLanguage)} ✕",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = NeonCyan
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                }

                LanguagePillButton(
                    currentLanguage = currentLanguage,
                    onClick = { onNavigate(Screen.LanguageSelect) }
                )
            }

        // Search Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            ZynovaSearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.updateSearchQuery(it) },
                placeholder = ZynovaStrings.filterPlaceholder(currentLanguage)
            )
        }

        // Subject Filter Chips
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(rawSubjects) { subject ->
                val isSelected = uiState.selectedSubject.equals(subject, ignoreCase = true)
                val localizedSubjectName = if (subject == "All") {
                    ZynovaStrings.all(currentLanguage)
                } else {
                    ZynovaStrings.getLocalizedSubject(subject, currentLanguage)
                }

                FilterChip(
                    selected = isSelected,
                    onClick = { viewModel.selectSubject(subject) },
                    label = {
                        Text(
                            text = localizedSubjectName,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) TextWhite else TextMuted
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NeonPurple,
                        containerColor = DarkSurfaceCard
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = if (isSelected) NeonPurple else DarkBorder,
                        selectedBorderColor = NeonCyan
                    ),
                    modifier = Modifier.testTag("filter_subject_$subject")
                )
            }
        }

        // Difficulty Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${ZynovaStrings.difficulty(currentLanguage)}:",
                fontSize = 12.sp,
                color = TextDark,
                fontWeight = FontWeight.Medium
            )
            rawDifficulties.forEach { diff ->
                val isSelected = uiState.selectedDifficulty.equals(diff, ignoreCase = true)
                val localizedDiff = when (diff) {
                    "All" -> ZynovaStrings.all(currentLanguage)
                    "Easy" -> ZynovaStrings.easy(currentLanguage)
                    "Medium" -> ZynovaStrings.medium(currentLanguage)
                    "Hard" -> ZynovaStrings.hard(currentLanguage)
                    else -> diff
                }
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (isSelected) NeonCyan.copy(alpha = 0.25f) else DarkSurfaceCard
                        )
                        .border(
                            1.dp,
                            if (isSelected) NeonCyan else DarkBorder,
                            RoundedCornerShape(8.dp)
                        )
                        .clickable { viewModel.selectDifficulty(diff) }
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("filter_diff_$diff")
                ) {
                    Text(
                        text = localizedDiff,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) NeonCyan else TextMuted
                    )
                }
            }
        }

        // Project List
        if (filteredProjects.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🔍",
                        fontSize = 42.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = ZynovaStrings.noProjectsFound(currentLanguage),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = ZynovaStrings.noProjectsSubtitle(currentLanguage),
                        fontSize = 13.sp,
                        color = TextMuted,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            viewModel.selectClass(null)
                            viewModel.selectSubject("All")
                            viewModel.selectDifficulty("All")
                            viewModel.updateSearchQuery("")
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple)
                    ) {
                        Text(ZynovaStrings.resetFilters(currentLanguage), color = TextWhite)
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredProjects) { project ->
                    DetailedProjectListCard(
                        project = project,
                        language = currentLanguage,
                        isBookmarked = bookmarkedIds.contains(project.id),
                        onBookmarkToggle = { viewModel.toggleBookmark(project.id) },
                        onClick = { viewModel.openProjectDetails(project) }
                    )
                }
            }
        }
    }

    // Add Custom Project Dialog
    if (showAddProjectDialog) {
        AddProjectDialog(
            language = currentLanguage,
            onDismiss = { showAddProjectDialog = false },
            onAdd = { newProj ->
                viewModel.addNewCustomProject(newProj)
                showAddProjectDialog = false
                viewModel.openProjectDetails(newProj)
            }
        )
    }
}
}

@Composable
fun DetailedProjectListCard(
    project: ScienceProject,
    language: AppLanguage,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = project.getLocalizedTitle(language)
    val subtitle = project.getLocalizedSubtitle(language)
    val subject = ZynovaStrings.getLocalizedSubject(project.subject, language)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("project_item_${project.id}")
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Project Image
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .border(1.dp, DarkBorderGlow, RoundedCornerShape(14.dp))
                        .background(DarkSurfaceVariant)
                ) {
                    ProjectImage(
                        imageSource = project.imageDrawableName,
                        contentDescription = title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                // Info
                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ClassBadge(classLevel = project.classLevel)
                        SubjectBadge(subject = subject)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        DifficultyBadge(difficulty = project.difficulty)
                        Text(
                            text = "⏱ ${project.estimatedTime}",
                            fontSize = 11.sp,
                            color = TextDark
                        )
                        Text(
                            text = "💰 ${project.estimatedCost}",
                            fontSize = 11.sp,
                            color = TextDark
                        )
                    }
                }

                // Bookmark Action
                IconButton(
                    onClick = onBookmarkToggle,
                    modifier = Modifier.testTag("bookmark_toggle_${project.id}")
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) NeonAmber else TextDark,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

