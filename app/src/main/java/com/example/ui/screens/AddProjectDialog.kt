package com.example.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
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
import com.example.ui.components.ProjectImage
import com.example.ui.components.ProjectImageHelper
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.DarkBorder
import com.example.ui.theme.DarkBorderGlow
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

data class EditableMaterial(
    var name: String = "",
    var quantity: String = "1 unit",
    var isEssential: Boolean = true
)

data class EditableStep(
    var title: String = "",
    var instruction: String = "",
    var proTip: String = ""
)

data class EditableViva(
    var question: String = "",
    var answer: String = ""
)

@Composable
fun AddProjectDialog(
    language: AppLanguage,
    onDismiss: () -> Unit,
    onAdd: (ScienceProject) -> Unit
) {
    val context = LocalContext.current

    // Core Form Fields
    var title by remember { mutableStateOf("") }
    var subtitle by remember { mutableStateOf("") }
    var classLevel by remember { mutableIntStateOf(6) }
    var subject by remember { mutableStateOf("Physics") }
    var difficulty by remember { mutableStateOf("Medium") }
    var estimatedTime by remember { mutableStateOf("30-45 mins") }
    var estimatedCost by remember { mutableStateOf("₹100 - ₹200") }
    var objective by remember { mutableStateOf("") }
    var workingPrinciple by remember { mutableStateOf("") }
    var scientificLaw by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var conclusion by remember { mutableStateOf("") }

    // Image state: Can be a local file path or preset drawable name
    var imageSource by remember { mutableStateOf("proj_water_filter") }
    var isCustomUploadedImage by remember { mutableStateOf(false) }

    // Dynamic Lists
    val materials = remember {
        mutableStateListOf(
            EditableMaterial("Baseboard / Cardboard", "1 pc", true),
            EditableMaterial("Connecting Wires / Tubes", "2 pcs", true)
        )
    }

    val procedureSteps = remember {
        mutableStateListOf(
            EditableStep("Base Construction", "Secure the main structure firmly on the baseboard.", "Ensure all joints are dry before testing."),
            EditableStep("Assembly & Testing", "Connect the components and verify the flow/circuit mechanism.", "Test in a well-lit area.")
        )
    }

    val vivaQuestions = remember {
        mutableStateListOf(
            EditableViva("What is the primary working principle of this project?", "It demonstrates the fundamental laws of energy transfer and scientific observation."),
            EditableViva("How can this project be applied in daily life?", "It helps in understanding cost-effective and sustainable everyday solutions.")
        )
    }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Image Picker Launcher
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val savedPath = ProjectImageHelper.saveImageToInternalStorage(context, it)
            imageSource = savedPath
            isCustomUploadedImage = true
        }
    }

    val subjectsList = listOf("Physics", "Chemistry", "Biology", "Environment", "Electronics", "Astronomy", "General Science")
    val difficultyList = listOf("Easy", "Medium", "Hard")
    val timePresets = listOf("20-30 mins", "45 mins", "1-2 hours", "2-3 hours")
    val costPresets = listOf("₹50 - ₹100", "₹100 - ₹200", "₹200 - ₹500", "Recycled / Low Cost")
    val presetImages = listOf("proj_water_filter", "proj_periscope", "proj_solar_cooker", "proj_digestive_model", "proj_electric_circuit")

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .testTag("add_project_dialog"),
            color = DarkBackground
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top App Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkSurfaceCard)
                        .border(1.dp, DarkBorder)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(38.dp)
                                .clip(CircleShape)
                                .background(DarkSurfaceVariant)
                                .testTag("close_add_project_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = TextWhite,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = ZynovaStrings.addProject(language),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )
                            Text(
                                text = "Class $classLevel • $subject",
                                fontSize = 12.sp,
                                color = NeonCyan
                            )
                        }
                    }

                    Button(
                        onClick = {
                            if (title.isBlank()) {
                                errorMessage = "Please enter a Project Title!"
                                return@Button
                            }

                            val formattedMaterials = materials
                                .filter { it.name.isNotBlank() }
                                .map { MaterialItem(name = it.name.trim(), quantity = it.quantity.trim().ifBlank { "1 unit" }, isEssential = it.isEssential) }
                                .ifEmpty { listOf(MaterialItem("Standard Project Materials", "1 set", true)) }

                            val formattedProcedure = procedureSteps
                                .filter { it.title.isNotBlank() || it.instruction.isNotBlank() }
                                .mapIndexed { idx, st ->
                                    ProcedureStep(
                                        stepNumber = idx + 1,
                                        title = st.title.trim().ifBlank { "Step ${idx + 1}" },
                                        instruction = st.instruction.trim().ifBlank { "Assemble components as per science instructions." },
                                        proTip = st.proTip.trim().takeIf { it.isNotBlank() }
                                    )
                                }
                                .ifEmpty { listOf(ProcedureStep(1, "Assembly & Execution", "Follow standard lab steps.")) }

                            val formattedViva = vivaQuestions
                                .filter { it.question.isNotBlank() }
                                .map { VivaQuestion(question = it.question.trim(), answer = it.answer.trim().ifBlank { "Working demonstration of scientific principles." }) }
                                .ifEmpty {
                                    listOf(
                                        VivaQuestion("What does this project demonstrate?", workingPrinciple.ifBlank { "Science experiment principles." })
                                    )
                                }

                            val newProject = ScienceProject(
                                id = "custom-${System.currentTimeMillis()}",
                                title = title.trim(),
                                subtitle = subtitle.trim().ifBlank { "Working Science Exhibition Model" },
                                classLevel = classLevel,
                                subject = subject,
                                difficulty = difficulty,
                                estimatedTime = estimatedTime.trim().ifBlank { "30-45 mins" },
                                estimatedCost = estimatedCost.trim().ifBlank { "₹100 - ₹200" },
                                imageDrawableName = imageSource,
                                objective = objective.trim().ifBlank { "To demonstrate scientific concepts through an engaging hands-on working model." },
                                materials = formattedMaterials,
                                procedure = formattedProcedure,
                                workingPrinciple = workingPrinciple.trim().ifBlank { "Based on fundamental scientific principles and observation." },
                                scientificLaw = scientificLaw.trim().ifBlank { "$subject Principles" },
                                result = result.trim().ifBlank { "The working model functioned successfully, validating the scientific hypothesis." },
                                conclusion = conclusion.trim().ifBlank { "The experiment successfully achieves its objective and provides practical insights." },
                                vivaQuestions = formattedViva,
                                tags = listOf(subject, difficulty, "Class $classLevel", "Custom Project"),
                                isCustom = true
                            )

                            onAdd(newProject)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.testTag("save_project_button")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp), tint = TextWhite)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(ZynovaStrings.save(language), color = TextWhite, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                // Error Message banner
                if (errorMessage != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(NeonCoral.copy(alpha = 0.2f))
                            .border(1.dp, NeonCoral)
                            .padding(12.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = errorMessage ?: "", color = NeonCoral, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                            IconButton(onClick = { errorMessage = null }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Close, contentDescription = "Dismiss", tint = NeonCoral, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }

                // Scrollable Form Content
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // SECTION 1: PROJECT IDENTITY & IMAGE
                    item {
                        FormSectionCard(
                            title = "Project Identity & Cover Image",
                            icon = Icons.Default.Science,
                            accentColor = NeonCyan
                        ) {
                            // Project Title
                            OutlinedTextField(
                                value = title,
                                onValueChange = {
                                    title = it
                                    if (errorMessage != null) errorMessage = null
                                },
                                label = { Text("Project Title *", color = TextMuted) },
                                placeholder = { Text("e.g. Smart Solar Water Purifier", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_project_title"),
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Subtitle
                            OutlinedTextField(
                                value = subtitle,
                                onValueChange = { subtitle = it },
                                label = { Text("Tagline / Subtitle", color = TextMuted) },
                                placeholder = { Text("e.g. Eco-friendly distillation model for exhibitions", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_project_subtitle"),
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(14.dp))

                            // Project Image Section
                            Text(
                                text = "Project Image Upload & Presets:",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextWhite
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Preview and Upload button
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(90.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .border(2.dp, Brush.linearGradient(listOf(NeonPurple, NeonCyan)), RoundedCornerShape(14.dp))
                                        .background(DarkSurfaceVariant)
                                ) {
                                    ProjectImage(
                                        imageSource = imageSource,
                                        contentDescription = "Project Preview",
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                                Column(modifier = Modifier.weight(1f)) {
                                    Button(
                                        onClick = { imagePickerLauncher.launch("image/*") },
                                        shape = RoundedCornerShape(10.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = NeonPurple.copy(alpha = 0.35f)),
                                        border = androidx.compose.foundation.BorderStroke(1.dp, NeonPurple),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .testTag("upload_image_button")
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(Icons.Default.AddPhotoAlternate, contentDescription = null, tint = NeonPurpleLight, modifier = Modifier.size(18.dp))
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Text(if (isCustomUploadedImage) "Change Photo" else "Upload Custom Image", fontSize = 12.sp, color = TextWhite)
                                        }
                                    }

                                    if (isCustomUploadedImage) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        TextButton(
                                            onClick = {
                                                isCustomUploadedImage = false
                                                imageSource = "proj_water_filter"
                                            },
                                            modifier = Modifier.height(28.dp)
                                        ) {
                                            Text("Reset to Preset", fontSize = 11.sp, color = NeonCoral)
                                        }
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text("Or select a Science Preset Illustration:", fontSize = 11.sp, color = TextMuted)
                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                presetImages.forEach { preset ->
                                    val isSelected = !isCustomUploadedImage && imageSource == preset
                                    Box(
                                        modifier = Modifier
                                            .size(54.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(DarkSurfaceVariant)
                                            .border(
                                                if (isSelected) 2.dp else 1.dp,
                                                if (isSelected) NeonCyan else DarkBorder,
                                                RoundedCornerShape(10.dp)
                                            )
                                            .clickable {
                                                imageSource = preset
                                                isCustomUploadedImage = false
                                            }
                                    ) {
                                        ProjectImage(
                                            imageSource = preset,
                                            contentDescription = preset,
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // SECTION 2: CLASS, SUBJECT & DIFFICULTY
                    item {
                        FormSectionCard(
                            title = "Academic Classification",
                            icon = Icons.Default.Psychology,
                            accentColor = NeonPurple
                        ) {
                            // Class Level Selector (1 to 10)
                            Text("Select Class (Grade 1 - 10):", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                for (c in 1..10) {
                                    val isSelected = classLevel == c
                                    Box(
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(if (isSelected) NeonPurple else DarkSurfaceVariant)
                                            .border(1.dp, if (isSelected) NeonPurpleLight else DarkBorder, RoundedCornerShape(10.dp))
                                            .clickable { classLevel = c }
                                            .testTag("select_class_$c"),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "$c",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 15.sp,
                                            color = if (isSelected) TextWhite else TextMuted
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Subject Selection
                            Text("Subject Category:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                subjectsList.forEach { subj ->
                                    val isSelected = subject.equals(subj, ignoreCase = true)
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(if (isSelected) NeonCyan.copy(alpha = 0.25f) else DarkSurfaceVariant)
                                            .border(1.dp, if (isSelected) NeonCyan else DarkBorder, RoundedCornerShape(10.dp))
                                            .clickable { subject = subj }
                                            .padding(horizontal = 14.dp, vertical = 8.dp)
                                            .testTag("select_subject_$subj")
                                    ) {
                                        Text(
                                            text = subj,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) NeonCyan else TextWhite
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Difficulty Selection
                            Text("Difficulty Level:", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = TextWhite)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                difficultyList.forEach { diff ->
                                    val isSelected = difficulty.equals(diff, ignoreCase = true)
                                    val color = when (diff) {
                                        "Easy" -> NeonGreen
                                        "Medium" -> NeonAmber
                                        "Hard" -> NeonCoral
                                        else -> NeonCyan
                                    }
                                    Box(
                                        modifier = Modifier
                                            .weight(1f)
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(if (isSelected) color.copy(alpha = 0.25f) else DarkSurfaceVariant)
                                            .border(1.dp, if (isSelected) color else DarkBorder, RoundedCornerShape(10.dp))
                                            .clickable { difficulty = diff }
                                            .padding(vertical = 10.dp)
                                            .testTag("select_difficulty_$diff"),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = diff,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (isSelected) color else TextWhite
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // SECTION 3: TIME & COST ESTIMATES
                    item {
                        FormSectionCard(
                            title = "Estimated Time & Cost",
                            icon = Icons.Default.Summarize,
                            accentColor = NeonAmber
                        ) {
                            // Estimated Time
                            OutlinedTextField(
                                value = estimatedTime,
                                onValueChange = { estimatedTime = it },
                                label = { Text("Estimated Time Required", color = TextMuted) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_estimated_time"),
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                timePresets.forEach { preset ->
                                    ChipPill(text = preset, isSelected = estimatedTime == preset) {
                                        estimatedTime = preset
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            // Estimated Cost
                            OutlinedTextField(
                                value = estimatedCost,
                                onValueChange = { estimatedCost = it },
                                label = { Text("Estimated Cost (Budget)", color = TextMuted) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_estimated_cost"),
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                costPresets.forEach { preset ->
                                    ChipPill(text = preset, isSelected = estimatedCost == preset) {
                                        estimatedCost = preset
                                    }
                                }
                            }
                        }
                    }

                    // SECTION 4: OBJECTIVE & HYPOTHESIS
                    item {
                        FormSectionCard(
                            title = "Objective & Hypothesis",
                            icon = Icons.Default.Lightbulb,
                            accentColor = NeonCyan
                        ) {
                            OutlinedTextField(
                                value = objective,
                                onValueChange = { objective = it },
                                label = { Text("Project Objective / Hypothesis", color = TextMuted) },
                                placeholder = { Text("State what problem this project solves or what scientific phenomenon it proves.", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_objective"),
                                minLines = 3,
                                colors = outlinedFieldColors()
                            )
                        }
                    }

                    // SECTION 5: MATERIALS REQUIRED (DYNAMIC LIST)
                    item {
                        FormSectionCard(
                            title = "Materials Required (${materials.size})",
                            icon = Icons.Default.Science,
                            accentColor = NeonGreen
                        ) {
                            Text(
                                text = "Add all components, tools, and materials needed to build this working model.",
                                fontSize = 12.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            materials.forEachIndexed { index, item ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    OutlinedTextField(
                                        value = item.name,
                                        onValueChange = { item.name = it },
                                        placeholder = { Text("Material Name", color = TextDark, fontSize = 12.sp) },
                                        modifier = Modifier
                                            .weight(1.8f)
                                            .testTag("material_name_$index"),
                                        colors = outlinedFieldColors(),
                                        singleLine = true
                                    )

                                    OutlinedTextField(
                                        value = item.quantity,
                                        onValueChange = { item.quantity = it },
                                        placeholder = { Text("Qty (e.g. 2 pcs)", color = TextDark, fontSize = 12.sp) },
                                        modifier = Modifier
                                            .weight(1.2f)
                                            .testTag("material_qty_$index"),
                                        colors = outlinedFieldColors(),
                                        singleLine = true
                                    )

                                    IconButton(
                                        onClick = {
                                            if (materials.size > 1) {
                                                materials.removeAt(index)
                                            }
                                        },
                                        modifier = Modifier.size(36.dp)
                                    ) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete Material", tint = NeonCoral, modifier = Modifier.size(20.dp))
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { materials.add(EditableMaterial("", "1 unit", true)) },
                                colors = ButtonDefaults.buttonColors(containerColor = NeonGreen.copy(alpha = 0.2f)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, NeonGreen),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("add_material_row_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = NeonGreen, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Add Material Item", color = NeonGreen, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    // SECTION 6: STEP-BY-STEP PROCEDURE (DYNAMIC LIST)
                    item {
                        FormSectionCard(
                            title = "Step-by-Step Procedure (${procedureSteps.size} Steps)",
                            icon = Icons.Default.Summarize,
                            accentColor = NeonPurpleLight
                        ) {
                            Text(
                                text = "Provide sequential steps so students can easily assemble the model.",
                                fontSize = 12.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            procedureSteps.forEachIndexed { index, step ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Box(
                                                    modifier = Modifier
                                                        .size(26.dp)
                                                        .clip(CircleShape)
                                                        .background(NeonPurple),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text("${index + 1}", color = TextWhite, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                                }
                                                Spacer(modifier = Modifier.width(8.dp))
                                                Text("Step ${index + 1}", fontWeight = FontWeight.Bold, color = TextWhite, fontSize = 13.sp)
                                            }

                                            if (procedureSteps.size > 1) {
                                                IconButton(
                                                    onClick = { procedureSteps.removeAt(index) },
                                                    modifier = Modifier.size(28.dp)
                                                ) {
                                                    Icon(Icons.Default.Delete, contentDescription = "Delete Step", tint = NeonCoral, modifier = Modifier.size(18.dp))
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(8.dp))

                                        OutlinedTextField(
                                            value = step.title,
                                            onValueChange = { step.title = it },
                                            placeholder = { Text("Step Title (e.g. Cut Cardboard Base)", color = TextDark, fontSize = 12.sp) },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = outlinedFieldColors(),
                                            singleLine = true
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        OutlinedTextField(
                                            value = step.instruction,
                                            onValueChange = { step.instruction = it },
                                            placeholder = { Text("Detailed Instructions for this step...", color = TextDark, fontSize = 12.sp) },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = outlinedFieldColors(),
                                            minLines = 2
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        OutlinedTextField(
                                            value = step.proTip,
                                            onValueChange = { step.proTip = it },
                                            placeholder = { Text("Exhibition Pro-Tip (Optional)", color = TextDark, fontSize = 11.sp) },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = outlinedFieldColors(),
                                            singleLine = true
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = {
                                    val nextNum = procedureSteps.size + 1
                                    procedureSteps.add(EditableStep("Step $nextNum", "", ""))
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = NeonPurple.copy(alpha = 0.2f)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, NeonPurple),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("add_step_row_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = NeonPurpleLight, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Add Another Step", color = NeonPurpleLight, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    // SECTION 7: WORKING PRINCIPLE & SCIENTIFIC LAW
                    item {
                        FormSectionCard(
                            title = "Working Principle & Scientific Law",
                            icon = Icons.Default.Science,
                            accentColor = NeonBlue
                        ) {
                            OutlinedTextField(
                                value = scientificLaw,
                                onValueChange = { scientificLaw = it },
                                label = { Text("Scientific Law / Concept", color = TextMuted) },
                                placeholder = { Text("e.g. Faraday's Law of Electromagnetic Induction", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_scientific_law"),
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = workingPrinciple,
                                onValueChange = { workingPrinciple = it },
                                label = { Text("Detailed Working Principle", color = TextMuted) },
                                placeholder = { Text("Explain how the science works in this model when operated.", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_working_principle"),
                                minLines = 3,
                                colors = outlinedFieldColors()
                            )
                        }
                    }

                    // SECTION 8: RESULT & CONCLUSION
                    item {
                        FormSectionCard(
                            title = "Result & Conclusion",
                            icon = Icons.Default.Check,
                            accentColor = NeonGreen
                        ) {
                            OutlinedTextField(
                                value = result,
                                onValueChange = { result = it },
                                label = { Text("Observed Result / Output", color = TextMuted) },
                                placeholder = { Text("e.g. Clear filtered water was obtained with 90% turbidity reduction.", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_result"),
                                minLines = 2,
                                colors = outlinedFieldColors()
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = conclusion,
                                onValueChange = { conclusion = it },
                                label = { Text("Scientific Conclusion", color = TextMuted) },
                                placeholder = { Text("e.g. Natural filtration layers effectively purify surface water for emergency usage.", color = TextDark) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("input_conclusion"),
                                minLines = 2,
                                colors = outlinedFieldColors()
                            )
                        }
                    }

                    // SECTION 9: VIVA QUESTIONS & ANSWERS (DYNAMIC LIST)
                    item {
                        FormSectionCard(
                            title = "Viva Questions & Answers (${vivaQuestions.size})",
                            icon = Icons.Default.Quiz,
                            accentColor = NeonPink
                        ) {
                            Text(
                                text = "Add expected viva questions from exhibition judges along with model answers.",
                                fontSize = 12.sp,
                                color = TextMuted
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            vivaQuestions.forEachIndexed { index, viva ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 6.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = CardDefaults.cardColors(containerColor = DarkSurfaceVariant),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkBorder)
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text("Q&A #${index + 1}", fontWeight = FontWeight.Bold, color = NeonPink, fontSize = 13.sp)
                                            if (vivaQuestions.size > 1) {
                                                IconButton(
                                                    onClick = { vivaQuestions.removeAt(index) },
                                                    modifier = Modifier.size(26.dp)
                                                ) {
                                                    Icon(Icons.Default.Delete, contentDescription = "Delete Q&A", tint = NeonCoral, modifier = Modifier.size(18.dp))
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(6.dp))

                                        OutlinedTextField(
                                            value = viva.question,
                                            onValueChange = { viva.question = it },
                                            placeholder = { Text("Question: e.g. Why did you use this material?", color = TextDark, fontSize = 12.sp) },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = outlinedFieldColors(),
                                            minLines = 2
                                        )

                                        Spacer(modifier = Modifier.height(6.dp))

                                        OutlinedTextField(
                                            value = viva.answer,
                                            onValueChange = { viva.answer = it },
                                            placeholder = { Text("Answer: e.g. It provides higher insulation and prevents heat loss.", color = TextDark, fontSize = 12.sp) },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = outlinedFieldColors(),
                                            minLines = 2
                                        )
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Button(
                                onClick = { vivaQuestions.add(EditableViva("", "")) },
                                colors = ButtonDefaults.buttonColors(containerColor = NeonPink.copy(alpha = 0.2f)),
                                border = androidx.compose.foundation.BorderStroke(1.dp, NeonPink),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .testTag("add_viva_row_button")
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Add, contentDescription = null, tint = NeonPink, modifier = Modifier.size(18.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Add Viva Question", color = NeonPink, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                                }
                            }
                        }
                    }

                    // Bottom Save Button
                    item {
                        Button(
                            onClick = {
                                if (title.isBlank()) {
                                    errorMessage = "Please enter a Project Title!"
                                    return@Button
                                }

                                val formattedMaterials = materials
                                    .filter { it.name.isNotBlank() }
                                    .map { MaterialItem(name = it.name.trim(), quantity = it.quantity.trim().ifBlank { "1 unit" }, isEssential = it.isEssential) }
                                    .ifEmpty { listOf(MaterialItem("Standard Project Materials", "1 set", true)) }

                                val formattedProcedure = procedureSteps
                                    .filter { it.title.isNotBlank() || it.instruction.isNotBlank() }
                                    .mapIndexed { idx, st ->
                                        ProcedureStep(
                                            stepNumber = idx + 1,
                                            title = st.title.trim().ifBlank { "Step ${idx + 1}" },
                                            instruction = st.instruction.trim().ifBlank { "Assemble components as per science instructions." },
                                            proTip = st.proTip.trim().takeIf { it.isNotBlank() }
                                        )
                                    }
                                    .ifEmpty { listOf(ProcedureStep(1, "Assembly & Execution", "Follow standard lab steps.")) }

                                val formattedViva = vivaQuestions
                                    .filter { it.question.isNotBlank() }
                                    .map { VivaQuestion(question = it.question.trim(), answer = it.answer.trim().ifBlank { "Working demonstration of scientific principles." }) }
                                    .ifEmpty {
                                        listOf(
                                            VivaQuestion("What does this project demonstrate?", workingPrinciple.ifBlank { "Science experiment principles." })
                                        )
                                    }

                                val newProject = ScienceProject(
                                    id = "custom-${System.currentTimeMillis()}",
                                    title = title.trim(),
                                    subtitle = subtitle.trim().ifBlank { "Working Science Exhibition Model" },
                                    classLevel = classLevel,
                                    subject = subject,
                                    difficulty = difficulty,
                                    estimatedTime = estimatedTime.trim().ifBlank { "30-45 mins" },
                                    estimatedCost = estimatedCost.trim().ifBlank { "₹100 - ₹200" },
                                    imageDrawableName = imageSource,
                                    objective = objective.trim().ifBlank { "To demonstrate scientific concepts through an engaging hands-on working model." },
                                    materials = formattedMaterials,
                                    procedure = formattedProcedure,
                                    workingPrinciple = workingPrinciple.trim().ifBlank { "Based on fundamental scientific principles and observation." },
                                    scientificLaw = scientificLaw.trim().ifBlank { "$subject Principles" },
                                    result = result.trim().ifBlank { "The working model functioned successfully, validating the scientific hypothesis." },
                                    conclusion = conclusion.trim().ifBlank { "The experiment successfully achieves its objective and provides practical insights." },
                                    vivaQuestions = formattedViva,
                                    tags = listOf(subject, difficulty, "Class $classLevel", "Custom Project"),
                                    isCustom = true
                                )

                                onAdd(newProject)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .testTag("bottom_save_project_button"),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(PurpleCyanGradient, RoundedCornerShape(14.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = TextWhite, modifier = Modifier.size(20.dp))
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "${ZynovaStrings.save(language)} & View Project Details 🚀",
                                        color = TextWhite,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
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
private fun FormSectionCard(
    title: String,
    icon: ImageVector,
    accentColor: Color,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = DarkSurfaceCard),
        border = androidx.compose.foundation.BorderStroke(1.dp, accentColor.copy(alpha = 0.35f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(accentColor.copy(alpha = 0.15f))
                        .border(1.dp, accentColor.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(18.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextWhite
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            content()
        }
    }
}

@Composable
private fun ChipPill(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isSelected) NeonCyan.copy(alpha = 0.25f) else DarkSurfaceVariant)
            .border(1.dp, if (isSelected) NeonCyan else DarkBorder, RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
            color = if (isSelected) NeonCyan else TextMuted
        )
    }
}

@Composable
private fun outlinedFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = TextWhite,
    unfocusedTextColor = TextWhite,
    focusedContainerColor = DarkBackground,
    unfocusedContainerColor = DarkBackground,
    focusedBorderColor = NeonCyan,
    unfocusedBorderColor = DarkBorder,
    focusedLabelColor = NeonCyan,
    unfocusedLabelColor = TextMuted
)
