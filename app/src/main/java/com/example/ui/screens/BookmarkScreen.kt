package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Science
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun BookmarkScreen(
    viewModel: ZynovaViewModel,
    onNavigate: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val bookmarkedProjects by viewModel.bookmarkedProjects.collectAsState()
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    var searchBookmarkText by remember { mutableStateOf("") }

    val filteredList = if (searchBookmarkText.isBlank()) {
        bookmarkedProjects
    } else {
        bookmarkedProjects.filter {
            it.title.contains(searchBookmarkText, ignoreCase = true) ||
            (it.titleHi?.contains(searchBookmarkText, ignoreCase = true) == true) ||
            (it.titleOr?.contains(searchBookmarkText, ignoreCase = true) == true) ||
            it.subject.contains(searchBookmarkText, ignoreCase = true) ||
            it.subtitle.contains(searchBookmarkText, ignoreCase = true)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBackground)
            .testTag("bookmark_screen")
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp, top = 12.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onNavigate(Screen.Home) },
                modifier = Modifier.testTag("bookmarks_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextWhite
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = ZynovaStrings.savedProjects(currentLanguage),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite,
                    modifier = Modifier.testTag("bookmarks_title")
                )
                Text(
                    text = ZynovaStrings.savedForOffline(bookmarkedProjects.size, currentLanguage),
                    fontSize = 12.sp,
                    color = TextMuted
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(NeonAmber.copy(alpha = 0.2f))
                    .border(1.dp, NeonAmber.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                Text(
                    text = "★ ${bookmarkedProjects.size}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = NeonAmber
                )
            }

            Spacer(modifier = Modifier.width(6.dp))

            LanguagePillButton(
                currentLanguage = currentLanguage,
                onClick = { onNavigate(Screen.LanguageSelect) }
            )
        }

        if (bookmarkedProjects.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                ZynovaSearchBar(
                    query = searchBookmarkText,
                    onQueryChange = { searchBookmarkText = it },
                    placeholder = ZynovaStrings.searchSavedPlaceholder(currentLanguage)
                )
            }
        }

        if (bookmarkedProjects.isEmpty()) {
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
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(DarkSurfaceVariant)
                            .border(1.dp, DarkBorder, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.BookmarkBorder,
                            contentDescription = "No bookmarks",
                            tint = TextDark,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = ZynovaStrings.noSavedProjects(currentLanguage),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextWhite
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = ZynovaStrings.noSavedSubtitle(currentLanguage),
                        fontSize = 13.sp,
                        color = TextMuted,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { onNavigate(Screen.ProjectList) },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                        modifier = Modifier.testTag("explore_from_bookmarks_button")
                    ) {
                        Text(ZynovaStrings.browseScienceProjects(currentLanguage), color = TextWhite, fontWeight = FontWeight.Bold)
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredList) { project ->
                    DetailedProjectListCard(
                        project = project,
                        language = currentLanguage,
                        isBookmarked = true,
                        onBookmarkToggle = { viewModel.toggleBookmark(project.id) },
                        onClick = { viewModel.openProjectDetails(project) }
                    )
                }
            }
        }
    }
}

