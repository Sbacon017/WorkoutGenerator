
// Prepare variables

// Initial option selector
var selector = document.querySelector("select");

// Paragraphs with dynamically created content
var inputOptions = document.getElementById("inputOptions");
var moreInputOptions = document.getElementById("moreInputOptions");
var results = document.getElementById("results");
var email = document.getElementById("email");
var error = document.getElementById("error");

// The XMLHttpRequest object that will handle all the back and forth
// with the back end
var xhttp = new XMLHttpRequest();

// The list of attributes that an object (exercise) can have
var attributeNameArray = ["name", "type", "sets",
  "reps", "weight", "notes"];

// Add event listener to selector
selector.addEventListener("change", onActionSelect);


// Function that handles initial action selection
function onActionSelect(){
  var choice = selector.value;

  resetDynamicContent();

  switch (choice){

    case "getExercises" :
      console.log("In getEx in switch");
      getExercises(xhttp, results);
      console.log("Exited getExercises");
      break;

    case "getExByType" :
      console.log("In getExByType");
      getInput(inputOptions, "Type", "onSubmitForExerciseType");
      var submit = document.getElementById("submit1");
      console.log("Submit = " + submit);
      break;


    case "getOneExercise" :
      console.log("In getOneExercise in switch");
      getInput(inputOptions, "Name", "onSubmitForExercise");
      var submit = document.getElementById("submit1");
      console.log('Submit = ' + submit);
      break;

    case "createExercise" :
      getMultiInput(moreInputOptions, attributeNameArray, "onCreateSubmit", null);
      break;

    case "updateExercise" :
      getInput(inputOptions, "Name", "onFindForUpdate");
      break;

    case "deleteExercise" :
      getInput(inputOptions, "Name", "onFindForDelete");
      break;

    case "getWorkout" :
      getMultiInput(inputOptions, ["Type", "Number Of Exercises"],
                    "onWorkoutSubmit", null);
      break;
  }

  console.log("Finished onActionSelect");

}

function onSubmitForExercise(){
  console.log("onSubmitForExercise called!");
  var userInput = document.getElementById('Name').value;
  getOneExercise(xhttp, results, userInput);
}

function onSubmitForExerciseType(){
  console.log("onSubmitForExerciseType called!");
  var userInput = document.getElementById('Type').value;
  getExerciseType(xhttp, results, userInput);
}

async function onSubmitUpdate(){
  console.log("onSubmitUpdate called!");
  var exercise = {};
  var numberTypes = ["weight", "reps", "sets"];
  for (var i = 0; i < attributeNameArray.length; i++){
    console.log("Iterating through exercise creation..." + i);
    var attribute = attributeNameArray[i];
    var value = document.getElementById(attribute).value;
    exercise[attribute] = value;
    console.log(attributeNameArray[i] + exercise.attribute);
  }
  console.log("Sending exercie update to server...");
  var success = await putExerciseUpdate(xhttp, exercise);
  if (success === true){
    results.innerHTML = "Success!";
  } else {
    results.innerHTML = errorOccurred("Something went wrong...");
  }
}


function onFindForUpdate(){
  console.log("onFindForUpdate called!");
  var userInput = document.getElementById('input1').value;
  getMultiInput(moreInputOptions, attributeNameArray, "onSubmitUpdate", userInput);
}

async function onCreateSubmit(){
  console.log("onCreateSubmit called!");
  var exercise = {};
  for (var i = 0; i < attributeNameArray.length; i++){
    console.log("Iterating through exercise creation..." + i);
    var attribute = attributeNameArray[i];
    var value = document.getElementById(attribute).value;
    exercise[attribute] = value;
    console.log(attributeNameArray[i] + exercise.attribute);
  }
  var success = await postNewExercise(xhttp, exercise);
  if (success === true){
    results.innerHTML = "Success!";
  } else {
    results.innerHTML = errorOccurred("Something went wrong...");
  }
}

function onFindForDelete(){
  getDeleteConfirmation(xhttp, results, moreInputOptions,
            document.getElementById("input1").value);
}

function onDeleteConfirmed(){
  deleteExercise(xhttp);
}

function onWorkoutSubmit(){
  console.log("onWorkoutSubmit called!");
  var typeVal = document.getElementById("Type").value;
  var numExVal = document.getElementById("Number Of Exercises").value;
  if (typeVal !== null && typeVal !== "Type"){
    var type = typeVal;
  }
  if (numExVal !== null && numExVal !== "Number Of Exercises"){
    var numEx = numExVal;
  }
  if (type && numEx){
    getNumTypeWorkout(numEx, type, xhttp, results);
  } else if (type){
    getTypeWorkout(type, xhttp, results);
  } else if (numEx){
    getNumWorkout(numEx, xhttp, results);
  } else {
    getDefaultRandomWorkout(xhttp, results);
  }
  getInput(email, "Email", "onSubmitEmail");
}

function onSubmitEmail(){
  console.log("OnSubmitEmail called!");
  content = results.innerHTML;
  emailInput = document.getElementById("Email").value;
  console.log(content + "\n" + emailInput);
  if(sendResultsToEmail(xhttp, content, emailInput)){
    resetDynamicContent();
    results.innerHTML = "Success!";
  } else {
    error.innerHTML = errorOccurred("Something broke... Try again?");
  }
}

function resetDynamicContent(){
  console.log("Resetting dynamic page content.");
  inputOptions.innerHTML = "";
  moreInputOptions.innerHTML = "";
  results.innerHTML = "";
  email.innerHTML = "";
  error.innerHTML = "";
}
