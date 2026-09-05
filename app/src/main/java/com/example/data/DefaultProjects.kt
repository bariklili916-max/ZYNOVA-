package com.example.data

import com.example.model.MaterialItem
import com.example.model.ProcedureStep
import com.example.model.ScienceProject
import com.example.model.VivaQuestion

object DefaultProjects {
    val sampleProjects = listOf(
        ScienceProject(
            id = "proj-water-pollution",
            title = "Water Pollution & Filtration",
            subtitle = "Working model of multi-stage water filtration column",
            classLevel = 6,
            subject = "Environment",
            difficulty = "Easy",
            estimatedTime = "30-45 mins",
            estimatedCost = "₹150-200",
            imageDrawableName = "proj_water_filter",
            featured = true,
            popular = true,
            objective = "To construct a mechanical water filtration column to demonstrate how physical impurities, suspended sediments, and odor can be separated from polluted water using natural filter layers.",
            materials = listOf(
                MaterialItem("Plastic Bottle (1.5L or 2L)", "1 piece", true),
                MaterialItem("Fine Clean Sand", "1 cup (150g)", true),
                MaterialItem("Coarse River Gravel", "1 cup (150g)", true),
                MaterialItem("Activated Charcoal Powder", "1/2 cup (80g)", true),
                MaterialItem("Cotton Balls / Filter Cloth", "4-5 pieces", true),
                MaterialItem("Turbid / Dirty Mud Water", "500 ml", true),
                MaterialItem("Transparent Collection Glass", "2 beakers", true),
                MaterialItem("Rubber Band & Scissors", "1 set", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Prepare the Filter Column Bottle",
                    instruction = "Take an empty 2-liter plastic bottle. Use scissors to carefully cut off the bottom base about 3 inches from the end. Invert the top spout section to act as a funnel column.",
                    proTip = "Keep edges smooth or tape them with insulation tape for safety."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Insert the Bottom Cotton Plug",
                    instruction = "Place a dense layer of clean cotton balls or fine muslin cloth tightly into the inverted bottle neck. This prevents fine sand and charcoal from spilling into the collection container.",
                    proTip = "Press the cotton gently so water can still drip through."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Add the Activated Charcoal Layer",
                    instruction = "Pour 2 inches of crushed activated charcoal directly over the cotton. Charcoal provides microscopic pore surface area to adsorb organic toxins, chemicals, and eliminate unpleasant odor.",
                    proTip = "Rinse charcoal lightly first to remove loose black dust."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Add Sand & Gravel Graded Layers",
                    instruction = "Layer 2 inches of fine washed sand above the charcoal, followed by 2 inches of coarse gravel/pebbles at the top. The top coarse gravel traps large debris and twigs, while sand traps medium silt.",
                    proTip = "Do not shake the bottle after layering to keep distinct strata."
                ),
                ProcedureStep(
                    stepNumber = 5,
                    title = "Pour Polluted Water & Observe",
                    instruction = "Place the filter over a clear collection beaker. Slowly pour muddy water into the top. Watch the water percolate through gravel, sand, charcoal, and cotton, dripping out crystal clear.",
                    proTip = "Pour slowly along the side of the bottle to avoid disturbing the sand surface."
                )
            ),
            workingPrinciple = "Multi-Stage Mechanical Sedimentation & Adsorption: Water purification operates through successive porosity gradient filtration. Large debris is halted by coarse gravel via sieve effect. Fine silt is captured in the tortuous pathways of sand. Activated charcoal performs chemical adsorption of dissolved organic impurities and odors via Van der Waals forces. Cotton serves as a final particulate arrestor.",
            scientificLaw = "Principle of Porous Media Filtration & Van der Waals Adsorption",
            result = "The dark, turbid muddy water poured into the filter emerges as noticeably clear, odorless liquid in the lower collection beaker with more than 95% of visible suspended solids removed.",
            conclusion = "Mechanical filtration using graded natural layers effectively removes physical particulates and dissolved odors. However, for drinking purposes, biological disinfection (such as boiling or UV sterilization) is additionally required to kill microscopic bacteria.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What is water pollution?",
                    answer = "Water pollution is the contamination of water bodies (rivers, lakes, groundwater) by harmful chemical, biological, or physical substances, making it unsafe for consumption and harming aquatic ecosystems."
                ),
                VivaQuestion(
                    question = "What is the specific role of activated charcoal in this filter?",
                    answer = "Activated charcoal has millions of microscopic pores that chemically adsorb dissolved impurities, organic compounds, chlorine, and bad odors from contaminated water."
                ),
                VivaQuestion(
                    question = "Why are materials arranged in this specific layer order?",
                    answer = "Arranging from largest pore size (gravel on top) to smallest (cotton at bottom) prevents rapid clogging and ensures each layer filters out progressively smaller particles."
                ),
                VivaQuestion(
                    question = "Is the filtered water immediately safe for drinking?",
                    answer = "No. While it is physically clean and odorless, microscopic pathogens (bacteria, viruses, and parasites) may still exist. It must be boiled or treated with chlorine/UV to be potable."
                )
            ),
            tags = listOf("Pollution", "Filter", "Chemistry", "Ecology", "Clean Water")
        ),
        ScienceProject(
            id = "proj-periscope",
            title = "Periscope (Law of Reflection)",
            subtitle = "See over obstacles using optical plane mirror reflection",
            classLevel = 6,
            subject = "Physics",
            difficulty = "Easy",
            estimatedTime = "25-35 mins",
            estimatedCost = "₹80-120",
            imageDrawableName = "proj_periscope",
            featured = true,
            popular = true,
            objective = "To construct a working optical periscope to view objects situated above the direct line of sight or behind obstacles using the optical principle of reflection by plane mirrors.",
            materials = listOf(
                MaterialItem("Cardboard Tube / Long Box (12-15 inches)", "1 piece", true),
                MaterialItem("Square Plane Mirrors (5cm x 5cm)", "2 pieces", true),
                MaterialItem("Protractor & Ruler", "1 piece", true),
                MaterialItem("Craft Cutter / Scissors", "1 piece", true),
                MaterialItem("Adhesive Tape / Hot Glue", "1 roll", true),
                MaterialItem("Black Chart Paper (interior lining)", "1 sheet", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Construct Box & View Windows",
                    instruction = "Take a long rectangular cardboard box. Cut two viewing windows (approx 5x5 cm) on opposite faces: one near the top front face and one near the bottom rear face.",
                    proTip = "Line the inner walls with matte black paper to reduce internal light scattering."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Mark 45-Degree Angles",
                    instruction = "Using a protractor, accurately mark a 45-degree angle diagonal slot directly opposite each viewing window on the side walls of the cardboard box.",
                    proTip = "Precision in the 45-degree angle is critical for undistorted image alignment."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Mount the Mirrors Facing Inward",
                    instruction = "Insert one mirror into the top 45° slot facing downwards towards the bottom of the box. Insert the second mirror into the bottom 45° slot facing upwards towards the top mirror.",
                    proTip = "Ensure both mirrors are parallel to each other."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Secure & Test the Periscope",
                    instruction = "Fasten both mirrors firmly with tape. Look through the bottom aperture while pointing the top aperture over a wall, desk, or door obstacle. Observe the upright image.",
                    proTip = "Calibrate mirror angles slightly if the view is tilted."
                )
            ),
            workingPrinciple = "Law of Reflection: Light rays coming from an elevated object enter the top aperture and strike the first plane mirror at an angle of incidence of 45°. By the Law of Reflection (angle of incidence = angle of reflection), the rays are deflected 90° downwards through the tube. The second parallel mirror at the bottom again reflects the rays by 90° into the viewer's eye, resulting in a parallel translation of the original light path.",
            scientificLaw = "Laws of Reflection of Light (i = r on Plane Mirrors)",
            result = "The observer at the bottom aperture can clearly see high or hidden objects above walls without exposing themselves, observing an erect, virtual image.",
            conclusion = "Parallel plane mirrors inclined at 45° accurately deflect optical rays by 180° total shift, allowing non-line-of-sight visual surveillance as used in submarines and armored vehicles.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "State the laws of reflection of light.",
                    answer = "1. The angle of incidence is equal to the angle of reflection (i = r). 2. The incident ray, reflected ray, and normal to the surface all lie in the same plane."
                ),
                VivaQuestion(
                    question = "Why are the mirrors inclined specifically at 45 degrees?",
                    answer = "An angle of incidence of 45° produces an angle of reflection of 45°, totaling a 90° turn for the light ray, steering it directly along the axis of the tube."
                ),
                VivaQuestion(
                    question = "What is the nature of the image seen in a periscope?",
                    answer = "The image is virtual, erect, identical in size to the object, with lateral inversion neutralized by the double reflection."
                ),
                VivaQuestion(
                    question = "Mention practical real-world applications of periscopes.",
                    answer = "Used in naval submarines to observe the sea surface while submerged, in military tanks, and in trench warfare."
                )
            ),
            tags = listOf("Optics", "Physics", "Light", "Reflection", "Submarine")
        ),
        ScienceProject(
            id = "proj-solar-cooker",
            title = "Solar Cooker Model",
            subtitle = "Cook food using renewable solar thermal energy",
            classLevel = 8,
            subject = "Physics",
            difficulty = "Medium",
            estimatedTime = "45-60 mins",
            estimatedCost = "₹150-200",
            imageDrawableName = "proj_solar_cooker",
            featured = true,
            popular = true,
            objective = "To construct a box-type solar cooker demonstrating how solar radiation can be collected, concentrated, and trapped to generate heat for cooking food and heating water without fuel.",
            materials = listOf(
                MaterialItem("Cardboard Boxes (One nesting inside another)", "2 boxes", true),
                MaterialItem("Aluminum Foil (High Reflectivity)", "1 roll", true),
                MaterialItem("Black Matte Construction Paper", "2 sheets", true),
                MaterialItem("Transparent Glass / Acrylic Sheet", "1 sheet", true),
                MaterialItem("Insulation (Crumpled newspaper / Wool)", "1 bundle", true),
                MaterialItem("Small Black Metal Cooking Pot / Bowl", "1 unit", true),
                MaterialItem("Thermometer (0°C to 120°C)", "1 unit", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Construct Double Box Insulation",
                    instruction = "Place the smaller box inside the larger box. Pack the 2-inch gap between their walls with crumpled newspaper or thermocol insulation to prevent thermal conduction loss.",
                    proTip = "Keep insulation dry for optimal thermal resistance."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Line the Cooking Chamber with Black Paper",
                    instruction = "Line the interior base and side walls of the inner box with black matte paper. Black surfaces act as near-ideal blackbody absorbers, absorbing maximum radiant solar heat.",
                    proTip = "Avoid glossy paper as it reflects rather than absorbs radiation."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Build the Reflective Booster Lid",
                    instruction = "Attach an adjustable cardboard flap on top of the box. Cover this flap with smooth aluminum foil to serve as a specular reflector directing extra sunlight into the chamber.",
                    proTip = "Smooth foil free of wrinkles provides maximum concentrated reflection."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Install Clear Glass Greenhouse Seal",
                    instruction = "Fit a transparent glass pane or clear acrylic sheet tightly over the inner box opening. Seal the edges with weatherstrip tape to prevent warm air convection leakage.",
                    proTip = "Glass is transparent to shortwave solar rays but opaque to longwave infrared heat."
                ),
                ProcedureStep(
                    stepNumber = 5,
                    title = "Test Under Sunlight",
                    instruction = "Place a black pot with water/rice inside. Position the solar cooker facing the sun and adjust the foil reflector. Monitor the temperature rise with a thermometer.",
                    proTip = "Re-align cooker every 30 minutes to track the sun's trajectory."
                )
            ),
            workingPrinciple = "Solar Thermal Conversion & Greenhouse Effect: Shortwave solar radiation (visible and UV rays) penetrates through the clear glass lid and strikes the black interior, converting into thermal energy. The heated interior emits longwave infrared radiation. The glass cover is opaque to infrared radiation, trapping the heat inside (Greenhouse Effect). Foil reflectors concentrate additional photons, and insulated walls prevent heat loss.",
            scientificLaw = "Stefan-Boltzmann Law of Radiation & Greenhouse Heat Retention",
            result = "Internal box temperature reaches between 75°C to 95°C on a clear sunny day within 45 minutes, sufficient to boil water, bake potatoes, or melt solids.",
            conclusion = "Solar cookers harness inexhaustible, clean solar energy efficiently, cutting carbon footprint and conserving fossil fuels in eco-friendly cooking.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "Why is the inner cooking box painted or lined with black color?",
                    answer = "Black surfaces are excellent absorbers of radiant energy (high emissivity/absorptivity), converting maximum sunlight into heat."
                ),
                VivaQuestion(
                    question = "What is the function of the glass cover?",
                    answer = "The glass cover produces the greenhouse effect: it allows shortwave incoming sunlight in but prevents outgoing longwave infrared heat from escaping."
                ),
                VivaQuestion(
                    question = "What is the role of the aluminum foil flap?",
                    answer = "It acts as a planar mirror reflector, bouncing additional sunlight into the chamber to increase thermal concentration."
                ),
                VivaQuestion(
                    question = "What are advantages and limitations of a solar cooker?",
                    answer = "Advantages: Zero fuel cost, zero emissions, retains food nutrients. Limitations: Depends on sunny weather, slower cooking time, cannot be used at night."
                )
            ),
            tags = listOf("Energy", "Solar", "Thermodynamics", "Renewable", "Greenhouse")
        ),
        ScienceProject(
            id = "proj-digestive-system",
            title = "Human Digestive System",
            subtitle = "3D working model of the alimentary canal and peristalsis",
            classLevel = 7,
            subject = "Biology",
            difficulty = "Easy",
            estimatedTime = "35-50 mins",
            estimatedCost = "₹120-180",
            imageDrawableName = "proj_digestive_model",
            featured = false,
            popular = true,
            objective = "To construct a 3D functional model illustrating the human digestive tract, the organs of the alimentary canal (mouth, esophagus, stomach, liver, pancreas, intestines), and mechanical peristalsis.",
            materials = listOf(
                MaterialItem("Hardboard Base / Foam Board (18x24 in)", "1 board", true),
                MaterialItem("Transparent Flexible Plastic Tubing (1 inch dia)", "3 feet", true),
                MaterialItem("Plastic Funnel (Mouth / Oral cavity)", "1 piece", true),
                MaterialItem("Latex Balloon / Squeezable Bulb (Stomach)", "2 pieces", true),
                MaterialItem("Corrugated Tube / Rope (Small & Large Intestine)", "4 feet", true),
                MaterialItem("Colored Clay / Foam Sheets (Liver, Pancreas)", "1 set", true),
                MaterialItem("Ping Pong Balls / Beads (Simulated Food Bolus)", "3-4 pieces", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Mount Board & Anatomical Outline",
                    instruction = "Draw a proportional human torso silhouette on the foam board. Label primary anatomical coordinates: Head/Mouth, Chest, Abdomen, and Pelvic area.",
                    proTip = "Use contrasting colored markers for distinct organ boundaries."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Assemble Mouth & Esophagus Pathway",
                    instruction = "Fix the funnel at the mouth position. Connect the transparent tube to the bottom of the funnel running vertically down through the chest as the esophagus.",
                    proTip = "Ensure the tube is clear so the food bolus ball can be observed moving."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Attach the Stomach Chamber",
                    instruction = "Connect the esophagus to an expandable latex balloon or clear squeezable bottle representing the stomach chamber where chemical and mechanical churning occurs.",
                    proTip = "Add a bit of yellow-tinted liquid to represent gastric juices and hydrochloric acid."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Model Liver, Pancreas & Intestinal Loops",
                    instruction = "Sculpt the liver (red-brown) and pancreas (yellow) with clay. Connect coiled narrow tubing for the 6-meter small intestine, leading into wider corrugated tube for the large intestine.",
                    proTip = "Coil the small intestine densely to show its massive surface area."
                ),
                ProcedureStep(
                    stepNumber = 5,
                    title = "Demonstrate Food Ingestion & Peristalsis",
                    instruction = "Drop a bead (food bolus) into the funnel mouth. Squeeze the flexible tube sequentially downwards with fingers to demonstrate wave-like muscular peristalsis.",
                    proTip = "Explain each organ's role as the bead moves through the pathway."
                )
            ),
            workingPrinciple = "Human Physiology & Peristaltic Propulsion: Digestion is the mechanical and chemical breakdown of complex food into absorbable nutrients. The model demonstrates peristalsis—rhythmic contractions of circular and longitudinal smooth muscles in the esophagus and intestines propelling the bolus forward regardless of gravity.",
            scientificLaw = "Physiological Principles of Gastrointestinal Motility & Enzymatic Breakdown",
            result = "The simulated food bolus travels seamlessly from the oral cavity through the esophageal tube, undergoes storage in the stomach model, and navigates the coiled intestinal labyrinth.",
            conclusion = "Hands-on modeling vividly demonstrates how specialized digestive organs work synergistically in nutrient extraction, water reabsorption, and waste excretion.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What is peristalsis?",
                    answer = "Peristalsis is the involuntary, wave-like contraction and relaxation of smooth muscles lining the alimentary canal that pushes food along the digestive tract."
                ),
                VivaQuestion(
                    question = "What is the function of hydrochloric acid (HCl) in the stomach?",
                    answer = "HCl creates an acidic pH (1.5-3.5) necessary for the enzyme pepsin to digest proteins, and kills ingested harmful bacteria."
                ),
                VivaQuestion(
                    question = "Where does the maximum absorption of digested nutrients take place?",
                    answer = "In the small intestine (jejunum and ileum), which has millions of finger-like projections called villi that multiply the absorption surface area."
                ),
                VivaQuestion(
                    question = "What is the role of bile produced by the liver?",
                    answer = "Bile emulsifies large fat globules into tiny droplets (increasing surface area for lipase enzymes) and neutralizes acidic chyme from the stomach."
                )
            ),
            tags = listOf("Biology", "Human Body", "Anatomy", "Digestion", "Health")
        ),
        ScienceProject(
            id = "proj-electric-circuit",
            title = "Electric Circuit & Switch",
            subtitle = "Simple DC electric circuit with series & parallel switching",
            classLevel = 5,
            subject = "Electronics",
            difficulty = "Easy",
            estimatedTime = "20-30 mins",
            estimatedCost = "₹100-150",
            imageDrawableName = "proj_electric_circuit",
            featured = true,
            popular = true,
            objective = "To assemble a complete, closed electrical circuit demonstrating electron flow, electric current, conductors vs insulators, and load switching using a battery and bulb.",
            materials = listOf(
                MaterialItem("9V Battery with Snap Connector (or 2x AA Holder)", "1 unit", true),
                MaterialItem("Miniature LED Bulb / 3V Flashlight Bulb", "2 pieces", true),
                MaterialItem("SPST Toggle Switch / Paperclip DIY Switch", "1 piece", true),
                MaterialItem("Insulated Copper Connecting Wires", "3 pieces (15cm each)", true),
                MaterialItem("Wooden / Plastic Baseboard (10x15 cm)", "1 board", true),
                MaterialItem("Conductive vs Insulating Test Samples (Coin, Plastic, Wood)", "1 kit", false),
                MaterialItem("Electrical Tape / Wire Stripper", "1 set", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Strip & Prepare Copper Wires",
                    instruction = "Strip about 1 cm of plastic insulation from both ends of each copper wire using a wire stripper to expose shiny conductive copper cores.",
                    proTip = "Twist stranded copper wires tightly to prevent stray short circuits."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Connect the Power Source & Switch",
                    instruction = "Connect the red (positive) wire from the 9V battery clip to one terminal of the toggle switch. Secure the joint with a twist and electrical tape.",
                    proTip = "Always wire the switch along the positive line for standard safety."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Connect Switch to Bulb Socket",
                    instruction = "Run a second jumper wire from the remaining terminal of the switch to the positive anode terminal of the LED bulb holder.",
                    proTip = "LEDs are polarized: longer leg is positive (anode), shorter is negative (cathode)."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Complete Closed Loop with Ground",
                    instruction = "Connect a third wire from the negative terminal of the bulb socket directly back to the black (negative) lead of the battery to close the loop.",
                    proTip = "Mount components on the board with double-sided tape for a neat presentation."
                ),
                ProcedureStep(
                    stepNumber = 5,
                    title = "Test Circuit Continuity & Conductivity",
                    instruction = "Toggle the switch to ON: current flows and the bulb illuminates instantly. Insert test materials (coin, rubber, graphite pencil) into an open gap to verify electrical conductivity.",
                    proTip = "Graphite in pencils conducts electricity because of delocalized electrons!"
                )
            ),
            workingPrinciple = "Ohm's Law & Closed Circuit Current: Electric current (I) is the directed flow of free electrons through a closed conductive path driven by potential difference (V) supplied by the battery. When the switch is closed (ON), circuit resistance (R) permits current I = V / R, energizing the tungsten filament / LED semiconductor junction to emit photons. Opening the switch creates an air gap (infinite resistance), arresting current.",
            scientificLaw = "Ohm's Law (V = I × R) & Electrical Circuit Continuity",
            result = "Closing the switch immediately illuminates the bulb brightly. When open, current drops to zero. Metal coins and graphite conduct current; plastic and wood block it.",
            conclusion = "A continuous unbroken conductive circuit is mandatory for electric current flow, and switches provide controlled interruption of energy transfer.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What constitutes an electric circuit?",
                    answer = "A continuous closed path consisting of an energy source (battery), conductive wires, an electrical load (bulb/motor), and a control device (switch)."
                ),
                VivaQuestion(
                    question = "What is the difference between an open circuit and a closed circuit?",
                    answer = "A closed circuit is an unbroken complete loop where current flows. An open circuit has a break or disconnection preventing electron flow."
                ),
                VivaQuestion(
                    question = "What is the difference between a conductor and an insulator?",
                    answer = "Conductors (like copper, aluminum, iron) have free electrons and allow current to pass easily. Insulators (rubber, plastic, glass) have tightly bound electrons and block current."
                ),
                VivaQuestion(
                    question = "Why does an LED require a current-limiting resistor?",
                    answer = "LEDs have low internal resistance; without a resistor or voltage match, excessive current flows causing the semiconductor to overheat and burn out."
                )
            ),
            tags = listOf("Electricity", "Circuits", "Physics", "STEM", "Electronics")
        ),
        ScienceProject(
            id = "proj-hydraulic-lift",
            title = "Hydraulic Lift (Pascal's Law)",
            subtitle = "Lifting heavy loads using fluid pressure transmission",
            classLevel = 9,
            subject = "Physics",
            difficulty = "Medium",
            estimatedTime = "40-55 mins",
            estimatedCost = "₹160-220",
            imageDrawableName = "proj_electric_circuit",
            featured = true,
            popular = false,
            objective = "To demonstrate Pascal's Principle of fluid pressure transmission by building a working hydraulic scissor lift with medical syringes and water.",
            materials = listOf(
                MaterialItem("Plastic Syringes (10ml & 20ml or 50ml)", "2 pieces", true),
                MaterialItem("Flexible Aquarium Silicone Tubing (50cm)", "1 tube", true),
                MaterialItem("Ice Cream Craft Sticks (Popsicle sticks)", "16 sticks", true),
                MaterialItem("Cardboard Base & Platform", "2 pieces", true),
                MaterialItem("Water with food coloring", "100 ml", true),
                MaterialItem("Split pins / Brass fasteners", "12 pins", true)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Construct Scissor Mechanism",
                    instruction = "Join pairs of craft sticks at their centers and ends using brass split pins to form an expandable X-shaped scissor linkage platform.",
                    proTip = "Ensure pivot pins are loose enough to flex smoothly."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Prepare the Hydraulic Fluid Line",
                    instruction = "Fill the 10ml syringe and silicone tube completely with colored water, eliminating all air bubbles. Connect the tube to the second (20ml or 10ml) syringe.",
                    proTip = "Air is compressible while water is incompressible. Bleed all air out!"
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Mount Syringe to Scissor Platform",
                    instruction = "Fix the slave syringe cylinder to the baseboard and glue its plunger to the bottom moving crossbar of the scissor lift mechanism.",
                    proTip = "Hot glue provides strong rigid anchoring."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Apply Master Force & Lift",
                    instruction = "Push the plunger of the master control syringe. Fluid transmits pressure instantaneously across the tube, extending the slave plunger and elevating the lift platform with load.",
                    proTip = "Place small weights on top to demonstrate mechanical advantage."
                )
            ),
            workingPrinciple = "Pascal's Law: Pressure applied to an enclosed, incompressible fluid is transmitted equally and undiminished in all directions to every portion of the fluid and walls of the container (P = F1/A1 = F2/A2). By using a smaller input syringe area and a larger output syringe area, a small input force creates a multiplied lifting force.",
            scientificLaw = "Pascal's Principle of Hydrostatics (P1 = P2)",
            result = "Applying gentle fingertip pressure to the control syringe raises heavy weights placed on the scissor platform smoothly without electrical power.",
            conclusion = "Incompressible liquids allow powerful force multiplication and remote actuation used in automotive car lifts, airplane landing gears, and excavators.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "State Pascal's Law.",
                    answer = "Pascal's Law states that when external pressure is applied to any point of an enclosed, incompressible fluid at rest, the pressure is transmitted undiminished in all directions."
                ),
                VivaQuestion(
                    question = "Why is water/oil used instead of air in hydraulic systems?",
                    answer = "Liquids are virtually incompressible, transmitting force instantly. Air is compressible and absorbs energy before moving the load."
                ),
                VivaQuestion(
                    question = "How does hydraulic system achieve mechanical advantage?",
                    answer = "Since pressure is constant (F1/A1 = F2/A2), if the output piston area A2 is larger than input area A1, the output force F2 is multiplied proportionally."
                )
            ),
            tags = listOf("Hydraulics", "Mechanics", "Pascal Law", "Physics", "Machines")
        ),
        ScienceProject(
            id = "proj-smart-irrigation",
            title = "Smart Automatic Plant Watering",
            subtitle = "Soil moisture sensor automated soil hydration system",
            classLevel = 10,
            subject = "Electronics",
            difficulty = "Hard",
            estimatedTime = "50-70 mins",
            estimatedCost = "₹250-350",
            imageDrawableName = "proj_water_filter",
            featured = true,
            popular = false,
            objective = "To construct an automated soil moisture detection circuit that triggers a mini DC water pump when soil becomes dry and turns off when moisture reaches optimum level.",
            materials = listOf(
                MaterialItem("Soil Moisture Sensor Module (or DIY Nails)", "1 unit", true),
                MaterialItem("5V Relay Module / NPN Transistor (BC547)", "1 unit", true),
                MaterialItem("Mini Submersible 5V DC Water Pump", "1 piece", true),
                MaterialItem("Clear Drip Tubing (5mm dia)", "1 meter", true),
                MaterialItem("9V Battery / 5V USB Power Source", "1 unit", true),
                MaterialItem("Pot with Soil and Plant", "1 pot", true),
                MaterialItem("Water Reservoir Cup", "1 cup", true)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Set Up Moisture Probe in Soil",
                    instruction = "Insert two conductive metal probes (or sensor prongs) into the potted plant soil spaced 2 inches apart.",
                    proTip = "Keep prongs clean of corrosion for stable resistance reading."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Wire Sensor to Switching Transistor / Relay",
                    instruction = "Connect the sensor's analog output to the base of the BC547 transistor or relay control pin. When soil is dry (high resistance), trigger voltage energizes the switch.",
                    proTip = "Use a 10k potentiometer to calibrate soil moisture sensitivity threshold."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Connect DC Water Pump & Reservoir",
                    instruction = "Submerge the 5V DC mini pump in the water reservoir cup. Run the drip tube from the pump output directly to the base of the plant.",
                    proTip = "Ensure tubing has no kinks to allow smooth water flow."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Test Automation Cycle",
                    instruction = "Power on the circuit. When soil is dry, the pump immediately activates and drips water. Once soil moisture increases, resistance drops, triggering the circuit to stop pumping automatically.",
                    proTip = "Demonstrates true closed-loop feedback automation!"
                )
            ),
            workingPrinciple = "Electrical Conductivity of Soil & Closed-Loop Feedback: Dry soil contains air gaps and has high electrical resistance. Wet soil with dissolved mineral ions conducts electricity easily (low resistance). The circuit senses this resistance change via voltage divider and switches the pump relay accordingly.",
            scientificLaw = "Electrolytic Conductivity & Electronic Switching Feedback Loop",
            result = "Plant is watered automatically and precisely without human intervention, preventing both underwatering and overwatering.",
            conclusion = "Automated sensor-based irrigation conserves up to 60% agricultural water while optimizing crop health.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "Why does wet soil conduct electricity better than dry soil?",
                    answer = "Water dissolves ionic mineral salts in the soil (like sodium, potassium, nitrates), creating free ions that act as charge carriers for electric current."
                ),
                VivaQuestion(
                    question = "What is closed-loop automation?",
                    answer = "A control system where output (soil moisture) is constantly measured by sensors to inform and regulate input actions (pump on/off) automatically."
                ),
                VivaQuestion(
                    question = "What is the function of a relay in an electronic circuit?",
                    answer = "A relay is an electrically operated switch that allows a low-power control signal to safely switch on/off a higher voltage or higher current device."
                )
            ),
            tags = listOf("Automation", "IoT", "Electronics", "Botany", "Water Conservation")
        ),
        ScienceProject(
            id = "proj-density-rainbow",
            title = "Density Rainbow Column",
            subtitle = "Liquid density layers demonstrating buoyancy & mass volume ratio",
            classLevel = 3,
            subject = "Chemistry",
            difficulty = "Easy",
            estimatedTime = "20-30 mins",
            estimatedCost = "₹50-80",
            imageDrawableName = "proj_water_filter",
            featured = false,
            popular = false,
            objective = "To construct a vibrant layered liquid column demonstrating differences in fluid densities and the principle of liquid immiscibility.",
            materials = listOf(
                MaterialItem("Tall Transparent Glass Cylinder / Jar", "1 piece", true),
                MaterialItem("Honey / Corn Syrup (Dense)", "50 ml", true),
                MaterialItem("Dishwashing Soap (Blue/Green)", "50 ml", true),
                MaterialItem("Water with Food Color (Red/Purple)", "50 ml", true),
                MaterialItem("Vegetable / Baby Oil (Yellow/Clear)", "50 ml", true),
                MaterialItem("Rubbing Alcohol (Colored)", "50 ml", true),
                MaterialItem("Test Objects (Bolt, Grape, Plastic bead, Foam)", "1 kit", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Pour the Densest Liquid First",
                    instruction = "Pour 50ml of honey into the dead center bottom of the tall glass. Ensure it does not touch the side walls.",
                    proTip = "Honey has the highest density (~1.42 g/cm³)."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Layer Dish Soap & Colored Water",
                    instruction = "Slowly pour dish soap down the interior side wall of the glass. Next, gently trickle colored water over the soap layer using the back of a spoon.",
                    proTip = "Pouring against a spoon breaks the fall velocity and preserves layers."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Add Vegetable Oil & Alcohol",
                    instruction = "Carefully add vegetable oil, followed by colored rubbing alcohol at the very top. Observe the 5 distinct floating color bands.",
                    proTip = "Do not stir or shake the container."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Drop Small Objects to Test Buoyancy",
                    instruction = "Gently drop a metal screw (sinks to honey), a grape (floats on dish soap), a plastic bead (floats on water), and a foam piece (floats on alcohol).",
                    proTip = "Each object settles at the liquid layer matching its own density."
                )
            ),
            workingPrinciple = "Mass Density & Archimedes' Buoyancy: Density is defined as mass per unit volume ($\rho = m/V$). Liquids with higher density sink below lighter liquids with lower density. Unless agitated or chemically miscible, the distinct densities form stable stratified layers.",
            scientificLaw = "Density Equation ($\rho = m/V$) & Archimedes Principle of Buoyancy",
            result = "Five vibrant, razor-sharp liquid layers form without mixing: Honey at bottom, Soap, Water, Oil, and Alcohol on top.",
            conclusion = "Objects and liquids float or sink based on their relative densities compared to the surrounding medium.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What is density?",
                    answer = "Density is the measure of how tightly packed mass is in a given volume of a substance ($\text{Density} = \text{Mass} / \text{Volume}$)."
                ),
                VivaQuestion(
                    question = "Why doesn't oil mix with water?",
                    answer = "Oil is non-polar and less dense than polar water molecules, which attract each other more strongly, repelling the oil to float on top."
                ),
                VivaQuestion(
                    question = "Why does a heavy steel bolt sink all the way to the bottom?",
                    answer = "The density of steel (~7.8 g/cm³) is far greater than all the liquids in the column, so gravity pulls it to the base."
                )
            ),
            tags = listOf("Density", "Chemistry", "Liquids", "Buoyancy", "Rainbow")
        ),
        ScienceProject(
            id = "proj-lemon-battery",
            title = "Lemon Battery Experiment",
            subtitle = "Generate electricity from citrus fruit chemical reactions",
            classLevel = 4,
            subject = "Chemistry",
            difficulty = "Easy",
            estimatedTime = "20-30 mins",
            estimatedCost = "₹60-100",
            imageDrawableName = "proj_electric_circuit",
            featured = false,
            popular = false,
            objective = "To create a voltaic electrochemical cell using fresh lemons to illuminate an LED or power a digital LCD clock.",
            materials = listOf(
                MaterialItem("Fresh Juicy Lemons", "3-4 pieces", true),
                MaterialItem("Copper Coins / Strips (Positive Anode)", "4 pieces", true),
                MaterialItem("Zinc Galvanized Nails (Negative Cathode)", "4 pieces", true),
                MaterialItem("Alligator Clip Jumper Wires", "5 wires", true),
                MaterialItem("Low-Voltage Red LED Bulb", "1 piece", true)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Prepare and Roll the Lemons",
                    instruction = "Roll each lemon firmly on a table with your palm. This breaks internal citrus pulp sacs, releasing acidic juice rich in citric acid electrolyte.",
                    proTip = "Do not puncture the lemon skin while rolling."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Insert Electrodes",
                    instruction = "Insert one copper coin and one zinc nail into each lemon, spaced about 1 inch apart. Ensure the metals do not touch each other inside.",
                    proTip = "Copper serves as the positive terminal and zinc as the negative terminal."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Connect Lemons in Series",
                    instruction = "Use alligator clips to connect the zinc nail of the first lemon to the copper coin of the second lemon. Repeat across all 4 lemons.",
                    proTip = "Series connection adds the voltage of each individual lemon cell (~0.9V each = 3.6V total)."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Connect to LED and Observe",
                    instruction = "Connect the free copper lead to the long positive leg of the LED and the free zinc lead to the short negative leg. Watch the LED glow brightly in a dim room.",
                    proTip = "If it doesn't light up, reverse the LED polarity."
                )
            ),
            workingPrinciple = "Electrochemical Voltaic Cell: Citric acid in lemon juice acts as an electrolyte. Zinc has higher oxidation potential and undergoes oxidation (Zn -> Zn2+ + 2e-), releasing electrons. Electrons travel through external wires to the copper electrode where hydrogen ions in the acid are reduced (2H+ + 2e- -> H2). This electron flow powers the LED.",
            scientificLaw = "Electrochemical Oxidation-Reduction (Redox) Reactions",
            result = "Four lemons connected in series produce approx 3.2V to 3.8V of DC electrical potential, successfully illuminating the red LED.",
            conclusion = "Chemical energy stored in atomic bonds of metals and acidic electrolyte can be converted directly into usable electrical energy.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What role does the lemon juice play in this battery?",
                    answer = "The lemon juice contains citric acid (C6H8O7), which acts as an electrolyte containing free hydrogen ions to facilitate ion transfer."
                ),
                VivaQuestion(
                    question = "Why do we use two different metals (Copper and Zinc)?",
                    answer = "Different metals have different electronegativities and oxidation potentials. Zinc oxidizes more readily than copper, creating the electrical potential difference."
                ),
                VivaQuestion(
                    question = "Why do we connect 4 lemons in series?",
                    answer = "One lemon cell produces only ~0.9V, which is below the threshold voltage of an LED (approx 2V). Series connection sums the voltages together."
                )
            ),
            tags = listOf("Battery", "Electrochemistry", "Renewable", "Lemon", "Physics")
        ),
        ScienceProject(
            id = "proj-shadow-clock",
            title = "Shadow Sundial Clock",
            subtitle = "Track time using the Sun's position and shadow movement",
            classLevel = 1,
            subject = "Astronomy",
            difficulty = "Easy",
            estimatedTime = "15-25 mins",
            estimatedCost = "₹30-50",
            imageDrawableName = "proj_periscope",
            featured = false,
            popular = false,
            objective = "To construct a simple sundial clock to understand how Earth's rotation causes the Sun to appear to move across the sky, casting changing shadows that tell time.",
            materials = listOf(
                MaterialItem("Paper Plate / Flat Cardboard Disc", "1 plate", true),
                MaterialItem("Pencil / Wooden Stick (Gnomon)", "1 piece", true),
                MaterialItem("Modeling Clay / Playdough", "1 ball", true),
                MaterialItem("Compass (or Smartphone Compass)", "1 unit", false),
                MaterialItem("Ruler & Colored Markers", "1 set", true)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Mount the Center Gnomon Stick",
                    instruction = "Use modeling clay to secure the pencil straight upright in the exact center of the paper plate.",
                    proTip = "A vertical pencil ensures symmetrical shadow length."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Place in Outdoor Sunlight",
                    instruction = "Set the plate on a flat outdoor surface exposed to sunlight throughout the day. Point the 12 o'clock mark towards True North.",
                    proTip = "Tape down the plate so wind does not shift its orientation."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Mark Hourly Shadows",
                    instruction = "At the start of each hour (9:00 AM, 10:00 AM, etc.), trace the shadow cast by the pencil and write the current time beside it.",
                    proTip = "Notice how shadows are long in morning, shortest at solar noon, and long again in evening."
                )
            ),
            workingPrinciple = "Earth's Diurnal Rotation & Celestial Mechanics: As the Earth rotates on its axis once every 24 hours from west to east, the Sun appears to move across the sky from east to west. An upright object (gnomon) blocks sunlight, casting a shadow in the opposite direction of the Sun.",
            scientificLaw = "Solar Geometry & Diurnal Planetary Rotation",
            result = "The shadow moves clockwise around the plate in a regular, predictable pattern, marking hourly time intervals accurately on sunny days.",
            conclusion = "Ancient civilizations used the natural movement of shadows to develop sundials as humanity's first timekeeping instruments.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "Why does the shadow change position throughout the day?",
                    answer = "Because the Earth is constantly rotating on its axis, changing the apparent position and angle of the Sun in the sky."
                ),
                VivaQuestion(
                    question = "At what time of day is the shadow shortest?",
                    answer = "At solar noon (around 12:00 PM), when the Sun is at its highest elevation directly overhead in the sky."
                ),
                VivaQuestion(
                    question = "What is the pointer in a sundial called?",
                    answer = "The shadow-casting upright stick or blade is called a 'Gnomon'."
                )
            ),
            tags = listOf("Astronomy", "Sun", "Shadow", "Time", "Class 1")
        ),
        ScienceProject(
            id = "proj-plant-transpiration",
            title = "Plant Transpiration & Capillary Action",
            subtitle = "See how plants absorb water and release vapor through leaves",
            classLevel = 2,
            subject = "Biology",
            difficulty = "Easy",
            estimatedTime = "15-20 mins (Wait 4 hrs)",
            estimatedCost = "₹40-60",
            imageDrawableName = "proj_digestive_model",
            featured = false,
            popular = false,
            objective = "To observe water uptake in plant vascular xylem tubes via capillary action and demonstrate transpiration through leaf stomata.",
            materials = listOf(
                MaterialItem("Fresh White Carnation / Celery Stalks", "2 stems", true),
                MaterialItem("Clear Glass Tumblers", "2 glasses", true),
                MaterialItem("Blue and Red Food Coloring", "2 bottles", true),
                MaterialItem("Clear Ziploc Plastic Bag", "1 bag", true),
                MaterialItem("Rubber Band & Scissors", "1 set", false)
            ),
            procedure = listOf(
                ProcedureStep(
                    stepNumber = 1,
                    title = "Prepare Colored Water Cups",
                    instruction = "Fill two clear glasses with water and stir in 10-15 drops of vibrant blue and red food coloring.",
                    proTip = "Vibrant concentrated colors show up best in flower petals."
                ),
                ProcedureStep(
                    stepNumber = 2,
                    title = "Trim Stems & Insert",
                    instruction = "Trim the bottom of the celery stalk or carnation stem diagonally underwater. Place one stem in blue water and one in red water.",
                    proTip = "Cutting underwater prevents air bubbles from blocking xylem vessels."
                ),
                ProcedureStep(
                    stepNumber = 3,
                    title = "Bag a Living Leaf for Transpiration",
                    instruction = "Tie a clear plastic bag over a green leafy branch of an outdoor potted plant and seal it with a rubber band.",
                    proTip = "Place the plant in warm sunlight for 2-3 hours."
                ),
                ProcedureStep(
                    stepNumber = 4,
                    title = "Observe Color Climb & Water Beads",
                    instruction = "After 3 hours, observe colored veins in celery and dyed flower petals. Check the plastic bag to see mist and condensed water droplets.",
                    proTip = "Splitting a single stem into two colored cups creates a half-blue, half-red flower!"
                )
            ),
            workingPrinciple = "Capillary Action & Transpirational Pull: Water molecules stick to each other (cohesion) and to the walls of microscopic plant xylem vessels (adhesion). As water evaporates from microscopic leaf pores (stomata) during transpiration, negative suction pressure pulls water upward against gravity from roots to leaves.",
            scientificLaw = "Cohesion-Tension Theory & Capillary Action in Xylem",
            result = "White flower petals turn distinctly blue and red as colored water travels up the stem. Clear condensation droplets accumulate inside the sealed leaf bag.",
            conclusion = "Plants continuously pump water and dissolved soil minerals upward without mechanical pumps using capillary action and transpirational suction.",
            vivaQuestions = listOf(
                VivaQuestion(
                    question = "What are the microscopic tubes that carry water in plants called?",
                    answer = "Xylem vessels (part of the plant's vascular system)."
                ),
                VivaQuestion(
                    question = "What is transpiration?",
                    answer = "Transpiration is the biological process where water evaporates into the atmosphere as water vapor from the aerial parts of plants, mainly through leaf stomata."
                ),
                VivaQuestion(
                    question = "What are stomata?",
                    answer = "Stomata are tiny microscopic pores on the underside of leaves guarded by guard cells, allowing gas exchange (CO2 / O2) and water vapor release."
                )
            ),
            tags = listOf("Plants", "Biology", "Capillary", "Nature", "Class 2")
        )
    )
}
