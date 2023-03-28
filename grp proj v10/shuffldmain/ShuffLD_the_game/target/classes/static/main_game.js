
// /////////////////



// //////////////////////////////////////////////////////// js not working so this is the orignail code 
// var row = 0;
// var column = 0;
// var solvedWords;
// var gameOver = false;
// let words;
// var checkHowManyBoxSelected = 0;
// var moves = 15;

// var testing;

// var bootGameOrNot;

// var playerFinishedTheGame = false;

// var ipAddress;

// var sendUsername;
// var sendPassword;
// //////////////////gib function
// function grabSwaps()
// {
//     fetch("/swaps")
//         .then((response) => response.json())
//         .then ((swaps) => {
//             console.log(swaps)
//             document.getElementById("SwapCount").innerText = swaps;
//             document.getElementById("SwapCount2").innerText = swaps;


//         });
// }

// function winGame()
// {
//   remain_Time();
//   document.getElementById("win").style.display = "block";
//   document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
// }





// //////////////////////////
// const form = document.getElementById('login-form');


// document.addEventListener('DOMContentLoaded', function () {
//     const form = document.getElementById('login-form');

//     form.addEventListener('submit', function (event) {
//         event.preventDefault(); // Prevent form from submitting

//         const username = document.getElementById('username').value;
//         const password = document.getElementById('password').value;

//         sendPassword = password;
//         sendUsername = username;

//         console.log(sendUsername);
//         console.log(sendPassword);


//         sendUsernameToController(sendUsername + "^" + sendPassword);

//     });
// });



// function end_game2() {
//   end.style.display = "block";
//   document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
//   document.getElementById("solvedboard").outerHTML = '<textarea id="solvedboard" rows="10" cols="50"></textarea>';
//   document.querySelector(".end-content p").innerHTML = "You win!!!! &#x1F389;"
// }

// function drawSolvedBox() {
//     //drawing 25 boxes
//     fetch("/solvedWords") //word from e
//         .then((response) => response.json())
//         .then((solvedWords) => {
//             data = solvedWords

//             for (let i = 0; i < 5; i++) {
//                 for (let j = 0; j < 5; j++) {
//                     let solvedbox = document.createElement("button");
//                     //span id going to be 0-0,0-1 etc
//                     solvedbox.id = i.toString() + "-" + j.toString();
//                     solvedbox.classList.add("solvedbox");

//                     // Add a class to the button
//                     solvedbox.classList.add("green-button");
//                     console.log(data);
//                     solvedbox.innerText = data[i][j]

//                     document.getElementById("solvedboard").appendChild(solvedbox);

//                 }
//             }


//         });

// }


// async function sendAcheivmentDetailsToController(streak) {
//   try {

//       const response = await fetch('/accountController/updatePlayerAchievement', {
//           method: 'POST',
//           headers: {
//               'Content-Type': 'application/json'
//           },
//           body: JSON.stringify({ streak })
//       });

//       if (response.ok) {
//           console.log("Successfully sent streak to Spring Boot controller");
//       } else {
//           console.log("Error sending streak to Spring Boot controller");
//       }
//   } catch (error) {
//       console.error(error);
//   }

//   getNewData();
// }



// function finalCheckOnLetterForAcheivement() { 
//   var totalWordGuessed = 0;
//   var ifThePlayerWonToday = 0; 
//   for (let i = 0; i < 5; i++) {
//     var count = 0;
//       for (let j = 0; j < 5; j++) {
//           let box = document.getElementById(i.toString() + "-" + j.toString());
//           if (box.innerText.toLowerCase() == solvedWords[i][j]) {
//             count ++;
//           }
//       }
//       if(count == 5 ){
//         console.log("that is a correct word: " + i)
//         totalWordGuessed ++;
//       }
//   }
//   if(totalWordGuessed == 5){
//     ifThePlayerWonToday = 1;
//     //end_game2();
//   }
  

//   sendAcheivmentDetailsToController(ifThePlayerWonToday +"^"+totalWordGuessed +"^"+ ultimateUsername);
  
// }


// let firstSelectionBox = null;
// //swap function for boxes 
// //when move is 0 display end game
// function swap(event) {
//     if (moves == 0) {
//         finalCheckOnLetterForAcheivement();
//         console.log("Sad to see");
//         remain_Time();
//         end_game();
//         grabSwaps();
//         drawSolvedBox();
//         return;
//     }

