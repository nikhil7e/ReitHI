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

        const creditFilterMax = parseInt(document.querySelector(".creditsSlideMax").value);
        const creditFilterMin = parseInt(document.querySelector(".creditsSlideMin").value);
        const graduateFilter = document.querySelector("#graduate").checked;
        console.log(graduateFilter);
        const undergraduateFilter = document.querySelector("#underGraduate").checked;
        console.log(undergraduateFilter);
        const overallFilterMax = parseInt(document.querySelector(".overallSlideMax").value);
        const overallFilterMin = parseInt(document.querySelector(".overallSlideMin").value);
        const difficultyFilterMax = parseInt(document.querySelector(".difficultySlideMax").value);
        const difficultyFilterMin = parseInt(document.querySelector(".difficultySlideMin").value);
        const workloadFilterMax = parseInt(document.querySelector(".workloadSlideMax").value);
        const workloadFilterMin = parseInt(document.querySelector(".workloadSlideMin").value);
        const teachingQualityFilterMax = parseInt(document.querySelector(".teachingQualitySlideMax").value);
        const teachingQualityFilterMin = parseInt(document.querySelector(".teachingQualitySlideMin").value);
        const courseMaterialFilterMax = parseInt(document.querySelector(".courseMaterialSlideMax").value);
        const courseMaterialFilterMin = parseInt(document.querySelector(".courseMaterialSlideMin").value);

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
            console.log("true "+ difficultyFilterMin)
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
    //document.getElementById("creditsMaxSpan").textContent = document.getElementById("creditsMax").value;
    //document.getElementById("creditsMinSpan").textContent = document.getElementById("creditsMin").value;

    //document.getElementById("overallMaxSpan").textContent = document.getElementById("overallMax").value;
    //document.getElementById("overallMinSpan").textContent = document.getElementById("overallMin").value;

    //document.getElementById("difficultyMaxSpan").textContent = document.getElementById("difficultyMax").value;
    //document.getElementById("difficultyMinSpan").textContent = document.getElementById("difficultyMin").value;

    //document.getElementById("workloadMaxSpan").textContent = document.getElementById("workloadMax").value;
    //document.getElementById("workloadMinSpan").textContent = document.getElementById("workloadMin").value;

    //document.getElementById("teachingQualityMaxSpan").textContent = document.getElementById("teachingQualityMax").value;
    //document.getElementById("teachingQualityMinSpan").textContent = document.getElementById("teachingQualityMin").value;

    //document.getElementById("courseMaterialMaxSpan").textContent = document.getElementById("courseMaterialMax").value;
    //document.getElementById("courseMaterialMinSpan").textContent = document.getElementById("courseMaterialMin").value;

    }


function controlFromInput(fromSlider, fromInput, toInput, controlSlider) {
    const [from, to] = getParsed(fromInput, toInput);
    fillSlider(fromInput, toInput, '#C6C6C6', '#25daa5', controlSlider);
    if (from > to) {
        fromSlider.value = to;
        fromInput.value = to;
    } else {
        fromSlider.value = from;
    }
}

function controlToInput(toSlider, fromInput, toInput, controlSlider) {
    const [from, to] = getParsed(fromInput, toInput);
    fillSlider(fromInput, toInput, '#C6C6C6', '#25daa5', controlSlider);
    setToggleAccessible(toInput);
    if (from <= to) {
        toSlider.value = to;
        toInput.value = to;
    } else {
        toInput.value = from;
    }
}

function controlFromSlider(fromSlider, toSlider, fromInput) {
    const [from, to] = getParsed(fromSlider, toSlider);
    fillSlider(fromSlider, toSlider, '#C6C6C6', '#25daa5', toSlider);
    if (from > to) {
        fromSlider.value = to;
        fromInput.value = to;
    } else {
        fromInput.value = from;
    }
}

function controlToSlider(fromSlider, toSlider, toInput) {
    const [from, to] = getParsed(fromSlider, toSlider);
    fillSlider(fromSlider, toSlider, '#C6C6C6', '#25daa5', toSlider);
    setToggleAccessible(toSlider);
    if (from <= to) {
        toSlider.value = to;
        toInput.value = to;
    } else {
        toInput.value = from;
        toSlider.value = from;
    }
}

function getParsed(currentFrom, currentTo) {
    const from = parseInt(currentFrom.value, 10);
    const to = parseInt(currentTo.value, 10);
    return [from, to];
}

function fillSlider(from, to, sliderColor, rangeColor, controlSlider) {
    const rangeDistance = to.max-to.min;
    const fromPosition = from.value - to.min;
    const toPosition = to.value - to.min;
    controlSlider.style.background = `linear-gradient(
      to right,
      ${sliderColor} 0%,
      ${sliderColor} ${(fromPosition)/(rangeDistance)*100}%,
      ${rangeColor} ${((fromPosition)/(rangeDistance))*100}%,
      ${rangeColor} ${(toPosition)/(rangeDistance)*100}%, 
      ${sliderColor} ${(toPosition)/(rangeDistance)*100}%, 
      ${sliderColor} 100%)`;
}

function setToggleAccessible(currentTarget) {
    if (Number(currentTarget.value) <= 0 ) {
        currentTarget.style.zIndex = "2";
    } else {
        currentTarget.style.zIndex = "0";
    }
}

const fromSliders = document.querySelectorAll('.slideMin');
const toSliders = document.querySelectorAll('.slideMax');
console.log(fromSliders)
console.log(toSliders)
const fromInputs = document.querySelectorAll('.fromInput');
const toInputs = document.querySelectorAll('.toInput');
console.log(fromInputs)
console.log(toInputs)

for (let i = 0; i<fromSliders.length; i++){
    console.log(i)
    let fromSlider = fromSliders.item(i);
    console.log(fromSlider)
    let toSlider = toSliders.item(i);

    let fromInput = fromInputs.item(i);
    let toInput = toInputs.item(i);
    fillSlider(fromSlider, toSlider, '#C6C6C6', '#25daa5', toSlider);
    setToggleAccessible(toSlider);

    fromSlider.oninput = () => controlFromSlider(fromSlider, toSlider, fromInput);
    toSlider.oninput = () => controlToSlider(fromSlider, toSlider, toInput);

    fromInput.oninput = () => controlFromInput(fromSlider, fromInput, toInput, toSlider);
    toInput.oninput = () => controlToInput(toSlider, fromInput, toInput, toSlider);
}






updateTextInput();
hide();

export {hide};
export {updateTextInput};