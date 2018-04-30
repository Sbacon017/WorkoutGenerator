// Functions that handle formatting of dynamic html elements in
// main.js and SelectorFunctions.js

// Formates a returned object from the server
// Expected to always be a string parseable into a json obj or array thereof
function formatResults(results){
  console.log("Passed to formatResults:" + results);
  var returnString = "";
  returnString = addHeader(returnString);
  console.log("Return string with header: " + returnString);
  parsedResults = JSON.parse(results);
  if (parsedResults.constructor === Array){
    console.log("Array detected in formatResults.");
    for (var i = 0; i < parsedResults.length; i++){
      returnString = addExercise(returnString, parsedResults[i]);
    }
  } else {
    console.log("Array not detected in formatResults");
    returnString = addExercise(returnString, parsedResults);
  }

  returnString += "</table>"
  console.log("Return string post processing: " + returnString);
  return returnString;

}


// Adds an exercise header to a string
function addHeader(needHeader){
  needHeader += "<table style=\"width:100%\">" +
        "<tr><th>NAME</th><th>TYPE</th><th>SETS</th>"
          + "<th>REPS</th><th>WEIGHT</th><th>NOTES</th></tr>";
  return needHeader;
}

// Adds a single exercises data to a string
function addExercise(returnString, parsedResults){
  returnString += "<tr><th>" + parsedResults.name + "</th>"
                  + "<th>" +parsedResults.type + "</th>"
                  + "<th>" +parsedResults.sets + "</th>"
                  + "<th>" +parsedResults.reps + "</th>"
                  + "<th>" +parsedResults.weight + "</th>"
                  + "<th>" +parsedResults.notes + "</th></tr>";
  return returnString;
}

function addInput(inputName, functionToPerform){
  console.log("Creating html input string.");
  var returnString = "<form onsubmit=\""
    + functionToPerform + "(); return false;\">"
    + "<br>Enter " + inputName + ":<br>"
    + "<input type=\'text\' id='input1'><br>"
    + "<input type=\'submit\' value=\'Submit\' id=\'submit1\'>"
    + "</form>";
  return returnString;

}

function errorOccurred(errorMessage){
  return "<strong> UH OH!</strong><br>" + errorMessage;
}