//     if (firstSelectionBox == null) {
//         firstSelectionBox = event.target;
//         firstSelectionBox.style.fontSize = '4.0em';
//     } else {
//         if (firstSelectionBox.id.charAt(2) === event.target.id.charAt(2)) {
//             if (firstSelectionBox === event.target) {
//                 // The user selected the same box twice, do nothing
//                 firstSelectionBox.style.fontSize = '2.5em';
//                 firstSelectionBox = null;
//                 return;
//             }
            
//             temp = event.target.innerText;
//             moves--;
//             event.target.innerText = firstSelectionBox.innerText;
//             firstSelectionBox.innerText = temp;
//             firstSelectionBox.style.fontSize = '2.5em';
//             firstSelectionBox = null;
//             checkCorrectLetters();
//             document.getElementById('movesLeft').innerHTML = "Moves Left: " + moves;
//             console.log(moves);
//             finalCheckOnLetterForAcheivement();
//         }
//     }
// }



// function checkCorrectLetters() {
//     let win = true;
//     for (let i = 0; i < 5; i++) {
//         for (let j = 0; j < 5; j++) {
//             let box = document.getElementById(i.toString() + "-" + j.toString());
//             if (box.innerText.toLowerCase() == solvedWords[i][j]) {
//                 box.classList.add("right");
//                 box.disabled = true;
//             }
//             else {
//                 box.classList.remove("right");
//                 win = false;
//             }
//         }
//     }
//     if (win) {
//       winGame();
//       grabSwaps();
//     }
// }

// function drawBox() {
//     //drawing 25 boxes
//     fetch("/words") //word from backend
//         .then((response) => response.json())
//         .then((shuffledWords) => {
//             words = shuffledWords

//             for (let i = 0; i < 5; i++) {
//                 for (let j = 0; j < 5; j++) {
//                     let box = document.createElement("button");
//                     //span id going to be 0-0,0-1 etc
//                     box.id = i.toString() + "-" + j.toString();
//                     box.classList.add("box");
//                     console.log(words);
//                     box.innerText = words[i][j]
//                     box.onclick = swap;
//                     document.getElementById("board").appendChild(box);

//                 }
//             }

//             fetch("/solvedWords")
//                 .then((response) => response.json())
//                 .then((data) => {
//                     solvedWords = data
//                     checkCorrectLetters();
//                 })
//         });

// } 





// // Get the End by declaring
// var end = document.getElementById("end-up1");

// // Get the button that opens the pop
// //var btn2 = document.getElementById("open-id2");


// // Get the Pop by declaring
// var pop = document.getElementById("Pop-up1");
// // Get the button that opens the pop
// var btn = document.getElementById("open-id");
// // Get the span element that closes the pops up
// var close = document.getElementsByClassName("close")[0];


// var ultimateUsername;
// var storeForRegUsername;




// //Click the button and open a block(content
// btn.onclick = function() {


//   pop.style.display = "block";
//   document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
// }



// // Get the Pop by declaring
// var pop3 = document.getElementById("Pop-up3");



// var btn3 = document.getElementById("open-id3");





// // When the user clicks on x then close the block
// close.onclick = function() {

//   if ( pop.style.display = "block") {
//     pop.style.display = "none"
// }



 
//   document.body.style.backgroundColor = "rgb(222,219,219)";
// }


// function toggleSearch() {
//   var searchContent = document.querySelector('.search-content');
//   searchContent.style.display = searchContent.style.display === 'none' ? 'block' : 'none';
// }


// function show_statics_content() {
//   var staticscontent = document.querySelector('.statics-content');
//   staticscontent.style.display = staticscontent.style.display === 'none' ? 'block' : 'none';
  
// }






// /////
//       function show() {
//           document.getElementById('menu').classList.toggle('active');
     

//         }
//         function Time() {
//         var date = new Date();
//         document.getElementById("time").innerHTML = date.toLocaleTimeString();

//       }



//       function remain_Time() {
//   // Get the current time in milliseconds
//   var current_time = new Date().getTime();

//   // Calculate the reset time as 00:00 midnight
//   var reset_time = new Date();
//   reset_time.setHours(0, 0, 0, 0);
//   reset_time.setDate(reset_time.getDate() + 1);

