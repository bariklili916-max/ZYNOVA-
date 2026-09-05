package com.example.model

enum class AppLanguage(
    val code: String,
    val displayName: String,
    val nativeName: String,
    val shortCode: String,
    val flag: String,
    val sampleText: String
) {
    ENGLISH(
        code = "en",
        displayName = "English",
        nativeName = "English",
        shortCode = "EN",
        flag = "🇬🇧",
        sampleText = "Learn science projects with easy steps and viva Q&A."
    ),
    HINDI(
        code = "hi",
        displayName = "Hindi",
        nativeName = "हिन्दी",
        shortCode = "HI",
        flag = "🇮🇳",
        sampleText = "कक्षा 1 से 10 के विज्ञान प्रोजेक्ट्स आसान चरणों में सीखें।"
    ),
    ODIA(
        code = "or",
        displayName = "Odia",
        nativeName = "ଓଡ଼ିଆ",
        shortCode = "OR",
        flag = "🇮🇳",
        sampleText = "ଶ୍ରେଣୀ ୧ ରୁ ୧୦ ପର୍ଯ୍ୟନ୍ତ ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ ସରଳ ଭାବରେ ଶିଖନ୍ତୁ।"
    );

    companion object {
        fun fromCode(code: String?): AppLanguage {
            return entries.firstOrNull { it.code.equals(code, ignoreCase = true) } ?: ENGLISH
        }
    }
}
