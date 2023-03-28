var users = [];

const memberDiv = document.querySelector('.memberDiv');
const addIcon = document.querySelector('.addIcon');

window.onload = function () {


    getClientIPAddr();
    

}


function addUserIcons() {
    users.reverse();
    users.forEach((curElem) => {
      memberDiv.insertAdjacentHTML('afterbegin', `
        <button class="btn"><span>${curElem}</span></button>
      `);
    });
  }

// memberDiv.addEventListener('click', () => {

//     //window.location.href = 'shuffldmain\ShuffLD_the_game\src\main\resources\templates\mainGame.html';
// })

addIcon.addEventListener('click', () => {
    let userName = prompt('please enter your name');

    if (userName != null && !users.includes(userName)) {
        users.push(userName);
        console.log(users);
        sendNewUsernameToController(userName);
        memberDiv.insertAdjacentHTML('afterbegin', `
        <button class="btn"><span>${userName}</span></button>
        `);
    } else {
        alert('username already exist');
    }

})


async function getClientIPAddr() {
    const response = await fetch('http://api.ipify.org/?format=json');
    const data = await response.json();
    ipAddress = data.ip;
    console.log(ipAddress);
    try{sendIpAddressToController(ipAddress)}
    catch(err){
        console.log(err);
    }
}

async function sendIpAddressToController(ipAddress) {
    try {
        const response = await fetch('/selectController/findPlayer', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ ipAddress })
        });

        if (response.ok) {
            console.log("Successfully sent IP address to Spring Boot controller");
            console.log("yes");
        } else {
            console.log("Error sending IP address to Spring Boot controller");
        }
    } catch (error) {
        console.error(error);
    }
    getNameList();
}


async function sendNewUsernameToController(userName) {
    try {
        const response = await fetch('/selectController/createNewUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({userName})
        });

        if (response.ok) {
            console.log("Successfully sent new created username to Spring Boot controller");
            console.log("yes");
        } else {
            console.log("Error sending IP address to Spring Boot controller");
        }
    } catch (error) {
        console.error(error);
    }
}


function getNameList() {

    fetch("/selectController/nameList") //word from e
        .then((response) => response.text())
        .then((nameList) => {
            data = nameList
            const words = data.split(",");
            for (let i = 0; i < words.length; i++) {
                if (!words[i] == "") {
                    users.push(words[i]);
                }
            }
            console.log(users);
            addUserIcons();
        });
}