//   // Calculate the time remaining in hours, minutes, and seconds
//   var time_remaining = (reset_time.getTime() - current_time) / 1000;
//   var hours_remaining = Math.floor(time_remaining / 3600);
//   var minutes_remaining = Math.floor((time_remaining % 3600) / 60);
//   var seconds_remaining = Math.floor(time_remaining % 60);

//   // Display the time remaining
//   document.getElementById("times").innerHTML ="comeback after " + hours_remaining + " hours " + minutes_remaining + " minutes ";
//   document.getElementById("timeRemaining").innerHTML = "Next puzzle in: " + hours_remaining + " hours " + minutes_remaining +" minutes";
// }





// // set limitaition for textboxes entry
// function Limitation_input(input) {

// user_input = input.target.value;
     
//       if (user_input.includes('^')) {
   
//         input.target.value = user_input.replace('^', '');
//       } 



//     }


// // When the user clicks on x then close the block
// close.onclick = function() {

//   if ( pop.style.display = "block") {
//     pop.style.display = "none"
// }

//   document.body.style.backgroundColor = "rgb(222,219,219)";
// }







    
//  function end_game() {


//       end.style.display = "block";
//       document.body.style.backgroundColor = "rgba(0,0,0,0.7)";
//       endScreenUpdateComplete(ultimateUsername);
//       }
      




      
//       setInterval(Time, 1000);
// ////////////////////////////////////////////////////////////////




// // directory api allows user to search meaning of word. 
// // it is fetching api by its url
// function search() {
//   const searchTerm = document.getElementById('word').value;
//   const api_Url = `https://api.dictionaryapi.dev/api/v2/entries/en/${searchTerm}`;

//   fetch(api_Url)
//     .then(response => response.json())
//     .then(data => {
//       // Extract the necessary information from the response data
//       const meaning = data[0].meanings[0];
//       const partOfSpeech = meaning.partOfSpeech;
//       const definition = meaning.definitions[0].definition;

//       // Generate the output HTML
//       const outputHtml = `
//         <div class="definition">
//           <p>${partOfSpeech}</p>
//           <p>${definition}</p>
//         </div>
//       `;

//       // Set the output HTML to the innerHTML property of a container element
//       const outputContainer = document.getElementById('output');
//       outputContainer.innerHTML = outputHtml;
//     })
    
// }


// ///////////////////////////////////////////


// var isRegisterForm_appear = false;

// function showRegisterForm() {
//   // Create the register form element
//   var registerForm = document.createElement("form");
 
//   registerForm.id = "register-form";
//   registerForm.innerHTML = "<h2>Register</h2><label>Name: <input type='text' name='username' id='register-username'></label><br> <label>Password: <input type='password' name='password' id='register-password'></label><br> <label>Email: <input type='email' name='email' id='register-email'></label><br><button type='button' onclick='registerFormSubmit()'>Register</button>";

//   // Create a close button element
//   var closeButton = document.createElement("button");
//   closeButton.id = "close-button";
//   closeButton.innerHTML = "Back";

//   // Append the close button to the register form element
//   registerForm.appendChild(closeButton);

//   // Add a click event listener to the close button to remove the register form element
//   closeButton.addEventListener("click", function() {
  
//     document.body.removeChild(registerForm);

//     isRegisterForm_appear = false;
//     console.log(isRegisterForm_appear);   
//   });

//   // Center the register form element horizontally and vertically
//   registerForm.style.position = "absolute";
//   registerForm.style.top = "40%";
//   registerForm.style.left = "82%";
//   registerForm.style.transform = "translate(-50%, -50%)";

//   // Add the register form to the page

  
//   if (!isRegisterForm_appear) {
//   document.body.appendChild(registerForm);
//   isRegisterForm_appear = true;
 
//   console.log(isRegisterForm_appear);


//   } else {
  

//     alert("Only click once");
   

//   }

 

// }








// //////////////////////////////////////////////////////////timmy part


// async function sendUsernameToController(sendUsername) {
//   try {
//       const response = await fetch('/accountController/saveUsername', {
//           method: 'POST',
//           headers: {
//               'Content-Type': 'application/json'
//           },
//           body: JSON.stringify({ sendUsername })
//       });

//       if (response.ok) {
//           console.log("Successfully sent username to Spring Boot controller");
//       } else {
//           console.log("Error sending username to Spring Boot controller");
//       }
//   } catch (error) {
//       console.error(error);
//   }

//   getComplete();
// }

