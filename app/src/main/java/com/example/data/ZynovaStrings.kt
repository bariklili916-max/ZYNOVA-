package com.example.data

import com.example.model.AppLanguage

object ZynovaStrings {

    // Bottom Navigation & Core Tabs
    fun navHome(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Home"
        AppLanguage.HINDI -> "होम"
        AppLanguage.ODIA -> "ମୁଖ୍ୟ ପୃଷ୍ଠା"
    }

    fun navClasses(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Classes"
        AppLanguage.HINDI -> "कक्षाएं"
        AppLanguage.ODIA -> "ଶ୍ରେଣୀଗୁଡ଼ିକ"
    }

    fun navProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Projects"
        AppLanguage.HINDI -> "प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ପ୍ରୋଜେକ୍ଟଗୁଡ଼ିକ"
    }

    fun navBookmarks(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Bookmarks"
        AppLanguage.HINDI -> "सेव किए गए"
        AppLanguage.ODIA -> "ସଂରକ୍ଷିତ"
    }

    fun navAbout(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "About"
        AppLanguage.HINDI -> "प्रोफ़ाइल"
        AppLanguage.ODIA -> "ପ୍ରୋଫାଇଲ୍"
    }

    // Top Greeting & Headers
    fun greetingStudent(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Hi, Student! 👋"
        AppLanguage.HINDI -> "नमस्ते, विद्यार्थी! 👋"
        AppLanguage.ODIA -> "ନମସ୍କାର, ଛାତ୍ରଛାତ୍ରୀ! 👋"
    }

    fun greetingSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "What project are you looking for today?"
        AppLanguage.HINDI -> "आज आप कौन सा प्रोजेक्ट बनाना चाहते हैं?"
        AppLanguage.ODIA -> "ଆଜି ଆପଣ କେଉଁ ପ୍ରୋଜେକ୍ଟ କରିବାକୁ ଚାହୁଁଛନ୍ତି?"
    }

    // Search
    fun searchPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Search science projects, models, materials..."
        AppLanguage.HINDI -> "साइंस प्रोजेक्ट, मॉडल या सामग्री खोजें..."
        AppLanguage.ODIA -> "ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ, ମଡେଲ୍ କିମ୍ବା ସାମଗ୍ରୀ ଖୋଜନ୍ତୁ..."
    }

    fun searchBookmarksPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Search in your saved projects..."
        AppLanguage.HINDI -> "अपने सेव किए गए प्रोजेक्ट्स में खोजें..."
        AppLanguage.ODIA -> "ଆପଣଙ୍କ ସଂରକ୍ଷିତ ପ୍ରୋଜେକ୍ଟ ମଧ୍ୟରେ ଖୋଜନ୍ତୁ..."
    }

    fun searchFilterPlaceholder(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Filter by title, principle, keywords..."
        AppLanguage.HINDI -> "शीर्षक, सिद्धांत या कीवर्ड से फ़िल्टर करें..."
        AppLanguage.ODIA -> "ଶୀର୍ଷକ, ନୀତି ବା ଶବ୍ଦ ଦ୍ୱାରା ଖୋଜନ୍ତୁ..."
    }

    // Quick Category Cards
    fun allProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "All Projects"
        AppLanguage.HINDI -> "सभी प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ସମସ୍ତ ପ୍ରୋଜେକ୍ଟ"
    }

    fun classRangeSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "1 - 10 Class"
        AppLanguage.HINDI -> "कक्षा 1 - 10"
        AppLanguage.ODIA -> "ଶ୍ରେଣୀ ୧ - ୧୦"
    }

    fun popular(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Popular"
        AppLanguage.HINDI -> "लोकप्रिय"
        AppLanguage.ODIA -> "ଲୋକପ୍ରିୟ"
    }

    fun topRatedSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Top Rated"
        AppLanguage.HINDI -> "सर्वोत्तम रेटेड"
        AppLanguage.ODIA -> "ଶ୍ରେଷ୍ଠ ରେଟିଂ"
    }

    fun scienceTopics(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Science Topics"
        AppLanguage.HINDI -> "विज्ञान विषय"
        AppLanguage.ODIA -> "ବିଜ୍ଞାନ ବିଷୟ"
    }

    fun scienceTopicsSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Physics, Bio, Chem"
        AppLanguage.HINDI -> "भौतिक, जीव, रसायन"
        AppLanguage.ODIA -> "ପଦାର୍ଥ, ଜୀବ, ରସାୟନ"
    }

    fun myBookmarks(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "My Bookmarks"
        AppLanguage.HINDI -> "मेरे बुकमार्क्स"
        AppLanguage.ODIA -> "ମୋ ସଂରକ୍ଷିତ ପ୍ରୋଜେକ୍ଟ"
    }

    fun savedProjectsSubtitle(count: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> if (count > 0) "$count Saved" else "Saved Projects"
        AppLanguage.HINDI -> if (count > 0) "$count सेव किए गए" else "सेव किए गए प्रोजेक्ट्स"
        AppLanguage.ODIA -> if (count > 0) "$count ଟି ସଂରକ୍ଷିତ" else "ସଂରକ୍ଷିତ ପ୍ରୋଜେକ୍ଟ"
    }

    // Class Selection
    fun chooseYourClass(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Choose Your Class"
        AppLanguage.HINDI -> "अपनी कक्षा चुनें"
        AppLanguage.ODIA -> "ଆପଣଙ୍କ ଶ୍ରେଣୀ ବାଛନ୍ତୁ"
    }

    fun seeAllClasses(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "See All 1-10 →"
        AppLanguage.HINDI -> "सभी 1-10 देखें →"
        AppLanguage.ODIA -> "ସମସ୍ତ ୧-୧୦ ଦେଖନ୍ତୁ →"
    }

    fun selectClassTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Select Class"
        AppLanguage.HINDI -> "कक्षा चुनें"
        AppLanguage.ODIA -> "ଶ୍ରେଣୀ ଚୟନ କରନ୍ତୁ"
    }

    fun selectClassSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Projects tailored for Class 1 to Class 10"
        AppLanguage.HINDI -> "कक्षा 1 से 10 के लिए विशेष प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ଶ୍ରେଣୀ ୧ ରୁ ୧୦ ପାଇଁ ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ"
    }

    fun allClassesTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "All Classes (1 to 10)"
        AppLanguage.HINDI -> "सभी कक्षाएं (1 से 10)"
        AppLanguage.ODIA -> "ସମସ୍ତ ଶ୍ରେଣୀ (୧ ରୁ ୧୦)"
    }

    fun exploreAllModels(count: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Explore all $count science models"
        AppLanguage.HINDI -> "सभी $count विज्ञान मॉडल देखें"
        AppLanguage.ODIA -> "ସମସ୍ତ $count ଟି ବିଜ୍ଞାନ ମଡେଲ୍ ଦେଖନ୍ତୁ"
    }

    fun classLabel(classNum: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Class $classNum"
        AppLanguage.HINDI -> "कक्षा $classNum"
        AppLanguage.ODIA -> "ଶ୍ରେଣୀ $classNum"
    }

    // Section Headers
    fun popularProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Popular Projects"
        AppLanguage.HINDI -> "लोकप्रिय प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ଲୋକପ୍ରିୟ ପ୍ରୋଜେକ୍ଟଗୁଡ଼ିକ"
    }

    fun popularProjectsSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Most loved by science teachers & students"
        AppLanguage.HINDI -> "शिक्षकों और छात्रों द्वारा सबसे अधिक पसंद किए गए"
        AppLanguage.ODIA -> "ଶିକ୍ଷକ ଏବଂ ଛାତ୍ରଛାତ୍ରୀଙ୍କ ଦ୍ୱାରା ସର୍ବାଧିକ ପସନ୍ଦ"
    }

    fun featuredModels(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Featured Models"
        AppLanguage.HINDI -> "विशेष मॉडल"
        AppLanguage.ODIA -> "ସ୍ୱତନ୍ତ୍ର ମଡେଲ୍"
    }

    fun featuredModelsSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Exhibition-ready projects with Viva Q&A"
        AppLanguage.HINDI -> "प्रदर्शनी के लिए तैयार प्रोजेक्ट्स और वाइवा प्रश्न"
        AppLanguage.ODIA -> "ପ୍ରଦର୍ଶନୀ ଉପଯୋଗୀ ପ୍ରୋଜେକ୍ଟ ଓ ଭାଇଭା ପ୍ରଶ୍ନୋତ୍ତର"
    }

    fun seeAll(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "See All"
        AppLanguage.HINDI -> "सभी देखें"
        AppLanguage.ODIA -> "ସବୁ ଦେଖନ୍ତୁ"
    }

    // Project Details Labels (MANDATORY REQUIREMENT 4)
    fun projectObjective(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Project Objective"
        AppLanguage.HINDI -> "प्रोजेक्ट का उद्देश्य"
        AppLanguage.ODIA -> "ପ୍ରୋଜେକ୍ଟ ଉଦ୍ଦେଶ୍ୟ"
    }

    fun materials(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Materials"
        AppLanguage.HINDI -> "सामग्री"
        AppLanguage.ODIA -> "ସାମଗ୍ରୀ"
    }

    fun materialsRequired(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Materials Required"
        AppLanguage.HINDI -> "आवश्यक सामग्री"
        AppLanguage.ODIA -> "ଆବଶ୍ୟକୀୟ ସାମଗ୍ରୀ"
    }

    fun procedure(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Procedure"
        AppLanguage.HINDI -> "बनाने की विधि"
        AppLanguage.ODIA -> "ପ୍ରଣାଳୀ"
    }

    fun stepByStepProcedure(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Step-by-Step Procedure"
        AppLanguage.HINDI -> "कदम-दर-कदम विधि"
        AppLanguage.ODIA -> "ପର୍ଯ୍ୟାୟକ୍ରମେ ପ୍ରଣାଳୀ"
    }

    fun workingPrinciple(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Working Principle"
        AppLanguage.HINDI -> "कार्य सिद्धांत"
        AppLanguage.ODIA -> "କାର୍ଯ୍ୟ ନୀତି"
    }

    fun scientificLaw(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Scientific Law & Theory"
        AppLanguage.HINDI -> "वैज्ञानिक नियम और सिद्धांत"
        AppLanguage.ODIA -> "ବୈଜ୍ଞାନିକ ନିୟମ ଓ ତତ୍ତ୍ୱ"
    }

    fun result(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Result"
        AppLanguage.HINDI -> "परिणाम"
        AppLanguage.ODIA -> "ଫଳାଫଳ"
    }

    fun resultAndObservation(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Result & Observation"
        AppLanguage.HINDI -> "परिणाम और प्रेक्षण"
        AppLanguage.ODIA -> "ଫଳାଫଳ ଏବଂ ନିରୀକ୍ଷଣ"
    }

    fun conclusion(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Conclusion"
        AppLanguage.HINDI -> "निष्कर्ष"
        AppLanguage.ODIA -> "ନିଷ୍କର୍ଷ"
    }

    fun conclusionAndTakeaway(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Conclusion & Takeaway"
        AppLanguage.HINDI -> "निष्कर्ष और सीख"
        AppLanguage.ODIA -> "ନିଷ୍କର୍ଷ ଏବଂ ଶିକ୍ଷା"
    }

    fun vivaQuestions(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Viva Questions"
        AppLanguage.HINDI -> "वाइवा प्रश्न"
        AppLanguage.ODIA -> "ଭାଇଭା ପ୍ରଶ୍ନୋତ୍ତର"
    }

    fun vivaQuestionsAndAnswers(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Viva Questions & Answers"
        AppLanguage.HINDI -> "मौखिक प्रश्न और उत्तर (Viva)"
        AppLanguage.ODIA -> "ମୌଖିକ ପ୍ରଶ୍ନୋତ୍ତର (Viva)"
    }

    fun vivaSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Frequently asked questions in school viva & exhibitions"
        AppLanguage.HINDI -> "स्कूल वाइवा और विज्ञान प्रदर्शनी में पूछे जाने वाले प्रश्न"
        AppLanguage.ODIA -> "ବିଦ୍ୟାଳୟ ପରୀକ୍ଷା ଓ ବିଜ୍ଞାନ ମେଳାରେ ପଚରାଯାଉଥିବା ପ୍ରଶ୍ନ"
    }

    fun tapToRevealAnswer(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Tap to reveal answer"
        AppLanguage.HINDI -> "उत्तर देखने के लिए टैप करें"
        AppLanguage.ODIA -> "ଉତ୍ତର ଦେଖିବା ପାଇଁ ଟ୍ୟାପ୍ କରନ୍ତୁ"
    }

    fun answerLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Answer"
        AppLanguage.HINDI -> "उत्तर"
        AppLanguage.ODIA -> "ଉତ୍ତର"
    }

    fun questionLabel(index: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Question $index"
        AppLanguage.HINDI -> "प्रश्न $index"
        AppLanguage.ODIA -> "ପ୍ରଶ୍ନ $index"
    }

    fun proTip(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Pro Tip"
        AppLanguage.HINDI -> "उपयोगी टिप"
        AppLanguage.ODIA -> "ଗୁରୁତ୍ୱପୂର୍ଣ୍ଣ ଟିପ୍"
    }

    fun stepLabel(stepNum: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Step $stepNum"
        AppLanguage.HINDI -> "चरण $stepNum"
        AppLanguage.ODIA -> "ପର୍ଯ୍ୟାୟ $stepNum"
    }

    fun startWalkthrough(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Start Presentation Mode"
        AppLanguage.HINDI -> "प्रस्तुति मोड शुरू करें"
        AppLanguage.ODIA -> "ଉପସ୍ଥାପନ ମୋଡ୍ ଆରମ୍ଭ କରନ୍ତୁ"
    }

    fun interactiveWalkthrough(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Interactive Step Walkthrough"
        AppLanguage.HINDI -> "चरण-दर-चरण इंटरेक्टिव गाइड"
        AppLanguage.ODIA -> "ପର୍ଯ୍ୟାୟକ୍ରମେ ଉପସ୍ଥାପନ ଗାଇଡ୍"
    }

    fun itemsCollected(collected: Int, total: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "$collected of $total items collected"
        AppLanguage.HINDI -> "$total में से $collected सामग्री तैयार"
        AppLanguage.ODIA -> "$total ରୁ $collected ସାମଗ୍ରୀ ସଂଗୃହୀତ"
    }

    fun allMaterialsReady(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "All materials collected! Ready to build 🚀"
        AppLanguage.HINDI -> "सभी सामग्री तैयार! प्रोजेक्ट शुरू करें 🚀"
        AppLanguage.ODIA -> "ସମସ୍ତ ସାମଗ୍ରୀ ପ୍ରସ୍ତୁତ! ଆରମ୍ଭ କରନ୍ତୁ 🚀"
    }

    fun tapToMarkCollected(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Tap items to check off as you gather them"
        AppLanguage.HINDI -> "सामग्री इकट्ठा करते समय टिक करने के लिए टैप करें"
        AppLanguage.ODIA -> "ସାମଗ୍ରୀ ସଂଗ୍ରହ କରିବା ସମୟରେ ଚିହ୍ନଟ କରନ୍ତୁ"
    }

    // Difficulties & Badges
    fun difficulty(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Difficulty"
        AppLanguage.HINDI -> "कठिनाई"
        AppLanguage.ODIA -> "କଠିନତା"
    }

    fun difficultyEasy(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Easy"
        AppLanguage.HINDI -> "सरल"
        AppLanguage.ODIA -> "ସହଜ"
    }

    fun difficultyMedium(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Medium"
        AppLanguage.HINDI -> "मध्यम"
        AppLanguage.ODIA -> "ମଧ୍ୟମ"
    }

    fun difficultyHard(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Hard"
        AppLanguage.HINDI -> "उन्नत"
        AppLanguage.ODIA -> "କଠିନ"
    }

    // Subjects
    fun subjectAll(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "All"
        AppLanguage.HINDI -> "सभी"
        AppLanguage.ODIA -> "ସମସ୍ତ"
    }

    fun subjectPhysics(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Physics"
        AppLanguage.HINDI -> "भौतिक विज्ञान"
        AppLanguage.ODIA -> "ପଦାର୍ଥ ବିଜ୍ଞାନ"
    }

    fun subjectChemistry(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Chemistry"
        AppLanguage.HINDI -> "रसायन विज्ञान"
        AppLanguage.ODIA -> "ରସାୟନ ବିଜ୍ଞାନ"
    }

    fun subjectBiology(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Biology"
        AppLanguage.HINDI -> "जीव विज्ञान"
        AppLanguage.ODIA -> "ଜୀବ ବିଜ୍ଞାନ"
    }

    fun subjectEnvironment(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Environment"
        AppLanguage.HINDI -> "पर्यावरण"
        AppLanguage.ODIA -> "ପରିବେଶ ବିଜ୍ଞାନ"
    }

    fun subjectElectronics(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Electronics"
        AppLanguage.HINDI -> "इलेक्ट्रॉनिक्स"
        AppLanguage.ODIA -> "ଇଲେକ୍ଟ୍ରୋନିକ୍ସ"
    }

    fun subjectAstronomy(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Astronomy"
        AppLanguage.HINDI -> "खगोल विज्ञान"
        AppLanguage.ODIA -> "ମହାକାଶ ବିଜ୍ଞାନ"
    }

    fun getLocalizedSubject(subject: String, lang: AppLanguage): String {
        return when (subject.lowercase()) {
            "physics" -> subjectPhysics(lang)
            "chemistry" -> subjectChemistry(lang)
            "biology" -> subjectBiology(lang)
            "environment", "environmental" -> subjectEnvironment(lang)
            "electronics" -> subjectElectronics(lang)
            "astronomy" -> subjectAstronomy(lang)
            else -> subject
        }
    }

    // Bookmarks Screen
    fun savedProjectsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Saved Projects"
        AppLanguage.HINDI -> "सेव किए गए प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ସଂରକ୍ଷିତ ପ୍ରୋଜେକ୍ଟଗୁଡ଼ିକ"
    }

    fun savedOfflineSubtitle(count: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "$count saved for offline reference"
        AppLanguage.HINDI -> "ऑफ़लाइन उपयोग के लिए $count प्रोजेक्ट सेव हैं"
        AppLanguage.ODIA -> "ଅଫଲାଇନ ବ୍ୟବହାର ପାଇଁ $count ଟି ସଂରକ୍ଷିତ ଅଛି"
    }

    fun noSavedProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "No Saved Projects Yet"
        AppLanguage.HINDI -> "अभी तक कोई प्रोजेक्ट सेव नहीं है"
        AppLanguage.ODIA -> "କୌଣସି ପ୍ରୋଜେକ୍ଟ ସଂରକ୍ଷିତ ନାହିଁ"
    }

    fun noSavedProjectsSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Bookmark projects to access materials, procedures, and viva questions instantly offline!"
        AppLanguage.HINDI -> "प्रोजेक्ट्स को बुकमार्क करें ताकि सामग्री, विधि और वाइवा प्रश्न कभी भी ऑफ़लाइन देख सकें!"
        AppLanguage.ODIA -> "ପ୍ରୋଜେକ୍ଟ ବୁକମାର୍କ କରନ୍ତୁ ଯାହାଦ୍ୱାରା ସାମଗ୍ରୀ, ପ୍ରଣାଳୀ ଓ ଭାଇଭା ପ୍ରଶ୍ନ ଅଫଲାଇନରେ ଦେଖିପାରିବେ!"
    }

    fun browseScienceProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Browse Science Projects"
        AppLanguage.HINDI -> "विज्ञान प्रोजेक्ट्स देखें"
        AppLanguage.ODIA -> "ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ ଖୋଜନ୍ତୁ"
    }

    // Language Selection & Settings
    fun languageSettings(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Language Settings"
        AppLanguage.HINDI -> "भाषा सेटिंग्स"
        AppLanguage.ODIA -> "ଭାଷା ସେଟିଂସ"
    }

    fun selectAppLanguage(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Select App Language"
        AppLanguage.HINDI -> "ऐप की भाषा चुनें"
        AppLanguage.ODIA -> "ଆପ୍ ଭାଷା ଚୟନ କରନ୍ତୁ"
    }

    fun currentLanguageLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Current Language"
        AppLanguage.HINDI -> "वर्तमान भाषा"
        AppLanguage.ODIA -> "ବର୍ତ୍ତମାନର ଭାଷା"
    }

    fun changeLanguage(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Change Language"
        AppLanguage.HINDI -> "भाषा बदलें"
        AppLanguage.ODIA -> "ଭାଷା ପରିବର୍ତ୍ତନ କରନ୍ତୁ"
    }

    fun languageSwitchedMsg(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Language changed to English 🇬🇧"
        AppLanguage.HINDI -> "भाषा बदलकर हिन्दी कर दी गई है 🇮🇳"
        AppLanguage.ODIA -> "ଭାଷା ଓଡ଼ିଆକୁ ପରିବର୍ତ୍ତନ କରାଗଲା 🇮🇳"
    }

    // First Launch Screen
    fun welcomeTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Welcome to ZYNOVA"
        AppLanguage.HINDI -> "ZYNOVA में आपका स्वागत है"
        AppLanguage.ODIA -> "ZYNOVA କୁ ସ୍ୱାଗତ"
    }

    fun welcomeSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Select your preferred language to begin your science journey"
        AppLanguage.HINDI -> "विज्ञान सीखने की यात्रा शुरू करने के लिए अपनी पसंदीदा भाषा चुनें"
        AppLanguage.ODIA -> "ଆପଣଙ୍କ ବିଜ୍ଞାନ ଶିକ୍ଷା ଆରମ୍ଭ କରିବା ପାଇଁ ପସନ୍ଦର ଭାଷା ବାଛନ୍ତୁ"
    }

    fun continueButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Continue"
        AppLanguage.HINDI -> "आगे बढ़ें"
        AppLanguage.ODIA -> "ଆଗକୁ ବଢ଼ନ୍ତୁ"
    }

    fun getStarted(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Explore Science Projects"
        AppLanguage.HINDI -> "विज्ञान प्रोजेक्ट्स देखें"
        AppLanguage.ODIA -> "ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ ଅନ୍ୱେଷଣ କରନ୍ତୁ"
    }

    fun canChangeAnytime(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "You can change language anytime in Settings / About screen."
        AppLanguage.HINDI -> "आप सेटिंग्स / अबाउट स्क्रीन से कभी भी भाषा बदल सकते हैं।"
        AppLanguage.ODIA -> "ଆପଣ ସେଟିଂସ ବା ଆବାଉଟ୍ ସ୍କ୍ରିନରୁ ଯେକୌଣସି ସମୟରେ ଭାଷା ବଦଳାଇ ପାରିବେ।"
    }

    // Key Features & About
    fun keyFeatures(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Key Features"
        AppLanguage.HINDI -> "मुख्य विशेषताएं"
        AppLanguage.ODIA -> "ମୁଖ୍ୟ ବୈଶିଷ୍ଟ୍ୟଗୁଡ଼ିକ"
    }

    fun aboutAppMission(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "ZYNOVA Version 1.0 is on a mission to make science education easy, practical, and super fun for every student from Class 1 to Class 10 with multilingual support."
        AppLanguage.HINDI -> "ZYNOVA का उद्देश्य कक्षा 1 से 10 के हर छात्र के लिए बहुभाषी समर्थन के साथ विज्ञान शिक्षा को आसान, व्यावहारिक और रोचक बनाना है।"
        AppLanguage.ODIA -> "ZYNOVA ର ଲକ୍ଷ୍ୟ ହେଉଛି ଶ୍ରେଣୀ ୧ ରୁ ୧୦ ପର୍ଯ୍ୟନ୍ତ ପ୍ରତ୍ୟେକ ଛାତ୍ରଛାତ୍ରୀଙ୍କ ପାଇଁ ବହୁଭାଷୀ ସୁବିଧା ସହିତ ବିଜ୍ଞାନ ଶିକ୍ଷାକୁ ସହଜ, ବ୍ୟବହାରିକ ଏବଂ ଆନନ୍ଦଦାୟକ କରିବା।"
    }

    // Comprehensive Helpers and Aliases for all Screens
    fun aboutZynova(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "About ZYNOVA"
        AppLanguage.HINDI -> "ZYNOVA के बारे में"
        AppLanguage.ODIA -> "ZYNOVA ବିଷୟରେ"
    }

    fun tagline(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Create. Learn. Present."
        AppLanguage.HINDI -> "बनाएं • सीखें • प्रस्तुत करें"
        AppLanguage.ODIA -> "ଗଢ଼ନ୍ତୁ • ଶିଖନ୍ତୁ • ଉପସ୍ଥାପନ କରନ୍ତୁ"
    }

    fun version(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "VERSION 1.0"
        AppLanguage.HINDI -> "संस्करण 1.0"
        AppLanguage.ODIA -> "ଭର୍ସନ ୧.୦"
    }

    fun aboutDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "ZYNOVA Version 1.0 is the first step towards smart learning. We are on a mission to make science education easy, practical, and super fun for every student from Class 1 to Class 10."
        AppLanguage.HINDI -> "ZYNOVA संस्करण 1.0 स्मार्ट लर्निंग की दिशा में पहला कदम है। हमारा मिशन कक्षा 1 से 10 तक के हर छात्र के लिए विज्ञान को आसान, व्यावहारिक और मजेदार बनाना है।"
        AppLanguage.ODIA -> "ZYNOVA ଭର୍ସନ ୧.୦ ହେଉଛି ସ୍ମାର୍ଟ ଶିକ୍ଷା ଦିଗରେ ଏକ ନୂତନ ପଦକ୍ଷେପ। ଶ୍ରେଣୀ ୧ ରୁ ୧୦ ପର୍ଯ୍ୟନ୍ତ ବିଜ୍ଞାନ ଶିକ୍ଷାକୁ ସହଜ ଓ ଆନନ୍ଦଦାୟକ କରିବା ଆମର ଲକ୍ଷ୍ୟ।"
    }

    fun builtForScienceStars(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Built for Science Stars! 🌟"
        AppLanguage.HINDI -> "विज्ञान के सितारों के लिए निर्मित! 🌟"
        AppLanguage.ODIA -> "ଭବିଷ୍ୟତର ବୈଜ୍ଞାନିକମାନଙ୍କ ପାଇଁ ନିର୍ମିତ! 🌟"
    }

    fun everyProjectContains(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Every project contains working principles, step-by-step procedures, and viva exam questions."
        AppLanguage.HINDI -> "हर प्रोजेक्ट में कार्य सिद्धांत, चरण-दर-चरण विधि और मौखिक परीक्षा प्रश्न शामिल हैं।"
        AppLanguage.ODIA -> "ପ୍ରତ୍ୟେକ ପ୍ରୋଜେକ୍ଟରେ କାର୍ଯ୍ୟ ନୀତି, ପର୍ଯ୍ୟାୟକ୍ରମେ ପ୍ରଣାଳୀ ଓ ଭାଇଭା ପ୍ରଶ୍ନୋତ୍ତର ରହିଛି।"
    }

    fun search(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Search & Filter"
        AppLanguage.HINDI -> "खोजें और फ़िल्टर करें"
        AppLanguage.ODIA -> "ଖୋଜନ୍ତୁ ଏବଂ ଫିଲ୍ଟର୍ କରନ୍ତୁ"
    }

    fun bookmarks(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Bookmarks & Favorites"
        AppLanguage.HINDI -> "बुकमार्क्स और पसंदीदा"
        AppLanguage.ODIA -> "ସଂରକ୍ଷିତ ଓ ପସନ୍ଦ"
    }

    fun projectData(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Project Data & Custom Uploads"
        AppLanguage.HINDI -> "प्रोजेक्ट डेटा और कस्टम अपलोड"
        AppLanguage.ODIA -> "ପ୍ରୋଜେକ୍ଟ ଡାଟା ଓ ଅପଲୋଡ୍"
    }

    fun addProject(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Add Project"
        AppLanguage.HINDI -> "नया प्रोजेक्ट जोड़ें"
        AppLanguage.ODIA -> "ପ୍ରୋଜେକ୍ଟ ଯୋଡନ୍ତୁ"
    }

    fun importJson(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Import JSON"
        AppLanguage.HINDI -> "JSON इम्पोर्ट करें"
        AppLanguage.ODIA -> "JSON ଆମଦାନୀ କରନ୍ତୁ"
    }

    fun exportDatabase(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Export Database JSON"
        AppLanguage.HINDI -> "डेटाबेस JSON एक्सपोर्ट करें"
        AppLanguage.ODIA -> "ଡାଟାବେସ୍ JSON ରପ୍ତାନି କରନ୍ତୁ"
    }

    fun presentationTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Exhibition Presentation Pro Tips"
        AppLanguage.HINDI -> "प्रदर्शनी प्रस्तुति के उपयोगी टिप्स"
        AppLanguage.ODIA -> "ବିଜ୍ଞାନ ମେଳା ଉପସ୍ଥାପନ ଟିପ୍ସ"
    }

    fun save(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Save"
        AppLanguage.HINDI -> "सहेजें"
        AppLanguage.ODIA -> "ସାଇତନ୍ତୁ"
    }

    fun cancel(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Cancel"
        AppLanguage.HINDI -> "रद्द करें"
        AppLanguage.ODIA -> "ବାତିଲ୍"
    }

    fun savedProjects(lang: AppLanguage): String = savedProjectsTitle(lang)

    fun savedForOffline(count: Int, lang: AppLanguage): String = savedOfflineSubtitle(count, lang)

    fun searchSavedPlaceholder(lang: AppLanguage): String = searchBookmarksPlaceholder(lang)

    fun noSavedSubtitle(lang: AppLanguage): String = noSavedProjectsSubtitle(lang)

    fun selectClass(lang: AppLanguage): String = selectClassTitle(lang)

    fun allClassesRange(lang: AppLanguage): String = allClassesTitle(lang)

    fun exploreModelsCount(count: Int, lang: AppLanguage): String = exploreAllModels(count, lang)

    fun projects(lang: AppLanguage): String = navProjects(lang)

    fun allScienceProjects(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "All Science Projects"
        AppLanguage.HINDI -> "सभी विज्ञान प्रोजेक्ट्स"
        AppLanguage.ODIA -> "ସମସ୍ତ ବିଜ୍ଞାନ ପ୍ରୋଜେକ୍ଟ"
    }

    fun modelsAvailable(count: Int, lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "$count models available"
        AppLanguage.HINDI -> "$count मॉडल उपलब्ध हैं"
        AppLanguage.ODIA -> "$count ଟି ମଡେଲ୍ ଉପଲବ୍ଧ"
    }

    fun modelsAvailable(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "models available"
        AppLanguage.HINDI -> "मॉडल उपलब्ध हैं"
        AppLanguage.ODIA -> "ଟି ମଡେଲ୍ ଉପଲବ୍ଧ"
    }

    fun allClasses(lang: AppLanguage): String = allClassesTitle(lang)

    fun filterPlaceholder(lang: AppLanguage): String = searchFilterPlaceholder(lang)

    fun all(lang: AppLanguage): String = subjectAll(lang)

    fun easy(lang: AppLanguage): String = difficultyEasy(lang)

    fun medium(lang: AppLanguage): String = difficultyMedium(lang)

    fun hard(lang: AppLanguage): String = difficultyHard(lang)

    fun noProjectsFound(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "No Science Projects Found"
        AppLanguage.HINDI -> "कोई प्रोजेक्ट नहीं मिला"
        AppLanguage.ODIA -> "କୌଣସି ପ୍ରୋଜେକ୍ଟ ମିଳିଲା ନାହିଁ"
    }

    fun noProjectsSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Try changing your search query, class, or difficulty filters."
        AppLanguage.HINDI -> "कृपया अपनी खोज, कक्षा या कठिनाई फ़िल्टर बदलकर देखें।"
        AppLanguage.ODIA -> "ଦୟାକରି ଖୋଜିବା ଶବ୍ଦ ବା ଫିଲ୍ଟର୍ ବଦଳାଇ ଚେଷ୍ଟା କରନ୍ତୁ।"
    }

    fun resetFilters(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Reset Filters"
        AppLanguage.HINDI -> "फ़िल्टर रीसेट करें"
        AppLanguage.ODIA -> "ଫିଲ୍ଟର୍ ରିସେଟ୍ କରନ୍ତୁ"
    }

    fun timeRequired(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Estimated Time"
        AppLanguage.HINDI -> "अनुमानित समय"
        AppLanguage.ODIA -> "ଆନୁମାନିକ ସମୟ"
    }

    fun estimatedCost(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Estimated Cost"
        AppLanguage.HINDI -> "अनुमानित लागत"
        AppLanguage.ODIA -> "ଆନୁମାନିକ ଖର୍ଚ୍ଚ"
    }

    fun answer(lang: AppLanguage): String = answerLabel(lang)

    fun step(stepNum: Int, lang: AppLanguage): String = stepLabel(stepNum, lang)

    fun step(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Step"
        AppLanguage.HINDI -> "चरण"
        AppLanguage.ODIA -> "ପର୍ଯ୍ୟାୟ"
    }

    fun completed(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Completed"
        AppLanguage.HINDI -> "पूर्ण"
        AppLanguage.ODIA -> "ସମ୍ପନ୍ନ"
    }

    fun previous(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Previous"
        AppLanguage.HINDI -> "पिछला"
        AppLanguage.ODIA -> "ପୂର୍ବବର୍ତ୍ତୀ"
    }

    fun next(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Next"
        AppLanguage.HINDI -> "अगला"
        AppLanguage.ODIA -> "ପରବର୍ତ୍ତୀ"
    }

    fun completeWalkthrough(lang: AppLanguage): String = when (lang) {
        AppLanguage.ENGLISH -> "Complete Presentation"
        AppLanguage.HINDI -> "प्रस्तुति पूर्ण करें"
        AppLanguage.ODIA -> "ଉପସ୍ଥାପନ ଶେଷ କରନ୍ତୁ"
    }
}

