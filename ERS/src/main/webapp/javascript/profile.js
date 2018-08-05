window.onload = () => {
    updatePage();
}

function updatePage() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
    	if(xhr.readyState === 4 && xhr.status === 200) {
            let currPerson = JSON.parse(xhr.responseText);
            console.log(currPerson);
            let nameTag = document.getElementById("name-tag");
            nameTag.innerHTML = currPerson.name;
            nameTag.default = currPerson.name;
            let usernameTag = document.getElementById("username-tag");
            usernameTag.innerHTML = currPerson.username;
            usernameTag.default = currPerson.username;
            let passwordTag = document.getElementById("password-tag");
            passwordTag.innerHTML = currPerson.password;
            passwordTag.default = currPerson.password;
            let ageTag = document.getElementById("age-tag");
            ageTag.innerHTML = currPerson.age;
            ageTag.default = currPerson.age;
            document.getElementById("position-tag").innerHTML = currPerson.position == 0 ? "Employee" : "Manager";
        }
    }
    xhr.open("get", "../*.employeeDo?check=true", true);
    xhr.send();
}

function updateEmployee() {
    let name = document.getElementById("employee-name").value;
    let username = document.getElementById("employee-username").value;
    let password = document.getElementById("employee-password").value;
    let age = document.getElementById("employee-age").value;

    if(name === "" && username === "" && password === "" && age === "") {
        alert("Please change at least one field to update!");
    } else {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(xhr.readyState === 4 && xhr.status === 200) {
                updatePage();
                alert("Update Successful!");
            }
        }
        let link = `name=${name}&username=${username}&password=${password}&age=${age}`;
        xhr.open('get', '../updateEmployee.employeeDo?' + link, true);
        xhr.send();
    }
}

function passwordWarning() {

    if(document.getElementById("employee-password") === "" ) {
        document.getElementById('password-warning').style.visibility = "hidden";
    } else {
        document.getElementById('password-warning').style.visibility = "visible";
    }
    changeName('employee-password', 'password-tag');
}

function changeName(from, to) {
    let f = document.getElementById(from);
    let t = document.getElementById(to);
    if(f.value === "") {
        t.innerHTML = t.default;
    } else {
        t.innerHTML = f.value;
    }
} 