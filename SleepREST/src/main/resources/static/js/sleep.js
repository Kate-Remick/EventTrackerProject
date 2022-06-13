window.addEventListener("load", function(e) {
	console.log("page loaded");
	init();
});

function init() {
	console.log("in init");
	retrieveActivities();
	retrieveAllSleep();
	let recommendations = ["goodActivities", "badActivities", "dinner", "nap", "workout", "sleepStart", "sleepEnd", "duration"];
	for (let recommendation of recommendations) {
		retrieveRecommendation(recommendation);
	}
	addEventListeners();
}


function populateSleepForm(allActivities) {

	let activityList = document.sleepForm.eveningActivity;
	let activityListFind = document.getElementById('eveningActivityFind');
	while (activityList.firstElementChild) {
		activityList.removeChild(activityList.firstElementChild);
	}

	console.log("populating forms")
	//console.log(allActivities)
	for (let activity of allActivities) {
		let option = document.createElement('option');
		option.value = activity.id;
		option.textContent = activity.name;
		activityList.appendChild(option);
	}
	for (let activity of allActivities) {
		let option = document.createElement('option');
		option.value = activity.id;
		option.textContent = activity.name;
		activityList.appendChild(option);
		activityListFind.appendChild(option);
	}
	for (let activity of allActivities) {
		let option = document.createElement('option');
		option.value = activity.id;
		option.textContent = activity.name;
		activityList.appendChild(option);
		activityListFind.appendChild(option);
		document.editSleepForm.eveningActivity.appendChild(option);
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
	document.editSleepForm.edit.addEventListener('click', editSleep);
	activityFormSubmit.addEventListener("click", addActivity)
	document.findSleep.findById.addEventListener('click', retrieveSleepById)
	document.findSleep.findByQuality.addEventListener('click',retrieveSleepByQuality )
	document.findSleep.findByWorkoutTime.addEventListener('click', retrieveSleepByWorkout)
	document.findSleep.findByActivity.addEventListener('click', retrieveSleepByActivity)
	document.findSleep.findAllSleep.addEventListener('click', function(e){
		e.preventDefault();
		retrieveAllSleep();
	});
	document.sleepForm.exercised.addEventListener('click', checkExercise)
	document.editSleepForm.exercised.addEventListener('click', checkExercise)
}

//************************** POST/PUT Functions  ************************** */

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
				let recommendations = ["goodActivities", "badActivities", "dinner", "nap", "workout", "sleepStart", "sleepEnd", "duration"];
				for (let recommendation of recommendations) {
					retrieveRecommendation(recommendation);
				}
			}
			else {
				console.error("POST request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}

	};
	xhr.send(sleepJSON);


}
let editSleep = function(e) {
	console.log("editing sleep")
	e.preventDefault();
	let editSleep = {
		id: document.editSleepForm.id.value,
		start: document.editSleepForm.timeStart.value,
		end: document.editSleepForm.timeEnd.value,
		quality: document.editSleepForm.quality.value,
		eveningActivity: {
			id: document.editSleepForm.eveningActivity.value
		},
		exercised: document.editSleepForm.exercised.value,
		workout: {
			id: document.editSleepForm.timeOfDay.value
		},
		hadAlcohol: document.editSleepForm.hadAlcohol.value,
		tookNap: document.editSleepForm.tookNap.value,
		largeDinner: document.editSleepForm.largeDinner.value
	};

	let sleepJSON = JSON.stringify(editSleep);

	let xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/sleep/' + editSleep.id, true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				let addedSleep = JSON.parse(xhr.responseText);
				console.log(addedSleep.id);
				retrieveAllSleep();
				let recommendations = ["goodActivities", "badActivities", "dinner", "nap", "workout", "sleepStart", "sleepEnd", "duration"];
				for (let recommendation of recommendations) {
					retrieveRecommendation(recommendation);
				}
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

//************************** End POST/PUT Functions  *********************** */
//************************** Start Edit functions ******************* */
let editNight = function(e) {
	e.preventDefault();
	let editedSleep = {
		id: e.target.parentElement.parentElement.id,
		start: document.editSleepForm.timeStart.value,
		end: document.editSleepForm.timeEnd.value,
		quality: document.editSleepForm.quality.value,
		eveningActivity: {
			id: document.editSleepForm.eveningActivity.value
		},
		exercised: document.editSleepForm.exercised.value,
		workout: {
			id: document.editSleepForm.timeOfDay.value
		},
		hadAlcohol: document.editSleepForm.hadAlcohol.value,
		tookNap: document.editSleepForm.tookNap.value,
		largeDinner: document.editSleepForm.largeDinner.value
	};

	let sleepJSON = JSON.stringify(newSleep);

	let xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/sleep', true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
				let editedSleep = JSON.parse(xhr.responseText);
				console.log(editedSleep.id);

				let recommendations = ["goodActivities", "badActivities", "dinner", "nap", "workout", "sleepStart", "sleepEnd", "duration"];
				for (let recommendation of recommendations) {
					retrieveRecommendation(recommendation);
				}
			}
			else {
				console.error("PUT request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}

	};
	xhr.send(sleepJSON);
}

//************************** End Edit functions ******************* */
//********************** Delete Function ********************************8*/
let removeNight = function(e) {
	console.log("removing night")
	let sleepId = e.target.parentElement.id;
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/sleep/' + sleepId, true);
	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 204 || xhr.status == 200) { // Ok or Created

				console.log("deleted");
				retrieveAllSleep();
			}
			else {
				console.error("DELETE request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}

	};
	xhr.send();
}

/********************** End Delete Function ********************************8*/
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
			if ((currentRowValue - nextRowValue) > 0) {
				sort = true;
			} else if (currentRowValue > nextRowValue && isNaN(currentRowValue - nextRowValue)) {
				sort = true;
			} else {
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
let retrieveSleepById = function(e){
	e.preventDefault();
	let id = document.findSleep.id.value;
	console.log("getting sleep by id")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/sleep/" + id, true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let jsonSleep = ajax.responseText;
				let allSleep = JSON.parse(jsonSleep);
				displaySingleSleepData(allSleep)
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();
}
let retrieveSleepByWorkout = function(e){
	e.preventDefault();
	let id = document.findSleep.workoutTime.value;
	console.log("getting sleep by workout")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/sleep/workout/"  + id, true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				
				let jsonSleep = ajax.responseText;
				let allSleep = JSON.parse(jsonSleep);
				displaySleepData(allSleep);
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();
}
let retrieveSleepByActivity = function(e){
	e.preventDefault();
	let id = document.findSleep.eveningActivityFind.value;
	console.log("getting sleep by workout")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/sleep/activity/"  + id, true);
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
let retrieveSleepByQuality = function(e){
	e.preventDefault();
	let min = document.findSleep.min.value;
	let max = document.findSleep.max.value;
	console.log("getting sleep by quality")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/sleep/" + min + "/"  + max, true);
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


let retrieveActivities = function() {
	console.log("getting activities")
	let ajax = new XMLHttpRequest();
	ajax.open('Get', "api/activities", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let jsonActivities = ajax.responseText;
				let activities = JSON.parse(jsonActivities);
				//console.log(activities);
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

let retrieveRecommendation = function(recommendation) {
	//console.log("getting recommendations")
	let ajax = new XMLHttpRequest();
	//	console.log(recommendation)

	ajax.open('Get', "api/recommendations/" + recommendation, true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let recDiv = document.getElementById('recommendations');
				let h = document.createElement('h5');
				console.log("change ")
				let jsonWording = ajax.responseText;
				if (typeof jsonWording === 'string') {
					//console.log(jsonWording)
					h.textContent = jsonWording;
				} else {
					let wording = JSON.parse(jsonWording);
					h.textContent = wording;
				}
				recDiv.appendChild(h);
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();




}

let retrieveWorkouts = function() {
	//console.log("getting recommendations")
	let ajax = new XMLHttpRequest();
	console.log(recommendation)

	ajax.open('Get', "api/recommendations/" + recommendation, true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState === 4) {
			if (ajax.status === 200) {
				let recDiv = document.getElementById('recommendations');
				let h = document.createElement('h6');
				console.log("change ")
				let jsonWording = ajax.responseText;
				if (typeof jsonWording === 'string') {
					//console.log(jsonWording)
					h.textContent = jsonWording;
				} else {
					let wording = JSON.parse(jsonWording);
					h.textContent = "- " + wording;
				}
				h.className = "col-1";
				recDiv.appendChild(h);
			} else {
				console.log("error making request: " + ajax.status);
			}
		}
	};
	ajax.send();




}



//****************************  End Retrieval Functions *********************** */
//******************************Start Display Functions *********************** */
let displaySleepData = function(sleep) {
	let sleepTableBody = document.getElementById('dataDisplay')
	while (sleepTableBody.firstElementChild) {
		sleepTableBody.removeChild(sleepTableBody.firstElementChild);
	}

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
		let edit = document.createElement('td');
		edit.textContent = "Edit";
		edit.className = "edit col-1";
		row.appendChild(edit);
		let edits = document.getElementsByClassName("edit");
		for (let item of edits) {
			item.addEventListener("click", editNight);
		}
		let del = document.createElement('td');
		del.textContent = "Delete";
		del.className = "delete col-1";
		row.appendChild(del);
		let deletes = document.getElementsByClassName("delete");
		for (let item of deletes) {
			item.addEventListener("click", removeNight);
		}

	}


}
let displaySingleSleepData = function(sleep) {
	let sleepTableBody = document.getElementById('dataDisplay')
	while (sleepTableBody.firstElementChild) {
		sleepTableBody.removeChild(sleepTableBody.firstElementChild);
	}
	let night = sleep
	
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
		let edit = document.createElement('td');
		edit.textContent = "Edit";
		edit.className = "edit col-1";
		row.appendChild(edit);
		let edits = document.getElementsByClassName("edit");
		for (let item of edits) {
			item.addEventListener("click", editNight);
		}
		let del = document.createElement('td');
		del.textContent = "Delete";
		del.className = "delete col-1";
		row.appendChild(del);
		let deletes = document.getElementsByClassName("delete");
		for (let item of deletes) {
			item.addEventListener("click", removeNight);
		

	}


}


//******************************End Display Functions  ************************* */

// ******************************** Check Functions ************************



// ******************************** End Check Functions ************************