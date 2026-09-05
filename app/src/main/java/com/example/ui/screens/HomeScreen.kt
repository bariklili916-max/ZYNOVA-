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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
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
fun HomeScreen(
    viewModel: ZynovaViewModel,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    val allProjects by viewModel.allProjects.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()

    val popularProjects = allProjects.filter { it.popular }
    val featuredProjects = allProjects.filter { it.featured }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 90.dp)
    ) {
        // Top Header
        item {
            HomeTopHeader(
                currentLanguage = currentLanguage,
                onLanguageClick = { onNavigate(Screen.LanguageSelect) },
                onMenuClick = { onNavigate(Screen.About) }
            )
        }

        // Search Bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                ZynovaSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { query ->
                        viewModel.updateSearchQuery(query)
                        if (query.isNotEmpty() && uiState.currentScreen !is Screen.ProjectList) {
                            onNavigate(Screen.ProjectList)
                        }
                    },
                    placeholder = ZynovaStrings.searchPlaceholder(currentLanguage),
                    onSearch = { onNavigate(Screen.ProjectList) }
                )
            }
        }

        // 4 Quick Access Category Cards
        item {
            QuickAccessCardsSection(
                language = currentLanguage,
                onAllProjectsClick = {
                    viewModel.selectClass(null)
                    onNavigate(Screen.ProjectList)
                },
                onPopularClick = {
                    viewModel.selectClass(null)
                    onNavigate(Screen.ProjectList)
                },
                onTopicsClick = {
                    onNavigate(Screen.Classes)
                },
                onBookmarksClick = {
                    onNavigate(Screen.Bookmarks)
                },
                bookmarkCount = bookmarkedIds.size
            )
        }

        // Choose Your Class Row
        item {
            ChooseYourClassSection(
                language = currentLanguage,
                selectedClass = uiState.selectedClass,
                onSelectClass = { classNum ->
                    viewModel.selectClass(classNum)
                    onNavigate(Screen.ProjectList)
                },
                onViewAllClasses = {
                    onNavigate(Screen.Classes)
                }
            )
        }

        // Popular Projects Section
        item {
            SectionHeader(
                title = ZynovaStrings.popularProjects(currentLanguage),
                subtitle = ZynovaStrings.popularProjectsSubtitle(currentLanguage),
                seeAllLabel = ZynovaStrings.seeAll(currentLanguage),
                onSeeAllClick = {
                    viewModel.selectClass(null)
                    onNavigate(Screen.ProjectList)
                }
            )
        }

        items(popularProjects) { project ->
            ProjectRowCard(
                project = project,
                language = currentLanguage,
                isBookmarked = bookmarkedIds.contains(project.id),
                onBookmarkToggle = { viewModel.toggleBookmark(project.id) },
                onClick = { viewModel.openProjectDetails(project) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
            )
        }

        // Featured Science Projects Banner
        if (featuredProjects.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                SectionHeader(
                    title = ZynovaStrings.featuredModels(currentLanguage),
                    subtitle = ZynovaStrings.featuredModelsSubtitle(currentLanguage),
                    seeAllLabel = ZynovaStrings.seeAll(currentLanguage),
                    onSeeAllClick = {
                        viewModel.selectClass(null)
                        onNavigate(Screen.ProjectList)
                    }
                )
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    items(featuredProjects) { proj ->
                        FeaturedProjectCard(
                            project = proj,
                            language = currentLanguage,
                            isBookmarked = bookmarkedIds.contains(proj.id),
                            onBookmarkToggle = { viewModel.toggleBookmark(proj.id) },
                            onClick = { viewModel.openProjectDetails(proj) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTopHeader(
    currentLanguage: AppLanguage,
    onLanguageClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ZYNOVA App Logo
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .border(1.5.dp, Brush.linearGradient(listOf(NeonPurple, NeonCyan)), RoundedCornerShape(12.dp))
                    .background(DarkSurfaceVariant)
                    .clickable { onMenuClick() }
                    .testTag("zynova_logo_header"),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.zynova_logo),
                    contentDescription = "ZYNOVA Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = "ZYNOVA",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = TextWhite,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = ZynovaStrings.greetingSubtitle(currentLanguage),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextMuted
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Language Switcher Pill
        LanguagePillButton(
            currentLanguage = currentLanguage,
            onClick = onLanguageClick
        )

        Spacer(modifier = Modifier.width(8.dp))

        // Student 3D Avatar
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .border(2.dp, Brush.linearGradient(listOf(NeonPurple, NeonCyan)), CircleShape)
                .background(DarkSurfaceVariant)
                .clickable { onMenuClick() }
                .testTag("student_avatar_button"),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.student_avatar),
                contentDescription = "Student Profile",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
private fun QuickAccessCardsSection(
    language: AppLanguage,
    onAllProjectsClick: () -> Unit,
    onPopularClick: () -> Unit,
    onTopicsClick: () -> Unit,
    onBookmarksClick: () -> Unit,
    bookmarkCount: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickCard(
                title = ZynovaStrings.allProjects(language),
                subtitle = ZynovaStrings.classRangeSubtitle(language),
                icon = Icons.Default.School,
                accentColor = NeonPurple,
                gradient = Brush.linearGradient(listOf(Color(0xFF2E1065), Color(0xFF1E1B4B))),
                modifier = Modifier.weight(1f),
                tag = "quick_all_projects",
                onClick = onAllProjectsClick
            )
            QuickCard(
                title = ZynovaStrings.popular(language),
                subtitle = ZynovaStrings.topRatedSubtitle(language),
                icon = Icons.Default.LocalFireDepartment,
                accentColor = NeonCoral,
                gradient = Brush.linearGradient(listOf(Color(0xFF4C0519), Color(0xFF1E1B4B))),
                modifier = Modifier.weight(1f),
                tag = "quick_popular_projects",
                onClick = onPopularClick
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickCard(
                title = ZynovaStrings.scienceTopics(language),
                subtitle = ZynovaStrings.scienceTopicsSubtitle(language),
                icon = Icons.Default.Science,
                accentColor = NeonCyan,
                gradient = Brush.linearGradient(listOf(Color(0xFF083344), Color(0xFF0F172A))),
                modifier = Modifier.weight(1f),
                tag = "quick_science_topics",
                onClick = onTopicsClick
            )
            QuickCard(
                title = ZynovaStrings.myBookmarks(language),
                subtitle = ZynovaStrings.savedProjectsSubtitle(bookmarkCount, language),
                icon = Icons.Default.Bookmark,
                accentColor = NeonAmber,
                gradient = Brush.linearGradient(listOf(Color(0xFF451A03), Color(0xFF0F172A))),
                modifier = Modifier.weight(1f),
                tag = "quick_bookmarks",
                onClick = onBookmarksClick
            )
        }
    }
}

@Composable
private fun QuickCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    gradient: Brush,
    modifier: Modifier = Modifier,
    tag: String,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(84.dp)
            .testTag(tag)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, accentColor.copy(alpha = 0.35f)),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(accentColor.copy(alpha = 0.2f))
                        .border(1.dp, accentColor.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = accentColor,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = subtitle,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextMuted,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
private fun ChooseYourClassSection(
    language: AppLanguage,
    selectedClass: Int?,
    onSelectClass: (Int) -> Unit,
    onViewAllClasses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = ZynovaStrings.chooseYourClass(language),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = ZynovaStrings.seeAllClasses(language),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = NeonCyan,
                modifier = Modifier
                    .clickable { onViewAllClasses() }
                    .testTag("see_all_classes_button")
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Horizontal list of classes 1 to 10
        val scrollState = rememberScrollState()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            val classColors = listOf(
                NeonPurple, NeonCoral, NeonGreen, NeonCyan,
                NeonPink, NeonBlue, NeonAmber, NeonPurpleLight,
                NeonGreen, NeonCyan
            )

            for (c in 1..10) {
                val color = classColors[(c - 1) % classColors.size]
                val isSelected = selectedClass == c
                ClassPill(
                    classNum = c,
                    language = language,
                    accentColor = color,
                    isSelected = isSelected,
                    onClick = { onSelectClass(c) }
                )
            }
        }
    }
}

@Composable
private fun ClassPill(
    classNum: Int,
    language: AppLanguage,
    accentColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .testTag("class_pill_$classNum")
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    if (isSelected) accentColor.copy(alpha = 0.35f) else DarkSurfaceCard
                )
                .border(
                    if (isSelected) 2.dp else 1.dp,
                    if (isSelected) accentColor else DarkBorder,
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$classNum",
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                color = if (isSelected) accentColor else TextWhite
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = ZynovaStrings.classLabel(classNum, language),
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) accentColor else TextMuted
        )
    }
}

