package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.model.AppLanguage
import com.example.model.MaterialItem
import com.example.model.ProcedureStep
import com.example.model.ScienceProject
import com.example.model.VivaQuestion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.json.JSONArray
import org.json.JSONObject

class ProjectRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("zynova_prefs", Context.MODE_PRIVATE)

    private val _projects = MutableStateFlow<List<ScienceProject>>(emptyList())
    val projects: StateFlow<List<ScienceProject>> = _projects.asStateFlow()

    private val _bookmarkedIds = MutableStateFlow<Set<String>>(emptySet())
    val bookmarkedIds: StateFlow<Set<String>> = _bookmarkedIds.asStateFlow()

    private val _collectedMaterials = MutableStateFlow<Set<String>>(emptySet())
    val collectedMaterials: StateFlow<Set<String>> = _collectedMaterials.asStateFlow()

    private val _selectedLanguage = MutableStateFlow(AppLanguage.ENGLISH)
    val selectedLanguage: StateFlow<AppLanguage> = _selectedLanguage.asStateFlow()

    private val _isFirstLaunch = MutableStateFlow(true)
    val isFirstLaunch: StateFlow<Boolean> = _isFirstLaunch.asStateFlow()

    init {
        loadLanguagePreference()
        loadBookmarks()
        loadCollectedMaterials()
        loadAllProjects()
    }

    private fun loadLanguagePreference() {
        val langCode = prefs.getString("selected_language", AppLanguage.ENGLISH.code)
        _selectedLanguage.value = AppLanguage.fromCode(langCode)
        val hasCompletedSetup = prefs.getBoolean("has_completed_language_setup", false)
        _isFirstLaunch.value = !hasCompletedSetup
    }

    fun setSelectedLanguage(language: AppLanguage) {
        _selectedLanguage.value = language
        prefs.edit()
            .putString("selected_language", language.code)
            .apply()
    }

    fun completeFirstLaunchLanguageSelection(language: AppLanguage) {
        _selectedLanguage.value = language
        _isFirstLaunch.value = false
        prefs.edit()
            .putString("selected_language", language.code)
            .putBoolean("has_completed_language_setup", true)
            .apply()
    }

    private fun loadBookmarks() {
        val saved = prefs.getStringSet("bookmarked_projects", emptySet()) ?: emptySet()
        _bookmarkedIds.value = saved
    }

    private fun loadCollectedMaterials() {
        val saved = prefs.getStringSet("collected_materials", emptySet()) ?: emptySet()
        _collectedMaterials.value = saved
    }

    private fun loadAllProjects() {
        val list = mutableListOf<ScienceProject>()
        // 1. Add bundled default projects
        list.addAll(DefaultProjects.sampleProjects)

        // 2. Load custom user-imported projects from JSON store
        val customJson = prefs.getString("custom_projects_json", null)
        if (!customJson.isNullOrEmpty()) {
            try {
                val parsedCustom = parseProjectsJson(customJson)
                list.addAll(parsedCustom)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        _projects.value = list
    }

    fun toggleBookmark(projectId: String) {
        val current = _bookmarkedIds.value.toMutableSet()
        if (current.contains(projectId)) {
            current.remove(projectId)
        } else {
            current.add(projectId)
        }
        _bookmarkedIds.value = current
        prefs.edit().putStringSet("bookmarked_projects", current).apply()
    }

    fun isBookmarked(projectId: String): Boolean {
        return _bookmarkedIds.value.contains(projectId)
    }

    fun toggleMaterialCollected(projectId: String, materialName: String) {
        val key = "$projectId:$materialName"
        val current = _collectedMaterials.value.toMutableSet()
        if (current.contains(key)) {
            current.remove(key)
        } else {
            current.add(key)
        }
        _collectedMaterials.value = current
        prefs.edit().putStringSet("collected_materials", current).apply()
    }

    fun isMaterialCollected(projectId: String, materialName: String): Boolean {
        val key = "$projectId:$materialName"
        return _collectedMaterials.value.contains(key)
    }

    fun addCustomProject(project: ScienceProject): Boolean {
        try {
            val currentList = _projects.value.toMutableList()
            // Check if already exists
            currentList.removeAll { it.id == project.id }
            currentList.add(0, project)
            _projects.value = currentList

            // Save custom list to prefs
            val customOnly = currentList.filter { it.isCustom }
            val jsonString = serializeProjectsToJson(customOnly)
            prefs.edit().putString("custom_projects_json", jsonString).apply()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    fun importProjectsFromJson(jsonString: String): Result<Int> {
        return try {
            val imported = parseProjectsJson(jsonString)
            if (imported.isEmpty()) {
                Result.failure(Exception("No valid projects found in JSON"))
            } else {
                val current = _projects.value.toMutableList()
                var count = 0
                imported.forEach { newProj ->
                    current.removeAll { it.id == newProj.id }
                    current.add(0, newProj.copy(isCustom = true))
                    count++
                }
                _projects.value = current

                val customOnly = current.filter { it.isCustom }
                val serialized = serializeProjectsToJson(customOnly)
                prefs.edit().putString("custom_projects_json", serialized).apply()
                Result.success(count)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun exportProjectsAsJson(): String {
        return serializeProjectsToJson(_projects.value)
    }

    private fun parseProjectsJson(jsonStr: String): List<ScienceProject> {
        val list = mutableListOf<ScienceProject>()
        val root = if (jsonStr.trim().startsWith("[")) {
            JSONArray(jsonStr)
        } else {
            val obj = JSONObject(jsonStr)
            obj.optJSONArray("projects") ?: JSONArray().put(obj)
        }

        for (i in 0 until root.length()) {
            val item = root.getJSONObject(i)
            val materialsArray = item.optJSONArray("materials") ?: JSONArray()
            val materials = mutableListOf<MaterialItem>()
            for (m in 0 until materialsArray.length()) {
                val mObj = materialsArray.getJSONObject(m)
                materials.add(
                    MaterialItem(
                        name = mObj.optString("name", "Material"),
                        quantity = mObj.optString("quantity", "1 unit"),
                        isEssential = mObj.optBoolean("isEssential", true),
                        nameHi = mObj.optString("nameHi").takeIf { it.isNotBlank() },
                        nameOr = mObj.optString("nameOr").takeIf { it.isNotBlank() },
                        quantityHi = mObj.optString("quantityHi").takeIf { it.isNotBlank() },
                        quantityOr = mObj.optString("quantityOr").takeIf { it.isNotBlank() }
                    )
                )
            }

            val procArray = item.optJSONArray("procedure") ?: JSONArray()
            val procedure = mutableListOf<ProcedureStep>()
            for (p in 0 until procArray.length()) {
                val pObj = procArray.getJSONObject(p)
                procedure.add(
                    ProcedureStep(
                        stepNumber = pObj.optInt("stepNumber", p + 1),
                        title = pObj.optString("title", "Step ${p + 1}"),
                        instruction = pObj.optString("instruction", ""),
                        proTip = pObj.optString("proTip").takeIf { it.isNotBlank() },
                        titleHi = pObj.optString("titleHi").takeIf { it.isNotBlank() },
                        titleOr = pObj.optString("titleOr").takeIf { it.isNotBlank() },
                        instructionHi = pObj.optString("instructionHi").takeIf { it.isNotBlank() },
                        instructionOr = pObj.optString("instructionOr").takeIf { it.isNotBlank() },
                        proTipHi = pObj.optString("proTipHi").takeIf { it.isNotBlank() },
                        proTipOr = pObj.optString("proTipOr").takeIf { it.isNotBlank() }
                    )
                )
            }

            val vivaArray = item.optJSONArray("vivaQuestions") ?: JSONArray()
            val vivaList = mutableListOf<VivaQuestion>()
            for (v in 0 until vivaArray.length()) {
                val vObj = vivaArray.getJSONObject(v)
                vivaList.add(
                    VivaQuestion(
                        question = vObj.optString("question", "Question"),
                        answer = vObj.optString("answer", "Answer"),
                        questionHi = vObj.optString("questionHi").takeIf { it.isNotBlank() },
                        questionOr = vObj.optString("questionOr").takeIf { it.isNotBlank() },
                        answerHi = vObj.optString("answerHi").takeIf { it.isNotBlank() },
                        answerOr = vObj.optString("answerOr").takeIf { it.isNotBlank() }
                    )
                )
            }

            val tagsArray = item.optJSONArray("tags") ?: JSONArray()
            val tags = mutableListOf<String>()
            for (t in 0 until tagsArray.length()) {
                tags.add(tagsArray.getString(t))
            }

            list.add(
                ScienceProject(
                    id = item.optString("id", "custom-${System.currentTimeMillis()}-$i"),
                    title = item.optString("title", "Custom Science Project"),
                    subtitle = item.optString("subtitle", "Science experimental model"),
                    classLevel = item.optInt("classLevel", 6).coerceIn(1, 10),
                    subject = item.optString("subject", "Physics"),
                    difficulty = item.optString("difficulty", "Medium"),
                    estimatedTime = item.optString("estimatedTime", "30 mins"),
                    estimatedCost = item.optString("estimatedCost", "₹100"),
                    imageDrawableName = item.optString("imageDrawableName", "proj_water_filter"),
                    featured = item.optBoolean("featured", false),
                    popular = item.optBoolean("popular", false),
                    objective = item.optString("objective", "Demonstrate scientific principle through hands-on model."),
                    materials = materials,
                    procedure = procedure,
                    workingPrinciple = item.optString("workingPrinciple", "Scientific principle demonstration."),
                    scientificLaw = item.optString("scientificLaw", "Scientific Law"),
                    result = item.optString("result", "Successful experiment observation."),
                    conclusion = item.optString("conclusion", "Project completes successfully."),
                    vivaQuestions = vivaList,
                    tags = tags,
                    isCustom = true,
                    titleHi = item.optString("titleHi").takeIf { it.isNotBlank() },
                    titleOr = item.optString("titleOr").takeIf { it.isNotBlank() },
                    subtitleHi = item.optString("subtitleHi").takeIf { it.isNotBlank() },
                    subtitleOr = item.optString("subtitleOr").takeIf { it.isNotBlank() },
                    objectiveHi = item.optString("objectiveHi").takeIf { it.isNotBlank() },
                    objectiveOr = item.optString("objectiveOr").takeIf { it.isNotBlank() },
                    workingPrincipleHi = item.optString("workingPrincipleHi").takeIf { it.isNotBlank() },
                    workingPrincipleOr = item.optString("workingPrincipleOr").takeIf { it.isNotBlank() },
                    scientificLawHi = item.optString("scientificLawHi").takeIf { it.isNotBlank() },
                    scientificLawOr = item.optString("scientificLawOr").takeIf { it.isNotBlank() },
                    resultHi = item.optString("resultHi").takeIf { it.isNotBlank() },
                    resultOr = item.optString("resultOr").takeIf { it.isNotBlank() },
                    conclusionHi = item.optString("conclusionHi").takeIf { it.isNotBlank() },
                    conclusionOr = item.optString("conclusionOr").takeIf { it.isNotBlank() }
                )
            )
        }
        return list
    }

    private fun serializeProjectsToJson(list: List<ScienceProject>): String {
        val jsonArray = JSONArray()
        list.forEach { p ->
            val obj = JSONObject().apply {
                put("id", p.id)
                put("title", p.title)
                put("subtitle", p.subtitle)
                put("classLevel", p.classLevel)
                put("subject", p.subject)
                put("difficulty", p.difficulty)
                put("estimatedTime", p.estimatedTime)
                put("estimatedCost", p.estimatedCost)
                put("imageDrawableName", p.imageDrawableName)
                put("featured", p.featured)
                put("popular", p.popular)
                put("objective", p.objective)
                put("workingPrinciple", p.workingPrinciple)
                put("scientificLaw", p.scientificLaw)
                put("result", p.result)
                put("conclusion", p.conclusion)
                p.titleHi?.let { put("titleHi", it) }
                p.titleOr?.let { put("titleOr", it) }
                p.subtitleHi?.let { put("subtitleHi", it) }
                p.subtitleOr?.let { put("subtitleOr", it) }
                p.objectiveHi?.let { put("objectiveHi", it) }
                p.objectiveOr?.let { put("objectiveOr", it) }
                p.workingPrincipleHi?.let { put("workingPrincipleHi", it) }
                p.workingPrincipleOr?.let { put("workingPrincipleOr", it) }
                p.scientificLawHi?.let { put("scientificLawHi", it) }
                p.scientificLawOr?.let { put("scientificLawOr", it) }
                p.resultHi?.let { put("resultHi", it) }
                p.resultOr?.let { put("resultOr", it) }
                p.conclusionHi?.let { put("conclusionHi", it) }
                p.conclusionOr?.let { put("conclusionOr", it) }

                val matArray = JSONArray()
                p.materials.forEach { m ->
                    matArray.put(JSONObject().apply {
                        put("name", m.name)
                        put("quantity", m.quantity)
                        put("isEssential", m.isEssential)
                        m.nameHi?.let { put("nameHi", it) }
                        m.nameOr?.let { put("nameOr", it) }
                        m.quantityHi?.let { put("quantityHi", it) }
                        m.quantityOr?.let { put("quantityOr", it) }
                    })
                }
                put("materials", matArray)

                val procArray = JSONArray()
                p.procedure.forEach { pr ->
                    procArray.put(JSONObject().apply {
                        put("stepNumber", pr.stepNumber)
                        put("title", pr.title)
                        put("instruction", pr.instruction)
                        put("proTip", pr.proTip ?: "")
                        pr.titleHi?.let { put("titleHi", it) }
                        pr.titleOr?.let { put("titleOr", it) }
                        pr.instructionHi?.let { put("instructionHi", it) }
                        pr.instructionOr?.let { put("instructionOr", it) }
                        pr.proTipHi?.let { put("proTipHi", it) }
                        pr.proTipOr?.let { put("proTipOr", it) }
                    })
                }
                put("procedure", procArray)

                val vivaArray = JSONArray()
                p.vivaQuestions.forEach { v ->
                    vivaArray.put(JSONObject().apply {
                        put("question", v.question)
                        put("answer", v.answer)
                        v.questionHi?.let { put("questionHi", it) }
                        v.questionOr?.let { put("questionOr", it) }
                        v.answerHi?.let { put("answerHi", it) }
                        v.answerOr?.let { put("answerOr", it) }
                    })
                }
                put("vivaQuestions", vivaArray)

                val tagsArray = JSONArray()
                p.tags.forEach { t -> tagsArray.put(t) }
                put("tags", tagsArray)
            }
            jsonArray.put(obj)
        }
        return jsonArray.toString(2)
    }
}

