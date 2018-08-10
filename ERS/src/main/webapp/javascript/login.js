window.onload = () => {
	document.getElementById("login-form").onsubmit = (event) => {
		checkLogin(event);
	}
}
function checkLogin(e) {
    console.log(e);
    console.log("i changed");
    //console.log("checkLogin");
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
                        document.getElementById('hello-card').innerHTML = "Hello, " + currPerson.name;
            	        if(currPerson.access > 0) {
                            document.getElementById("manager-profile").style.display = "inline";
                            document.getElementById("manager-viewAll").style.display = "inline";
            	        	document.getElementById("employee-profile").style.display = "none";
            	        } else {
                            document.getElementById("manager-profile").style.display = "none";
                            document.getElementById("manager-viewAll").style.display = "none";
            	        	document.getElementById("employee-profile").style.display = "inline";
            	        }
            	    }
            	}
                let date = JSON.stringify(new Date());
            	xhr.open('get', '../*.employeeDo?check=true&date='+date, true);
            	xhr.send();
            }
        }
    }
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    xhr.open("get", `../login.employeeDo?username=${username}&password=${password}`, true);
    xhr.send();
}