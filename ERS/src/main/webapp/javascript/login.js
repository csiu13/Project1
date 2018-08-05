window.onload = () => {
	document.getElementById("login-form").onsubmit = (event) => {
		checkLogin(event);
	}
}
function checkLogin(e) {
    console.log("checkLogin");
    e.preventDefault();
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            if(xhr.responseText === 'fail') {
                let show = xhr.responseText === 'fail' ? 'inline' : 'none';
                //console.log(xhr.responseText);
                //console.log(show);
                document.getElementById("login-fail").style.display = show;
            } else {
                document.getElementById("body").innerHTML = xhr.responseText;
                xhr = new XMLHttpRequest();
            	xhr.onreadystatechange = function() {
            	    if(xhr.readyState === 4 && xhr.status === 200) {
            	    	let currPerson = JSON.parse(xhr.responseText);
            	        document.getElementById("welcome-card").innerHTML = "Welcome, " + currPerson.name;
            	        if(currPerson.access > 0) {
            	        	document.getElementById("manager-profile").style.display = "inline";
            	        	document.getElementById("employee-profile").style.display = "none";
            	        } else {
            	        	document.getElementById("manager-profile").style.display = "none";
            	        	document.getElementById("employee-profile").style.display = "inline";
            	        }
            	    }
            	}
            	xhr.open('get', '../*.employeeDo?check=true', true);
            	xhr.send();
            }
        }
    }
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    xhr.open("get", `../login.employeeDo?username=${username}&password=${password}`, true);
    xhr.send();
}