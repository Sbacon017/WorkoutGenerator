// Functions called upon initial action selection in main.js


function getXhttpResponse(xhttp){
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(xhttp.responseText);
    }, 1000);
  });
}

function getInput(inputOptions, inputName, functionToPerform){
  console.log("Adding input options to" + inputOptions);
  inputOptions.innerHTML = addInput(inputName, functionToPerform);
}

async function getMultiInput(targetLocation, inputNameArray, functionToPerform, userInput){
  console.log("Adding input options to p2");
  if (userInput !== null) {
    xhttp.open("GET", "http://localhost:8080/exercise/exercises/name/"
                + userInput, true);
    xhttp.send();
    var retrievedData = await getXhttpResponse(xhttp);
  } else {
    var retrievedData = null;
  }
  console.log("Exercise data retrieved: " + retrievedData);
  targetLocation.innerHTML = addMultiInput(inputNameArray, functionToPerform,
                                            retrievedData);
}

// For getting a list of exercises ("getExercises")
async function getExercises(xhttp, results){
  xhttp.open("GET", "http://localhost:8080/exercise/exercises", true);
  xhttp.send();
  var resolved = await getXhttpResponse(xhttp);
  console.log("Resolved? :" + resolved);
  results.innerHTML = formatResults(resolved);
}

async function getExerciseType(xhttp, results, inputOptions){
  console.log("Entered getExerciseType");
  xhttp.open("GET", "http://localhost:8080/exercise/exercises/type/"
              + inputOptions, true);
  xhttp.send();
  var resolved = await getXhttpResponse(xhttp);
  //THIS PART IS NOT FUNCTIONING PROPERLY.
  if (resolved && resolved.length !== 0){
    console.log("Resolved is not null: " + resolved);
    results.innerHTML = formatResults(resolved);
  } else {
    console.log("Resolved is null");
    results.innerHTML = errorOccurred("No Exercises of that type!");
  }

}


async function getOneExercise(xhttp, results, userInput){
  console.log("Entered getOneExercie.");
  console.log("Input = " + userInput);
  xhttp.open("GET", "http://localhost:8080/exercise/exercises/name/"
              + userInput, true);
  xhttp.send();
  return resolvedCheck(xhttp);
}

async function postNewExercise(xhttp, newEx) {
  try {
    console.log("postNewExercise called!");
    newEx = JSON.stringify(newEx);
    console.log("Exercise: " + newEx);
    xhttp.open("POST", "http://localhost:8080/exercise/exercises", true);
    xhttp.send(newEx);
    var resolved = await getXhttpResponse(xhttp);
    console.log("Resolved: " + resolved);
    return (true);
  } catch (error){
    console.log(errorOccurred("Error caught in postNewExercise!"));
    return false;
  }
}

async function putExerciseUpdate(xhttp, exUpdate){
  try {
    console.log("putExerciseUpdate called!");
    exUpdate = JSON.stringify(exUpdate);
    console.log("Exercise: " + exUpdate);
    xhttp.open("PUT", "http://localhost:8080/exercise/exercises", true);
    xhttp.send(exUpdate);
    var resolved = await getXhttpResponse(xhttp);
    console.log("Resolved: " + resolved);
    return (true);
  } catch (error){
    console.log(errorOccurred("Error caught in putExerciseUpdate!"));
    return false;
  }
}


async function getDeleteConfirmation(xhttp, resultTarget, confirmationTarget, userInput){
  console.log("Adding confirmation to p2");
  var success = await getOneExercise(xhttp, resultTarget, userInput);
  console.log("Success = " + success);
  if (success === true){
    confirmationTarget.innerHTML = addConfirmation("Really delete?",
                                      "onDeleteConfirmed()", "resetDynamicContent()");
  } else {
    confirmationTarget.innerHTML = errorOccurred("Exercise not found.");
  }

}

async function deleteExercise(xhttp){
  xhttp.open("DELETE", "http://localhost:8080/exercise/exercises/delete", true);
  xhttp.send();
  var success = await resolvedCheck(xhttp);
  if (success == true){
    console.log("Success condition entered in deleteExercise: " + success);
    resetDynamicContent();
    results.innerHTML = "Success!!";
  } else {
    console.log("Failure condition entered in deleteExercise: " + success);
    resetDynamicContent();
    results.innerHTML = errorOccurred("Something went wrong. Please try again.");
  }
}

// this is a fucking GARBAGE method. I am actually angry that I wrote this.
async function resolvedCheck(xhttp){
  var resolved = await getXhttpResponse(xhttp);
  console.log("Resolved: " + resolved);
  try {
    resolved = JSON.parse(resolved);
    console.log("Resolved.status: " + resolved.status);
    if (resolved.status === 500){
      console.log("Resolved status in 500 condition: " + resolved.status);
      results.innerHTML = errorOccurred("Exercise not found!");
      return false;
    } else if (resolved === ""){
      console.log("Resolved is an empty string in resolvedCheck");
      return true;
      } else {
      console.log("Resolved in else condition: " + resolved.status);
      resolved = JSON.stringify(resolved);
      console.log("Resolved after stringify: " + resolved);
      results.innerHTML = formatResults(resolved);
      return true;
    }
  } catch (error){
    return true;
  }
}


async function getNumTypeWorkout(numEx, type, xhttp, results){
  try {
    console.log("getNumTypeWorkout called!")
    xhttp.open("GET", "http://localhost:8080/exercise/workout/type/" + type
                + "/" + numEx, true);
    xhttp.send();
    var resolved = await getXhttpResponse(xhttp);
    results.innerHTML = formatWorkout(resolved);
  } catch (e){
    console.log("Error occurred in getNumTypeWorkout");
  }
}

async function getTypeWorkout(type, xhttp, results){
  try{
    console.log("getTypeWorkout called!");
    xhttp.open("GET", "http://localhost:8080/exercise/workout/type/" + type, true);
    xhttp.send();
    var resolved = await getXhttpResponse(xhttp);
    results.innerHTML = formatWorkout(resolved);
  } catch (e){
    console.log("Error occurred in getTypeWorkout");
  }
}

async function getNumWorkout(numEx, xhttp, results){
  try {
    console.log("getNumWorkout called!");
    xhttp.open("GET", "http://localhost:8080/exercise/workout/number/" + numEx, true);
    xhttp.send();
    var resolved = await getXhttpResponse(xhttp);
    results.innerHTML = formatWorkout(resolved);
  } catch (e){
    console.log("Error occurred in getNumWorkout!")
  }
}

async function getDefaultRandomWorkout(xhttp, results){
  try {
    console.log("getDefaultRandomWorkout called!");
    xhttp.open("GET", "http://localhost:8080/exercise/workout", true);
    xhttp.send();
    var resolved = await getXhttpResponse(xhttp);
    results.innerHTML = formatWorkout(resolved);
  } catch (e){
    console.log("Error occurred in getDefaultRandomWorkout!");
  }
}

async function sendResultsToEmail(xhttp, content, emailInput){
  try {
    console.log("sendResultsToEmail called!");
    console.log(content);
    console.log(emailInput);
    xhttp.open("POST", "http://localhost:8080/exercise/email/" + emailInput, true);
    xhttp.send(content);
    return true;
  } catch (e){
    console.log("Error occurred in sendResultsToEmail!");
    return false;
  }
}
