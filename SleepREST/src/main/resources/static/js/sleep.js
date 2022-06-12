window.addEventListener("load", function(e) {
	console.log("page loaded");
	init();
});

function init() {
	console.log("in init");
	retrieveActivities();
	retrieveAllSleep();
	retrieveRecommendations();
	addEventListeners();
}


function populateSleepForm(allActivities) {

	let activityList = document.sleepForm.eveningActivity;

	while (activityList.firstElementChild) {
		activityList.removeChild(activityList.firstElementChild);
	}

	console.log("populating forms")
	console.log(allActivities)
	for (let activity of allActivities) {
		let option = document.createElement('option');
		option.value = activity.id;
		option.textContent = activity.name;
		activityList.appendChild(option);
	}


}

function addEventListeners() {
	let headers = document.getElementsByTagName('th');
	for (let header of headers) {

		header.addEventListener("click", sortRows);
	}
	let sleepFormSubmit = document.sleepForm.submit;
	sleepFormSubmit.addEventListener("click", addSleep)
	let activityFormSubmit = document.activityForm.submit;
	activityFormSubmit.addEventListener("click", addActivity)
}

//************************** POST Functions  ************************** */

let addSleep = function(e) {
	e.preventDefault();
	let newSleep = {
		start: document.sleepForm.timeStart.value,
		end: document.sleepForm.timeEnd.value,
		quality: document.sleepForm.quality.value,
		eveningActivity: {
			id: document.sleepForm.eveningActivity.value
		},
		exercised: document.sleepForm.exercised.value,
		workout: {
			id: document.sleepForm.timeOfDay.value
		},
		hadAlcohol: document.sleepForm.hadAlcohol.value,
		tookNap: document.sleepForm.tookNap.value,
		largeDinner: document.sleepForm.largeDinner.value
	};

	let sleepJSON = JSON.stringify(newSleep);

	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/sleep', true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				let addedSleep = JSON.parse(xhr.responseText);
				console.log(addedSleep.id);
				retrieveRecommendations
			}
			else {
				console.error("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}

	};
	xhr.send(sleepJSON);


}

let addActivity = function(e) {
	e.preventDefault(e)

	let newActivity = {
		name: document.activityForm.name.value
	};

	let activityJSON = JSON.stringify(newActivity);

	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/activities', true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				let addedActivity = JSON.parse(xhr.responseText);
				console.log(addedActivity.id);
				retrieveActivities();
			}
			else {
				console.error("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}

	};
	xhr.send(activityJSON);
}

//************************** End POST Functions  *********************** */

//****************************Sort Functions */


let sortRows = function(e) {
	console.log("sorting rows")
	let dataRows = document.getElementsByClassName('dataRow');
	let sorted = true;
	let idx = e.target.abbr;
	console.log("by index: " + idx);
	do {
		flip = false;
		for (let i = 0; i < dataRows.length - 1; i++) {
			let holder;
			let display = document.getElementById('dataDisplay')
			let currentRowValue = dataRows[i].children[idx].textContent;
			let nextRowValue = dataRows[i + 1].children[idx].textContent;
			if ((currentRowValue - nextRowValue) > 0){
				sort = true;
			}else if(currentRowValue > nextRowValue && isNaN(currentRowValue - nextRowValue)){
				sort = true;
			}else{
				sort = false;
			}

			if (sort) {
				holder = dataRows[i + 1];
				currentNode = display.children[i];
				display.removeChild(display.children[i + 1])
				display.insertBefore(holder, currentNode);

				flip = true;
				sorted = false;
			}
			if (!flip) {
				sorted = true;
			}
		}

	} while (!sorted)
}

//*******************************End Sort Functions */


//**************************** Retrieval Functions **************************** */

let retrieveActivities = function() {
	console.log("getting activities")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/activities", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let jsonActivities = ajax.responseText;
				let activities = JSON.parse(jsonActivities);
				console.log(activities);
				populateSleepForm(activities);
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();
}
let retrieveAllSleep = function() {
	console.log("getting sleep")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/sleep", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let jsonSleep = ajax.responseText;
				let allSleep = JSON.parse(jsonSleep);
				displaySleepData(allSleep)
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();
}

let retrieveRecommendations = function() {
	console.log("getting recommendations")
	let ajax = new XMLHttpRequest();
	let recommendations = ["goodActivities", "badActivities", "dinner", "nap", "workout", "sleepStart", "sleepEnd", "duration"]
	for (let recommendation of recommendations) {
		ajax.open('Get', "api/" + recommendation, true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState === 4) {
				if (ajax.status === 200) {
					let h = document.createElement('h3');
					let jsonWording = ajax.responseText;
					let wording = JSON.parse(jsonWording);
					h.textValue = wording;

				} else {
					console.log("error making request: " + ajax.status);
				}
			}
		};
		ajax.send();

	}


}



//****************************  End Retrieval Functions *********************** */
//******************************Start Display Functions *********************** */
let displaySleepData = function(sleep) {
	let sleepTableBody = document.getElementById('dataDisplay')

	for (let night of sleep) {
		let row = document.createElement('tr');
		row.id = night.id;
		row.className = "dataRow";
		sleepTableBody.appendChild(row);
		row = document.getElementById(night.id);
		let trQuality = document.createElement('td');
		trQuality.textContent = night.quality;
		row.appendChild(trQuality);
		let trDate = document.createElement('td');
		trDate.textContent = night.start.substring(0, 10)
		row.appendChild(trDate);
		let trStart = document.createElement('td');
		trStart.textContent = night.start.substring(11);
		row.appendChild(trStart);
		let trEnd = document.createElement('td');
		trEnd.textContent = night.end.substring(11);
		row.appendChild(trEnd);
		let trDuration = document.createElement('td');
		trDuration.textContent = night.duration;
		row.appendChild(trDuration);
		let trEveningActivity = document.createElement('td');
		trEveningActivity.textContent = night.eveningActivity.name;
		row.appendChild(trEveningActivity);
		let trExercised = document.createElement('td');
		if (night.exercised === true) {
			trExercised.textContent = "Yes";
		} else {
			trExercised.textContent = "No";
		}
		row.appendChild(trExercised);
		let trWorkout = document.createElement('td');
		if (night.exercised === true) {
			trWorkout.textContent = night.workout.timeOfDay;
		} else {
			trWorkout.textContent = "N/A";
		}
		row.appendChild(trWorkout);
		let trAlcohol = document.createElement('td');
		if (night.hadAlcohol === true) {
			trAlcohol.textContent = "Yes";
		} else {
			trAlcohol.textContent = "No";
		}
		row.appendChild(trAlcohol);
		let trNap = document.createElement('td');
		if (night.tookNap === true) {
			trNap.textContent = "Yes";
		} else {
			trNap.textContent = "No";
		}
		row.appendChild(trNap);
		let trId = document.createElement('td');
		trId.textContent = night.id;
		row.appendChild(trId);

	}


}



//******************************End Display Functions  ************************* */