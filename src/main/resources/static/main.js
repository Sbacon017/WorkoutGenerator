
// Prepare variables

// Initial option selector
var selector = document.querySelector("select");

// Paragraphs with dynamically created content
var inputOptions = document.getElementById("inputOptions");
var moreInputOptions = document.getElementById("moreInputOptions");
var results = document.getElementById("results");

// The XMLHttpRequest object that will handle all the back and forth
// with the back end
var xhttp = new XMLHttpRequest();



// Add event listener to selector
selector.addEventListener("change", onActionSelect);


// Function that handles initial action selection
function onActionSelect(){
  var choice = selector.value;

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
      /*console.log("Adding event listener for getOneExercise");
      submit.addEventListener("submit", onSubmitForExercise);
      getOneExercise(xhttp, results, name);*/
      break;

    case "updateExercise" :
      break;

    case "getWorkout" :
      break;
  }

  console.log("Finished onActionSelect");

}

function onSubmitForExercise(){
  console.log("onSubmitForExercise called!");
  var userInput = document.getElementById('input1').value;
  getOneExercise(xhttp, results, userInput);
}

function onSubmitForExerciseType(){
  console.log("onSubmitForExerciseType called!");
  var userInput = document.getElementById('input1').value;
  getExerciseType(xhttp, results, userInput);
}