// var alwaysTrue = true;
// async function endScreenUpdateComplete(g) {
//   try {
//     console.log(g);
//       const response = await fetch('/accountController/completedTheGame', {
//           method: 'POST',
//           headers: {
//               'Content-Type': 'application/json'
//           },
//           body: JSON.stringify({g})
//       });

//       if (response.ok) {
//           console.log("Successfully sent true to Spring Boot controller");
//       } else {
//           console.log("Error sending true to Spring Boot controller");
//       }
//   } catch (error) {
//       console.error(error);
//   }

// }


// function getComplete() {

//   fetch("/accountController/complete") //word from e
//       .then((response) => response.json())
//       .then((complete) => {
//           data = complete
//           //bootGameOrNot = data;
//           console.log(data);
//           if(data == 0){
//             alert("wrong password");
//             console.log("wrong passwrod");
//           }
//           else if (data == 1){
//             ultimateUsername = sendUsername; 
//             document.getElementById("login-form").remove();
//             end_game();
//             console.log("the user have completed the game for today");
            
            
//             console.log("1 ultimate: " + ultimateUsername);
//           }
//           else if(data == 2){
//             ultimateUsername = sendUsername; 
//             document.getElementById("login-form").remove();
//             drawBox();
//             console.log("the user have not played today");
//             console.log("2 ultimate: " + ultimateUsername);
//           }
//           else{
//             alert("you are not registered, please register before you play!");
//             console.log("no match")
//           }

          
//       });


// }


// function getRegistration() {

//   fetch("/accountController/registration") //word from e
//       .then((response) => response.json())
//       .then((complete) => {
//           data = complete
//           //bootGameOrNot = data;
//           console.log(data);
//           if(data == 0){
//             alert("Congradulations! You have registered! Enjoy the game!!!");
//             ultimateUsername = storeForRegUsername;
//             drawBox();
//             console.log("ultimate: " + ultimateUsername);
//             document.getElementById("register-form").remove();
//             document.getElementById("login-form").remove();

//           }
//           else{
//             alert("username already exist");
//           }

//       });


// }



// function getNewData() {
//   var depender = 0;
//   var dsiplayPlayed="";
//   var displayWin = "";
//   var displayWordGuessed ="";

//   fetch("/accountController/updateFrontEndAchievement") //word from e
//       .then((response) => response.text())
//       .then((data) => {
//         var split = [];
//           console.log("these data are going into achievement: " + data);

//           split = data.split("");
//           for(var i = 0; i < split.length; i++){

//             if (depender == 0 && split[i] !== "^") {
//               dsiplayPlayed += split[i];
//             } 
//             else if (depender == 1 && split[i] !== "^") {
//               displayWin += split[i];
//             } 
//             else if (depender == 2 && split[i] !== "^") {
//               displayWordGuessed += split[i];
//             } 
//             else if(split[i] == "^"){
//               depender++;
//             }
//           }
//           console.log(dsiplayPlayed);
//           console.log(displayWin);
//           console.log(displayWordGuessed);
//           document.getElementById("displayWordGuessed").textContent = displayWordGuessed;
//           document.getElementById("displayWin").textContent = displayWin;
//           document.getElementById("dsiplayPlayed").textContent = dsiplayPlayed;
//       });
// }


// async function sendRegUsernameToController(sendRegUsername) {
//   try {

//     console.log("storing register name: " + storeForRegUsername);
//       const response = await fetch('/accountController/saveRegUsername', {
//           method: 'POST',
//           headers: {
//               'Content-Type': 'application/json'
//           },
//           body: JSON.stringify({ sendRegUsername })
//       });

//       if (response.ok) {
//           console.log("Successfully sent regusername to Spring Boot controller");
//       } else {
//           console.log("Error sending regusername to Spring Boot controller");
//       }
//   } catch (error) {
//       console.error(error);
//   }

//   getRegistration();
// }




// function registerFormSubmit(){
//   console.log("work for register button");


//   const regUsername = document.getElementById('register-username').value;
//   const regPassword = document.getElementById('register-password').value;
//   const regEmail =  document.getElementById('register-email').value;

//   console.log(regUsername);
//   console.log(regPassword);
//   console.log(regEmail);
//   storeForRegUsername = regUsername;
//   console.log("the store: " + storeForRegUsername);
//   sendRegUsernameToController(regUsername + "^" + regPassword + "^" + regEmail);
// }