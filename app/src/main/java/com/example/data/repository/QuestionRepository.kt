package com.example.data.repository

import com.example.data.model.Question

object QuestionRepository {

    /**
     * A curated collection of 51 scientifically accurate multiple-choice questions
     * across Biology, Chemistry, Physics, Astronomy, and Earth Science.
     */
    private val questionsList: List<Question> = listOf(
        Question(
            id = 1,
            questionText = "Which organelle is widely known as the powerhouse of the cell?",
            options = listOf("Mitochondria", "Ribosome", "Endoplasmic Reticulum", "Golgi Apparatus"),
            correctOptionIndex = 0,
            explanation = "Mitochondria generate most of the chemical energy needed to power the cell's biochemical reactions through ATP production.",
            category = "Biology"
        ),
        Question(
            id = 2,
            questionText = "Which planet in our solar system is nicknamed the 'Red Planet'?",
            options = listOf("Venus", "Mars", "Jupiter", "Mercury"),
            correctOptionIndex = 1,
            explanation = "Mars appears reddish due to the pervasive presence of iron oxide (rust) on its surface.",
            category = "Astronomy"
        ),
        Question(
            id = 3,
            questionText = "What is the chemical formula for water?",
            options = listOf("CO2", "H2O", "NaCl", "O2"),
            correctOptionIndex = 1,
            explanation = "Water consists of two hydrogen atoms covalently bonded to a single oxygen atom (H2O).",
            category = "Chemistry"
        ),
        Question(
            id = 4,
            questionText = "Which gas do green plants primarily absorb from the air during photosynthesis?",
            options = listOf("Oxygen", "Nitrogen", "Carbon Dioxide", "Argon"),
            correctOptionIndex = 2,
            explanation = "Plants take in carbon dioxide (CO2) from the atmosphere and use sunlight to convert it into glucose and oxygen.",
            category = "Biology"
        ),
        Question(
            id = 5,
            questionText = "What is the approximate speed of light in a vacuum?",
            options = listOf("150,000 km/s", "300,000 km/s", "500,000 km/s", "1,000,000 km/s"),
            correctOptionIndex = 1,
            explanation = "Light travels through a vacuum at roughly 299,792 kilometers per second (commonly rounded to 300,000 km/s).",
            category = "Physics"
        ),
        Question(
            id = 6,
            questionText = "What is the dense central region of an atom called?",
            options = listOf("Proton", "Nucleus", "Electron Cloud", "Quark"),
            correctOptionIndex = 1,
            explanation = "The nucleus sits at the center of an atom, containing nearly all its mass in protons and neutrons.",
            category = "Physics"
        ),
        Question(
            id = 7,
            questionText = "Which fundamental force attracts objects toward the center of the Earth?",
            options = listOf("Electromagnetism", "Strong Nuclear Force", "Gravity", "Friction"),
            correctOptionIndex = 2,
            explanation = "Gravity is the universal attractive force between all masses, pulling downward toward Earth's center of mass.",
            category = "Physics"
        ),
        Question(
            id = 8,
            questionText = "Which gas makes up approximately 78% of Earth's atmosphere?",
            options = listOf("Oxygen", "Nitrogen", "Argon", "Carbon Dioxide"),
            correctOptionIndex = 1,
            explanation = "Nitrogen (N2) is the most abundant atmospheric gas, constituting about 78% of dry air by volume.",
            category = "Earth Science"
        ),
        Question(
            id = 9,
            questionText = "Which chemical element is represented by the atomic symbol 'Au'?",
            options = listOf("Silver", "Gold", "Copper", "Aluminum"),
            correctOptionIndex = 1,
            explanation = "'Au' comes from the Latin word 'aurum', meaning shining dawn or gold.",
            category = "Chemistry"
        ),
        Question(
            id = 10,
            questionText = "What type of energy is stored in an object because of its elevated position?",
            options = listOf("Kinetic energy", "Thermal energy", "Gravitational potential energy", "Chemical energy"),
            correctOptionIndex = 2,
            explanation = "Gravitational potential energy depends on mass, gravitational acceleration, and height above reference point.",
            category = "Physics"
        ),
        Question(
            id = 11,
            questionText = "How many bones are in the typical adult human skeleton?",
            options = listOf("186", "206", "226", "256"),
            correctOptionIndex = 1,
            explanation = "An adult human has 206 bones; infants start with around 270 bones, many of which fuse during growth.",
            category = "Biology"
        ),
        Question(
            id = 12,
            questionText = "What is the closest star to planet Earth?",
            options = listOf("Proxima Centauri", "Sirius", "The Sun", "Alpha Centauri A"),
            correctOptionIndex = 2,
            explanation = "The Sun is the closest star to Earth, located at an average distance of about 149.6 million kilometers (1 AU).",
            category = "Astronomy"
        ),
        Question(
            id = 13,
            questionText = "Which subatomic particle carries a negative electric charge?",
            options = listOf("Proton", "Neutron", "Electron", "Positron"),
            correctOptionIndex = 2,
            explanation = "Electrons carry a single negative elementary electric charge (-1e) and orbit the nucleus.",
            category = "Physics"
        ),
        Question(
            id = 14,
            questionText = "What term describes the phase transition of liquid water turning into water vapor below its boiling point?",
            options = listOf("Sublimation", "Condensation", "Evaporation", "Deposition"),
            correctOptionIndex = 2,
            explanation = "Evaporation occurs at the liquid's surface as high-energy molecules escape into the vapor phase.",
            category = "Chemistry"
        ),
        Question(
            id = 15,
            questionText = "What is the pH value of pure, neutral water at 25°C?",
            options = listOf("0", "5", "7", "14"),
            correctOptionIndex = 2,
            explanation = "A pH of 7 is strictly neutral on the logarithmic scale, where concentrations of H+ and OH- ions are equal.",
            category = "Chemistry"
        ),
        Question(
            id = 16,
            questionText = "Which blood cells contain hemoglobin and transport oxygen to body tissues?",
            options = listOf("Platelets", "White blood cells", "Red blood cells", "Lymphocytes"),
            correctOptionIndex = 2,
            explanation = "Red blood cells (erythrocytes) are packed with hemoglobin, an iron-rich protein that binds oxygen molecules.",
            category = "Biology"
        ),
        Question(
            id = 17,
            questionText = "What is the hardest naturally occurring mineral on Mohs hardness scale?",
            options = listOf("Quartz", "Corundum", "Diamond", "Topaz"),
            correctOptionIndex = 2,
            explanation = "Diamond rates a maximum 10 on the Mohs scale due to its rigid tetrahedral covalent carbon crystal lattice.",
            category = "Earth Science"
        ),
        Question(
            id = 18,
            questionText = "Which layer of the Earth lies directly beneath the continental and oceanic crust?",
            options = listOf("Outer core", "Inner core", "Mantle", "Lithosphere"),
            correctOptionIndex = 2,
            explanation = "Earth's mantle is a thick rocky shell extending down roughly 2,900 kilometers to the outer core.",
            category = "Earth Science"
        ),
        Question(
            id = 19,
            questionText = "What is the International System of Units (SI) unit for electric current?",
            options = listOf("Volt", "Ampere", "Watt", "Ohm"),
            correctOptionIndex = 1,
            explanation = "The ampere (A) measures the rate of electric charge flow through a conductor per second.",
            category = "Physics"
        ),
        Question(
            id = 20,
            questionText = "Which human gland or organ secretes the hormone insulin?",
            options = listOf("Liver", "Pancreas", "Thyroid", "Kidney"),
            correctOptionIndex = 1,
            explanation = "Beta cells located within the islets of Langerhans in the pancreas secrete insulin to regulate blood glucose.",
            category = "Biology"
        ),
        Question(
            id = 21,
            questionText = "In which barred spiral galaxy is our solar system located?",
            options = listOf("Andromeda", "Milky Way", "Triangulum", "Sombrero"),
            correctOptionIndex = 1,
            explanation = "Our solar system resides in the Orion Arm of the Milky Way galaxy, containing hundreds of billions of stars.",
            category = "Astronomy"
        ),
        Question(
            id = 22,
            questionText = "What is the chemical name and formula for ordinary table salt?",
            options = listOf("Sodium chloride (NaCl)", "Potassium chloride (KCl)", "Calcium carbonate (CaCO3)", "Sodium hydroxide (NaOH)"),
            correctOptionIndex = 0,
            explanation = "Table salt is an ionic compound formed from sodium and chloride ions in a 1:1 ratio (NaCl).",
            category = "Chemistry"
        ),
        Question(
            id = 23,
            questionText = "What triggers the vibrant atmospheric displays known as the Aurora Borealis (Northern Lights)?",
            options = listOf("Solar wind interacting with Earth's magnetosphere", "Moonlight reflected off arctic ice", "Static electricity in winter storm clouds", "Volcanic ash in the stratosphere"),
            correctOptionIndex = 0,
            explanation = "Charged solar particles collide with atmospheric gases along Earth's magnetic field lines, emitting colourful photons.",
            category = "Earth Science"
        ),
        Question(
            id = 24,
            questionText = "Which colored muscular ring of the eye adjusts the size of the pupil?",
            options = listOf("Cornea", "Retina", "Iris", "Lens"),
            correctOptionIndex = 2,
            explanation = "The iris contains smooth muscle fibers that constrict or dilate the pupil to modulate incoming light.",
            category = "Biology"
        ),
        Question(
            id = 25,
            questionText = "How was Pluto officially reclassified by the International Astronomical Union in 2006?",
            options = listOf("Asteroid", "Comet", "Dwarf planet", "Exoplanet"),
            correctOptionIndex = 2,
            explanation = "Pluto meets roundness criteria but has not cleared its neighboring orbital path, designating it a dwarf planet.",
            category = "Astronomy"
        ),
        Question(
            id = 26,
            questionText = "Through which state of matter does sound generally travel at the greatest speed?",
            options = listOf("Vacuum", "Gas", "Liquid", "Solid"),
            correctOptionIndex = 3,
            explanation = "Solids have tightly packed atoms and high elastic moduli, transferring mechanical acoustic vibrations most rapidly.",
            category = "Physics"
        ),
        Question(
            id = 27,
            questionText = "Which primary pigment gives plants their green coloration and captures light energy?",
            options = listOf("Carotene", "Anthocyanin", "Chlorophyll", "Xanthophyll"),
            correctOptionIndex = 2,
            explanation = "Chlorophyll absorbs blue and red wavelengths while reflecting green light, facilitating photosynthesis.",
            category = "Biology"
        ),
        Question(
            id = 28,
            questionText = "What is the SI unit of measurement for electrical resistance?",
            options = listOf("Ohm", "Farad", "Henry", "Tesla"),
            correctOptionIndex = 0,
            explanation = "The ohm (symbol: Ω) quantifies electrical resistance, named after physicist Georg Simon Ohm.",
            category = "Physics"
        ),
        Question(
            id = 29,
            questionText = "Which atmospheric gas layer shields life on Earth by absorbing ultraviolet (UV-B) radiation?",
            options = listOf("Methane", "Ozone", "Nitrous oxide", "Carbon monoxide"),
            correctOptionIndex = 1,
            explanation = "The stratospheric ozone layer (O3) absorbs between 97% and 99% of the Sun's medium-frequency ultraviolet light.",
            category = "Earth Science"
        ),
        Question(
            id = 30,
            questionText = "Which renowned scientist formulated the three universal laws of classical motion?",
            options = listOf("Albert Einstein", "Sir Isaac Newton", "Galileo Galilei", "Niels Bohr"),
            correctOptionIndex = 1,
            explanation = "Isaac Newton published his three laws of motion in the 'Principia Mathematica' in 1687.",
            category = "Physics"
        ),
        Question(
            id = 31,
            questionText = "What is the chemical symbol for the metallic element Iron?",
            options = listOf("Ir", "In", "Fe", "I"),
            correctOptionIndex = 2,
            explanation = "'Fe' originates from the Latin 'ferrum', designating iron in the periodic table.",
            category = "Chemistry"
        ),
        Question(
            id = 32,
            questionText = "What type of chemical bond forms when two atoms share one or more pairs of valence electrons?",
            options = listOf("Ionic bond", "Covalent bond", "Hydrogen bond", "Metallic bond"),
            correctOptionIndex = 1,
            explanation = "A covalent bond consists of the electrostatic attraction between the atomic nuclei and shared electron pairs.",
            category = "Chemistry"
        ),
        Question(
            id = 33,
            questionText = "Which giant gas planet is famous for having the most extensive and visible ring system?",
            options = listOf("Uranus", "Neptune", "Jupiter", "Saturn"),
            correctOptionIndex = 3,
            explanation = "Saturn's rings consist predominantly of billions of water-ice particles coated with rocky dust.",
            category = "Astronomy"
        ),
        Question(
            id = 34,
            questionText = "What complex carbohydrate forms the primary structural fiber of plant cell walls?",
            options = listOf("Glycogen", "Starch", "Cellulose", "Chitin"),
            correctOptionIndex = 2,
            explanation = "Cellulose is an organic polysaccharide composed of linear chains of beta-glucose units, providing rigidity.",
            category = "Biology"
        ),
        Question(
            id = 35,
            questionText = "What is the standard boiling point of pure water at 1 standard atmosphere (101.3 kPa)?",
            options = listOf("80°C", "90°C", "100°C", "120°C"),
            correctOptionIndex = 2,
            explanation = "At standard sea-level atmospheric pressure, water boils vigorously and changes to steam at exactly 100°C (212°F).",
            category = "Physics"
        ),
        Question(
            id = 36,
            questionText = "Which pair of bean-shaped organs filters metabolic waste from blood to form urine?",
            options = listOf("Lungs", "Kidneys", "Spleen", "Gallbladder"),
            correctOptionIndex = 1,
            explanation = "Kidneys contain millions of microscopic nephrons that filter toxins, urea, and excess ions from the bloodstream.",
            category = "Biology"
        ),
        Question(
            id = 37,
            questionText = "Which form of electromagnetic radiation has the shortest wavelength and highest photon energy?",
            options = listOf("Radio waves", "Microwaves", "X-rays", "Gamma rays"),
            correctOptionIndex = 3,
            explanation = "Gamma rays possess the highest frequencies (above 10^19 Hz) and shortest wavelengths in the electromagnetic spectrum.",
            category = "Physics"
        ),
        Question(
            id = 38,
            questionText = "What does the first law of thermodynamics (conservation of energy) assert?",
            options = listOf("Energy constantly dissipates into nothing", "Energy cannot be created or destroyed, only transformed", "Thermal energy spontaneously flows from cold to hot", "Total mass increases with kinetic velocity"),
            correctOptionIndex = 1,
            explanation = "The law states that energy in an isolated system is constant; it can change forms (e.g. chemical to thermal) but not vanish.",
            category = "Physics"
        ),
        Question(
            id = 39,
            questionText = "What biological transformation process allows a caterpillar to develop into an adult butterfly?",
            options = listOf("Mitosis", "Metamorphosis", "Binary fission", "Meiosis"),
            correctOptionIndex = 1,
            explanation = "Holometabolism (complete metamorphosis) involves distinct life stages: egg, larva (caterpillar), pupa (chrysalis), and adult.",
            category = "Biology"
        ),
        Question(
            id = 40,
            questionText = "What is the chemical formula for carbon dioxide gas?",
            options = listOf("CO", "CO2", "C2O", "CH4"),
            correctOptionIndex = 1,
            explanation = "Carbon dioxide consists of one central carbon atom double-bonded to two oxygen atoms (CO2).",
            category = "Chemistry"
        ),
        Question(
            id = 41,
            questionText = "Which planet has the highest mean surface temperature in the solar system?",
            options = listOf("Mercury", "Venus", "Mars", "Jupiter"),
            correctOptionIndex = 1,
            explanation = "Venus experiences an extreme runaway greenhouse effect driven by its dense CO2 atmosphere, maintaining around 465°C.",
            category = "Astronomy"
        ),
        Question(
            id = 42,
            questionText = "Which critical organ is enclosed and guarded by the cranial bones?",
            options = listOf("Heart", "Spinal cord", "Brain", "Thyroid"),
            correctOptionIndex = 2,
            explanation = "The human cranium forms a rigid bony protective vault around the brain.",
            category = "Biology"
        ),
        Question(
            id = 43,
            questionText = "What biological classification describes animals whose diet naturally includes both plants and animal flesh?",
            options = listOf("Herbivores", "Carnivores", "Omnivores", "Detritivores"),
            correctOptionIndex = 2,
            explanation = "Omnivores possess anatomical and digestive adaptations enabling them to consume both flora and fauna.",
            category = "Biology"
        ),
        Question(
            id = 44,
            questionText = "Which transition metal remains in liquid state under standard room temperature and pressure?",
            options = listOf("Lead", "Mercury", "Tin", "Zinc"),
            correctOptionIndex = 1,
            explanation = "Mercury (Hg) has a melting point of -38.83°C, making it liquid under standard ambient conditions.",
            category = "Chemistry"
        ),
        Question(
            id = 45,
            questionText = "What is the estimated age of our universe based on cosmic microwave background measurements?",
            options = listOf("4.5 billion years", "13.8 billion years", "25 billion years", "100 billion years"),
            correctOptionIndex = 1,
            explanation = "Astrophysical data from the Planck satellite places the Big Bang at approximately 13.787 ± 0.020 billion years ago.",
            category = "Astronomy"
        ),
        Question(
            id = 46,
            questionText = "Which type of optical lens is thicker in the middle and causes parallel rays of light to converge?",
            options = listOf("Concave lens", "Convex lens", "Cylindrical lens", "Planar lens"),
            correctOptionIndex = 1,
            explanation = "A convex (converging) lens bends incoming parallel light rays inward toward a common focal point.",
            category = "Physics"
        ),
        Question(
            id = 47,
            questionText = "Which element makes up approximately three-quarters of the Sun's total mass?",
            options = listOf("Helium", "Hydrogen", "Carbon", "Iron"),
            correctOptionIndex = 1,
            explanation = "Hydrogen accounts for roughly 73% of the Sun's mass, actively fusing into helium in its blazing core.",
            category = "Astronomy"
        ),
        Question(
            id = 48,
            questionText = "Which digestive enzyme present in human saliva initiates the chemical breakdown of starches?",
            options = listOf("Pepsin", "Amylase", "Lipase", "Trypsin"),
            correctOptionIndex = 1,
            explanation = "Salivary amylase (ptyalin) catalyzes the hydrolysis of complex starches into maltose and dextrin.",
            category = "Biology"
        ),
        Question(
            id = 49,
            questionText = "What temperature corresponds to absolute zero on the Celsius scale?",
            options = listOf("0°C", "-100°C", "-273.15°C", "-459.67°C"),
            correctOptionIndex = 2,
            explanation = "Absolute zero (0 Kelvin) is the thermodynamic limit where particle thermal motion reaches minimum, equal to -273.15°C.",
            category = "Physics"
        ),
        Question(
            id = 50,
            questionText = "What type of cellular division yields two genetically identical diploid daughter cells?",
            options = listOf("Meiosis", "Mitosis", "Cytokinesis", "Apoptosis"),
            correctOptionIndex = 1,
            explanation = "Mitosis duplicates a cell's chromosomes equally into two identical daughter nuclei for growth and tissue repair.",
            category = "Biology"
        ),
        Question(
            id = 51,
            questionText = "Which fluid dynamics principle explains the aerodynamic lift generated by curved airplane wings?",
            options = listOf("Archimedes' principle", "Bernoulli's principle", "Pascal's principle", "Hooke's law"),
            correctOptionIndex = 1,
            explanation = "Bernoulli's principle indicates that an increase in fluid velocity occurs simultaneously with a decrease in static pressure.",
            category = "Physics"
        )
    )

    /**
     * Returns the list of 51 science questions.
     * If [randomizeQuestions] is true, questions are shuffled.
     * If [randomizeOptions] is true, each question's options are shuffled while updating the correct index.
     */
    fun getQuestions(
        randomizeQuestions: Boolean = true,
        randomizeOptions: Boolean = true
    ): List<Question> {
        val baseList = if (randomizeQuestions) questionsList.shuffled() else questionsList
        if (!randomizeOptions) return baseList

        return baseList.map { q ->
            val correctOptionText = q.options[q.correctOptionIndex]
            val shuffledOptions = q.options.shuffled()
            val newCorrectIndex = shuffledOptions.indexOf(correctOptionText)
            q.copy(
                options = shuffledOptions,
                correctOptionIndex = newCorrectIndex
            )
        }
    }

    fun getTotalQuestionCount(): Int = questionsList.size
}
