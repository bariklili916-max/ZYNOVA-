package com.example.model

data class MaterialItem(
    val name: String,
    val quantity: String,
    val isEssential: Boolean = true,
    val nameHi: String? = null,
    val nameOr: String? = null,
    val quantityHi: String? = null,
    val quantityOr: String? = null
) {
    fun getLocalizedName(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> nameHi ?: name
        AppLanguage.ODIA -> nameOr ?: name
        AppLanguage.ENGLISH -> name
    }

    fun getLocalizedQuantity(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> quantityHi ?: quantity
        AppLanguage.ODIA -> quantityOr ?: quantity
        AppLanguage.ENGLISH -> quantity
    }
}

data class ProcedureStep(
    val stepNumber: Int,
    val title: String,
    val instruction: String,
    val proTip: String? = null,
    val titleHi: String? = null,
    val titleOr: String? = null,
    val instructionHi: String? = null,
    val instructionOr: String? = null,
    val proTipHi: String? = null,
    val proTipOr: String? = null
) {
    fun getLocalizedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> titleHi ?: title
        AppLanguage.ODIA -> titleOr ?: title
        AppLanguage.ENGLISH -> title
    }

    fun getLocalizedInstruction(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> instructionHi ?: instruction
        AppLanguage.ODIA -> instructionOr ?: instruction
        AppLanguage.ENGLISH -> instruction
    }

    fun getLocalizedProTip(lang: AppLanguage): String? = when (lang) {
        AppLanguage.HINDI -> proTipHi ?: proTip
        AppLanguage.ODIA -> proTipOr ?: proTip
        AppLanguage.ENGLISH -> proTip
    }
}

data class VivaQuestion(
    val question: String,
    val answer: String,
    val questionHi: String? = null,
    val questionOr: String? = null,
    val answerHi: String? = null,
    val answerOr: String? = null
) {
    fun getLocalizedQuestion(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> questionHi ?: question
        AppLanguage.ODIA -> questionOr ?: question
        AppLanguage.ENGLISH -> question
    }

    fun getLocalizedAnswer(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> answerHi ?: answer
        AppLanguage.ODIA -> answerOr ?: answer
        AppLanguage.ENGLISH -> answer
    }
}

data class ScienceProject(
    val id: String,
    val title: String,
    val subtitle: String,
    val classLevel: Int, // 1 to 10
    val subject: String, // Physics, Chemistry, Biology, Environmental, Electronics, etc.
    val difficulty: String, // Easy, Medium, Hard
    val estimatedTime: String, // e.g. "30-45 mins"
    val estimatedCost: String, // e.g. "₹150-200"
    val imageDrawableName: String,
    val featured: Boolean = false,
    val popular: Boolean = false,
    val objective: String,
    val materials: List<MaterialItem>,
    val procedure: List<ProcedureStep>,
    val workingPrinciple: String,
    val scientificLaw: String,
    val result: String,
    val conclusion: String,
    val vivaQuestions: List<VivaQuestion>,
    val tags: List<String> = emptyList(),
    val isCustom: Boolean = false,
    // Multilingual Content (Hindi & Odia)
    val titleHi: String? = null,
    val titleOr: String? = null,
    val subtitleHi: String? = null,
    val subtitleOr: String? = null,
    val objectiveHi: String? = null,
    val objectiveOr: String? = null,
    val workingPrincipleHi: String? = null,
    val workingPrincipleOr: String? = null,
    val scientificLawHi: String? = null,
    val scientificLawOr: String? = null,
    val resultHi: String? = null,
    val resultOr: String? = null,
    val conclusionHi: String? = null,
    val conclusionOr: String? = null
) {
    fun getLocalizedTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> titleHi ?: title
        AppLanguage.ODIA -> titleOr ?: title
        AppLanguage.ENGLISH -> title
    }

    fun getLocalizedSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> subtitleHi ?: subtitle
        AppLanguage.ODIA -> subtitleOr ?: subtitle
        AppLanguage.ENGLISH -> subtitle
    }

    fun getLocalizedObjective(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> objectiveHi ?: objective
        AppLanguage.ODIA -> objectiveOr ?: objective
        AppLanguage.ENGLISH -> objective
    }

    fun getLocalizedWorkingPrinciple(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> workingPrincipleHi ?: workingPrinciple
        AppLanguage.ODIA -> workingPrincipleOr ?: workingPrinciple
        AppLanguage.ENGLISH -> workingPrinciple
    }

    fun getLocalizedScientificLaw(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> scientificLawHi ?: scientificLaw
        AppLanguage.ODIA -> scientificLawOr ?: scientificLaw
        AppLanguage.ENGLISH -> scientificLaw
    }

    fun getLocalizedResult(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> resultHi ?: result
        AppLanguage.ODIA -> resultOr ?: result
        AppLanguage.ENGLISH -> result
    }

    fun getLocalizedConclusion(lang: AppLanguage): String = when (lang) {
        AppLanguage.HINDI -> conclusionHi ?: conclusion
        AppLanguage.ODIA -> conclusionOr ?: conclusion
        AppLanguage.ENGLISH -> conclusion
    }
}

enum class SubjectCategory(val displayName: String, val iconName: String) {
    ALL("All", "science"),
    PHYSICS("Physics", "bolt"),
    CHEMISTRY("Chemistry", "science"),
    BIOLOGY("Biology", "eco"),
    ENVIRONMENT("Environment", "park"),
    ELECTRONICS("Electronics", "memory"),
    ASTRONOMY("Astronomy", "star")
}

