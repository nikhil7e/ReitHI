window.hide = hide;
window.updateTextInput = updateTextInput;

function hide() {
    const courses = document.querySelectorAll(".searchResult");
    
    for (let i = 0; i < courses.length; i++) {
        const course = courses.item(i);


        const credit = parseInt(course.querySelector(".courseCredits").textContent.slice(0,2));
        const level = course.querySelector(".level").textContent;
        console.log(level);
        const overall = parseFloat(course.querySelector(".overall").textContent.slice(16,19));
        const difficulty = parseFloat(course.querySelector(".difficulty").textContent.slice(12,15));
        const workload = parseFloat(course.querySelector(".workload").textContent.slice(10, 13));
        const teachingQuality = parseFloat(course.querySelector(".teachingQuality").textContent.slice(18,21));
        const courseMaterial = parseFloat(course.querySelector(".courseMaterial").textContent.slice(17,20));

        const creditFilterMax = parseInt(document.querySelector("#creditsMaxSpan").textContent);
        const creditFilterMin = parseInt(document.querySelector("#creditsMinSpan").textContent);
        const graduateFilter = document.querySelector("#graduate").checked;
        console.log(graduateFilter);
        const undergraduateFilter = document.querySelector("#underGraduate").checked;
        console.log(undergraduateFilter);
        const overallFilterMax = parseInt(document.querySelector("#overallMaxSpan").textContent);
        const overallFilterMin = parseInt(document.querySelector("#overallMinSpan").textContent);
        const difficultyFilterMax = parseInt(document.querySelector("#difficultyMaxSpan").textContent);
        const difficultyFilterMin = parseInt(document.querySelector("#difficultyMinSpan").textContent);
        const workloadFilterMax = parseInt(document.querySelector("#workloadMaxSpan").textContent);
        const workloadFilterMin = parseInt(document.querySelector("#workloadMinSpan").textContent);
        const teachingQualityFilterMax = parseInt(document.querySelector("#teachingQualityMaxSpan").textContent);
        const teachingQualityFilterMin = parseInt(document.querySelector("#teachingQualityMinSpan").textContent);
        const courseMaterialFilterMax = parseInt(document.querySelector("#courseMaterialMaxSpan").textContent);
        const courseMaterialFilterMin = parseInt(document.querySelector("#courseMaterialMinSpan").textContent);

        course.removeAttribute("hidden")

        hide = false;

        if (credit < creditFilterMin) {
            hide = true;
            console.log("true")
        }
        if (credit > creditFilterMax) {
            hide = true;
            console.log("true")
        }

        if (overall < overallFilterMin) {
            hide = true;
            console.log("true")
        }
        if (overall > overallFilterMax) {
            hide = true;
            console.log("true")
        }

        if (difficulty < difficultyFilterMin) {
            hide = true;
            console.log("true")
        }
        if (difficulty > difficultyFilterMax) {
            hide = true;
            console.log("true")
        }

        if (workload < workloadFilterMin) {
            hide = true;
            console.log("true")
        }
        if (workload > workloadFilterMax) {
            hide = true;
            console.log("true")
        }

        if (teachingQuality < teachingQualityFilterMin) {
            hide = true;
            console.log("true")
        }
        if (teachingQuality > teachingQualityFilterMax) {
            hide = true;
            console.log("true")
        }

        if (courseMaterial < courseMaterialFilterMin) {
            hide = true;
            console.log("true")
        }
        if (courseMaterial > courseMaterialFilterMax) {
            hide = true;
            console.log("true")
        }

        if (undergraduateFilter && level == "Graduate") {
            hide = true;
        }
        if (graduateFilter && level == "Undergraduate") {
            hide = true;
        }

        if (hide) {
            course.setAttribute("hidden" , "true" );
        }
    }
}

function updateTextInput() {
    document.getElementById("creditsMaxSpan").textContent = document.getElementById("creditsMax").value;
    document.getElementById("creditsMinSpan").textContent = document.getElementById("creditsMin").value;

    document.getElementById("overallMaxSpan").textContent = document.getElementById("overallMax").value;
    document.getElementById("overallMinSpan").textContent = document.getElementById("overallMin").value;

    document.getElementById("difficultyMaxSpan").textContent = document.getElementById("difficultyMax").value;
    document.getElementById("difficultyMinSpan").textContent = document.getElementById("difficultyMin").value;

    document.getElementById("workloadMaxSpan").textContent = document.getElementById("workloadMax").value;
    document.getElementById("workloadMinSpan").textContent = document.getElementById("workloadMin").value;

    document.getElementById("teachingQualityMaxSpan").textContent = document.getElementById("teachingQualityMax").value;
    document.getElementById("teachingQualityMinSpan").textContent = document.getElementById("teachingQualityMin").value;

    document.getElementById("courseMaterialMaxSpan").textContent = document.getElementById("courseMaterialMax").value;
    document.getElementById("courseMaterialMinSpan").textContent = document.getElementById("courseMaterialMin").value;

    }

updateTextInput();
hide();

export {hide};
export {updateTextInput};