@Composable
private fun SectionHeader(
    title: String,
    subtitle: String,
    seeAllLabel: String = "See All",
    onSeeAllClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextWhite
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = TextMuted
            )
        }
        Text(
            text = seeAllLabel,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = NeonCyan,
            modifier = Modifier.clickable { onSeeAllClick() }
        )
    }
}

@Composable
fun ProjectRowCard(
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
            .testTag("project_card_${project.id}")
            .clickable { onClick() },
        shape = RoundedCornerShape(18.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Project Image Thumbnail
            Box(
                modifier = Modifier
                    .size(76.dp)
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

            // Info column
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = ZynovaStrings.classLabel(project.classLevel, language),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NeonPurpleLight
                    )
                    Text(
                        text = "•",
                        fontSize = 11.sp,
                        color = TextDark
                    )
                    Text(
                        text = subject,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = NeonCyan
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

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
                    fontWeight = FontWeight.Normal,
                    color = TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    DifficultyBadge(difficulty = project.difficulty)
                    Text(
                        text = project.estimatedTime,
                        fontSize = 11.sp,
                        color = TextDark
                    )
                }
            }

            // Bookmark Toggle Button
            IconButton(
                onClick = onBookmarkToggle,
                modifier = Modifier.testTag("bookmark_button_${project.id}")
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                    contentDescription = "Bookmark Project",
                    tint = if (isBookmarked) NeonAmber else TextDark,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun FeaturedProjectCard(
    project: ScienceProject,
    language: AppLanguage,
    isBookmarked: Boolean,
    onBookmarkToggle: () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val title = project.getLocalizedTitle(language)
    val subtitle = project.getLocalizedSubtitle(language)

    Card(
        modifier = modifier
            .width(260.dp)
            .testTag("featured_card_${project.id}")
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp,
            Brush.linearGradient(listOf(NeonPurple.copy(alpha = 0.5f), NeonCyan.copy(alpha = 0.5f)))
        ),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
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
                                listOf(Color.Transparent, DarkSurfaceCard.copy(alpha = 0.9f))
                            )
                        )
                )

                // Top badges
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ClassBadge(classLevel = project.classLevel)

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(DarkBackground.copy(alpha = 0.7f))
                            .clickable { onBookmarkToggle() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isBookmarked) Icons.Filled.Bookmark else Icons.Filled.BookmarkBorder,
                            contentDescription = "Bookmark",
                            tint = if (isBookmarked) NeonAmber else TextWhite,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DifficultyBadge(difficulty = project.difficulty)
                    Text(
                        text = project.estimatedCost,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NeonCyan
                    )
                }
            }
        }
    }
}

