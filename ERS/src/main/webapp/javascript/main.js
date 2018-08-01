var xhr = new XMLHttpRequest();
//alert("hello");
xhr.onreadystatechange = function() {
    if(xhr.readyState === 4 && xhr.status === 200) {
        //alert(xhr.responseText);
    	let currPerson = JSON.parse(xhr.responseText);
        document.getElementById("welcome-card").innerHTML = "Welcome, " + currPerson.name;
    }
}
xhr.open('get', '../*.employeeDo?check=true', true);
xhr.send();