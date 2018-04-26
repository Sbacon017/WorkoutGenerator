var myButton = document.querySelector('button');
var myHeading = document.querySelector('h1')

function setUserName(){
  var myName = prompt("Please enter your name:");
  localStorage.setItem('name', myName);
  myHeading.textContent = 'Mozilla is cool, ' + myName;
}

if (!localStorage.getItem('name')){
  setUserName();
} else {
  var localName = localStorage.getItem('name');
  myHeading.textContent = 'Welcome back, ' + localName;
}

myButton.onclick = function(){
  setUserName();
}
