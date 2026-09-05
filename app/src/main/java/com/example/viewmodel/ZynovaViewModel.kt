package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.ProjectRepository
import com.example.data.ZynovaStrings
import com.example.model.AppLanguage
import com.example.model.ScienceProject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object LanguageSelect : Screen("language_select")
    object Home : Screen("home")
    object Classes : Screen("classes")
    object ProjectList : Screen("project_list")
    object ProjectDetails : Screen("project_details")
    object Bookmarks : Screen("bookmarks")
    object About : Screen("about")
}

data class ZynovaUiState(
    val currentScreen: Screen = Screen.Splash,
    val selectedClass: Int? = null, // null means all classes
    val selectedSubject: String = "All",
    val selectedDifficulty: String = "All",
    val searchQuery: String = "",
    val selectedProject: ScienceProject? = null,
    val walkthroughStep: Int = 0,
    val isWalkthroughActive: Boolean = false,
    val activeDetailsTab: Int = 0 // 0: Overview, 1: Materials, 2: Procedure, 3: Principle, 4: Viva
)

class ZynovaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProjectRepository(application)

    private val _uiState = MutableStateFlow(ZynovaUiState())
    val uiState: StateFlow<ZynovaUiState> = _uiState.asStateFlow()

    private val _userMessage = MutableSharedFlow<String>()
    val userMessage: SharedFlow<String> = _userMessage.asSharedFlow()

    val currentLanguage: StateFlow<AppLanguage> = repository.selectedLanguage
    val isFirstLaunch: StateFlow<Boolean> = repository.isFirstLaunch

    val allProjects: StateFlow<List<ScienceProject>> = repository.projects
    val bookmarkedIds: StateFlow<Set<String>> = repository.bookmarkedIds
    val collectedMaterials: StateFlow<Set<String>> = repository.collectedMaterials

    // Filtered projects based on active query, class filter, subject, and difficulty (multilingual support)
    val filteredProjects: StateFlow<List<ScienceProject>> = combine(
        repository.projects,
        _uiState,
        currentLanguage
    ) { projects, state, lang ->
        projects.filter { p ->
            val matchClass = state.selectedClass == null || p.classLevel == state.selectedClass
            val matchSubject = state.selectedSubject == "All" ||
                    p.subject.equals(state.selectedSubject, ignoreCase = true) ||
                    ZynovaStrings.getLocalizedSubject(p.subject, lang).equals(state.selectedSubject, ignoreCase = true)
            val matchDifficulty = state.selectedDifficulty == "All" ||
                    p.difficulty.equals(state.selectedDifficulty, ignoreCase = true)

            val query = state.searchQuery.trim()
            val matchQuery = query.isBlank() ||
                    p.title.contains(query, ignoreCase = true) ||
                    (p.titleHi?.contains(query, ignoreCase = true) == true) ||
                    (p.titleOr?.contains(query, ignoreCase = true) == true) ||
                    p.subtitle.contains(query, ignoreCase = true) ||
                    (p.subtitleHi?.contains(query, ignoreCase = true) == true) ||
                    (p.subtitleOr?.contains(query, ignoreCase = true) == true) ||
                    p.subject.contains(query, ignoreCase = true) ||
                    p.scientificLaw.contains(query, ignoreCase = true) ||
                    (p.scientificLawHi?.contains(query, ignoreCase = true) == true) ||
                    (p.scientificLawOr?.contains(query, ignoreCase = true) == true) ||
                    p.tags.any { it.contains(query, ignoreCase = true) }

            matchClass && matchSubject && matchDifficulty && matchQuery
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Bookmarked projects list
    val bookmarkedProjects: StateFlow<List<ScienceProject>> = combine(
        repository.projects,
        repository.bookmarkedIds
    ) { projects, bookmarkSet ->
        projects.filter { bookmarkSet.contains(it.id) }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun navigateTo(screen: Screen) {
        _uiState.value = _uiState.value.copy(currentScreen = screen)
    }

    fun setLanguage(language: AppLanguage) {
        repository.setSelectedLanguage(language)
        viewModelScope.launch {
            _userMessage.emit(ZynovaStrings.languageSwitchedMsg(language))
        }
    }

    fun completeFirstLaunchLanguageSelection(language: AppLanguage) {
        repository.completeFirstLaunchLanguageSelection(language)
        _uiState.value = _uiState.value.copy(currentScreen = Screen.Home)
    }

    fun selectClass(classLevel: Int?) {
        _uiState.value = _uiState.value.copy(
            selectedClass = classLevel,
            currentScreen = Screen.ProjectList
        )
    }

    fun selectSubject(subject: String) {
        _uiState.value = _uiState.value.copy(selectedSubject = subject)
    }

    fun selectDifficulty(difficulty: String) {
        _uiState.value = _uiState.value.copy(selectedDifficulty = difficulty)
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun openProjectDetails(project: ScienceProject) {
        _uiState.value = _uiState.value.copy(
            selectedProject = project,
            activeDetailsTab = 0,
            walkthroughStep = 0,
            isWalkthroughActive = false,
            currentScreen = Screen.ProjectDetails
        )
    }

    fun setDetailsTab(tabIndex: Int) {
        _uiState.value = _uiState.value.copy(activeDetailsTab = tabIndex)
    }

    fun startWalkthrough() {
        _uiState.value = _uiState.value.copy(
            isWalkthroughActive = true,
            walkthroughStep = 0
        )
    }

    fun setWalkthroughStep(step: Int) {
        _uiState.value = _uiState.value.copy(walkthroughStep = step)
    }

    fun closeWalkthrough() {
        _uiState.value = _uiState.value.copy(isWalkthroughActive = false)
    }

    fun toggleBookmark(projectId: String) {
        val isNowBookmarked = !repository.isBookmarked(projectId)
        repository.toggleBookmark(projectId)
        val lang = currentLanguage.value
        viewModelScope.launch {
            if (isNowBookmarked) {
                _userMessage.emit(
                    when (lang) {
                        AppLanguage.ENGLISH -> "Added to Bookmarks ⭐"
                        AppLanguage.HINDI -> "बुकमार्क्स में जोड़ा गया ⭐"
                        AppLanguage.ODIA -> "ବୁକମାର୍କରେ ଯୋଡାଗଲା ⭐"
                    }
                )
            } else {
                _userMessage.emit(
                    when (lang) {
                        AppLanguage.ENGLISH -> "Removed from Bookmarks"
                        AppLanguage.HINDI -> "बुकमार्क्स से हटाया गया"
                        AppLanguage.ODIA -> "ବୁକମାର୍କରୁ ହଟାଗଲା"
                    }
                )
            }
        }
    }

    fun isBookmarked(projectId: String): Boolean {
        return repository.isBookmarked(projectId)
    }

    fun toggleMaterial(projectId: String, materialName: String) {
        repository.toggleMaterialCollected(projectId, materialName)
    }

    fun isMaterialCollected(projectId: String, materialName: String): Boolean {
        return repository.isMaterialCollected(projectId, materialName)
    }

    fun importProjectsJson(jsonString: String) {
        viewModelScope.launch {
            val result = repository.importProjectsFromJson(jsonString)
            if (result.isSuccess) {
                _userMessage.emit("Successfully imported ${result.getOrNull()} project(s)! 🎉")
            } else {
                _userMessage.emit("Failed to import JSON: ${result.exceptionOrNull()?.message}")
            }
        }
    }

    fun exportProjectsJson(): String {
        return repository.exportProjectsAsJson()
    }

    fun addNewCustomProject(project: ScienceProject) {
        viewModelScope.launch {
            val success = repository.addCustomProject(project)
            if (success) {
                _userMessage.emit("Project '${project.title}' added successfully! 🚀")
                openProjectDetails(project)
            } else {
                _userMessage.emit("Could not save project. Please check fields.")
            }
        }
    }

    fun getProjectCountForClass(classLevel: Int): Int {
        return allProjects.value.count { it.classLevel == classLevel }
    }
}

