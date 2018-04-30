var select = document.querySelector("select");
var paragraph = document.querySelector("p1");
var paragraph2 = document.querySelector("p2");

select.addEventListener("change", getResponse);

var xhttp = new XMLHttpRequest();
xhttp.onreadystatechange = function(){
  if (this.readyState == 4 && this.status == 200){
    formatReturn(xhttp.responseText);
    console.log("Status change!");
  }
}

function getResponse(){
  var choice = select.value;

  if (choice === "getExercises"){
    xhttp.open("GET", "http://localhost:8080/exercise/exercises/id/1", true);
    xhttp.send();
    console.log("Done with getResponse!");

  } else if (choice === "getWorkout"){
    paragraph.textContent = "This is where you'd have a workout."
    } else {
    paragraph.textContent = "This is where text will go."
    }
  }

  function formatReturn(response){
    console.log("Begun formatReturn!");
    console.log("1: " + typeof(xhttp.responseText));
    console.log("2: " + typeof(response));
    console.log("3: " + xhttp.responseText);
    console.log("4: " + response);
    console.log("5:" + JSON.parse(xhttp.responseText));
    console.log("6: "+ JSON.parse(response));
    response = JSON.parse(response);
    console.log("7: " + response.name);
    console.log("8: " + response.type);
    var str = "";
    console.log("Okay, gonna try and access data:");
    str += "Name: " + response.name + "\nType: " + response.type + "\n";
    document.getElementById("results").innerHTML = str;
}
