// Functions called upon initial action selection in main.js


function getXhttpResponse(xhttp){
  return new Promise(resolve => {
    setTimeout(() => {
      resolve(xhttp.responseText);
    }, 1000);
  });
}

function getInput(inputOptions, inputName, functionToPerform){
  console.log("Adding input options to p1");
  inputOptions.innerHTML = addInput(inputName, functionToPerform);
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


async function getOneExercise(xhttp, results, inputOptions){
  console.log("Entered getOneExercie.");
  console.log("Input = " + inputOptions);
  xhttp.open("GET", "http://localhost:8080/exercise/exercises/name/"
              + inputOptions, true);
  xhttp.send();
  var resolved = await getXhttpResponse(xhttp);
  console.log("Resolved: " + resolved);
  if (resolved){
    console.log("Resolved is not null");
    results.innerHTML = formatResults(resolved);
  } else {
      console.log("Resolved is null");
      results.innerHTML = errorOccurred("Exercise not found!");
  }
}
