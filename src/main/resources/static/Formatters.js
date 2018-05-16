// Functions that handle formatting of dynamic html elements in
// main.js and SelectorFunctions.js

// Formates a returned object from the server
// Expected to always be a string parseable into a json obj or array thereof
function formatResults(results){
  try {
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
  } catch (error){
    return "Error!";
  }

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

// To add dynamic page content for multi input
// retrievedData may be NULL!!
function addMultiInput(inputNameArray, functionToPerform, retrievedData){
  console.log("Creating html multi-input string.");

  //Initialize array to check for number properties
  var numberTypes = ["weight", "reps", "sets"];

  //Parse the retrievedData if any
  if (retrievedData !== null){
    var parsedData = JSON.parse(retrievedData);
  }
  // Initialize the header
  var returnString = "<form onsubmit=\"" + functionToPerform
    + "(); return false;\"><br>";

  // Iterate through array and add input names
  for (var i = 0; i < inputNameArray.length; i++){

    // Determine Type of input
    var inputType = "";
    if (numberTypes.indexOf(inputNameArray[i]) === -1){
      inputType = "text";
    } else {
      inputType = "number";
    }

    // Determine if input has earlier value
    var value = "";
    var attName = inputNameArray[i];
    if (retrievedData !== null && parsedData[attName]){
      console.log("Value found!");
      value = parsedData[attName];
    } else {
      console.log("Value not found!");
      if (inputType === "number"){
        value = 0;
      } else {
        value = inputNameArray[i];
      }
    }

    console.log("Value = " + value);

    returnString += "<label for=\"" + attName + "\">" + attName + "</label>"
      +"<input type=\'" + inputType + "\'"
      + "id=\'" + attName + "\' value=\'"
      + value + "\'><br>"
  }

  // Add footer
  returnString += "<input type=\'submit\' value=\'Submit\'"
    + "id=\'submit2\'></form>";

  // Return finished string
  console.log("Return string for multiInput: " + returnString);
  return returnString;

}

function addConfirmation(inputMessage, confirmFunction, cancelFunction){
  var returnString = "<br>" + inputMessage + "<br><button onclick=\""
                    + confirmFunction +"\">Confirm</button><button onclick=\""
                    + cancelFunction + "\">Cancel</button>";
  return returnString;
}

function errorOccurred(errorMessage){
  return "<strong> UH OH!</strong><br>" + errorMessage;
}

function formatWorkout(results){
  try {
    console.log("formatWorkout called!");
    var returnString = "";
    var parsedResults = JSON.parse(results);
    var exerciseString = JSON.stringify(parsedResults.exercises);
    if (exerciseString === undefined){
      return (errorOccurred("Something went wrong. <br>Either there aren't enough "
                + "exercises of that type, or the type is misspelled."
                + " <br>Try again!"));
    }
    return formatResults(exerciseString);
  } catch (e){
    console.log("Error in formatWorkout!");
  }
}
