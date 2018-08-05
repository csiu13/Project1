window.onload = () => {
	console.log("hello");
	let xhr = new XMLHttpRequest();
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

// function changePage(name) {
// 	clearMain();
// 	switch (name) {
// 		case "employeeList":
// 			viewAllEmployees();
// 		case "viewProfile":
// 			viewProfile();
// 	}
// //	$('#main').load('http://localhost:8080/ERS/html/EmployeeList.html', function() {
// //		viewAllEmployees();
// //	});
// 	//console.log("wat");
// 	//document.getElementById("main").innerHTML=`<object width="1500" height="1700" type="text/html" data=${name}></object>`;
// 	//viewAllEmployees();
// }

function clearMain() {
	let main = document.getElementById("main");
	while(main.firstChild) {
		main.removeChild(main.firstChild);
	}
	
}

// function viewAllEmployees() {
// 	let xhr = new XMLHttpRequest();
// 	xhr.onreadystatechange = function() {
// 		if(xhr.readyState === 4 && xhr.status === 200) {
// 			//let table = document.getElementById("employee-table");
// 			let table = document.createElement("table");
// 			table.setAttribute("class","table table-bordered table-striped");
// 			table.setAttribute("id", "employee-table");
// 			let thead = document.createElement('tr');
// 			let tempRow = document.createElement('th');
// 			tempRow.innerHTML = 'Employee ID';
// 			thead.appendChild(tempRow);
// 			tempRow = document.createElement('th');
// 			tempRow.innerHTML = 'Name';
// 			thead.appendChild(tempRow);
// 			tempRow = document.createElement('th');
// 			tempRow.innerHTML = 'Username';
// 			thead.appendChild(tempRow);
// 			tempRow = document.createElement('th');
// 			tempRow.innerHTML = 'Age';
// 			thead.appendChild(tempRow);
// 			tempRow = document.createElement('th');
// 			tempRow.innerHTML = 'Position';
// 			thead.appendChild(tempRow);
// 			table.appendChild(thead);
			
// 			let employees = JSON.parse(xhr.responseText);
			
// 			for(let emp in employees) {
// 				let row = document.createElement('tr');
// 				for(let key in employees[emp]) {
// 					let cell = document.createElement('td');
// 					switch(key) {
// 						case "e_id":
// 						case "name":
// 						case "username":
// 						case "age":
// 							cell.innerHTML = employees[emp][key];
// 							row.appendChild(cell);
// 							break;
// 						case "access":
// 							cell.innerHTML = employees[emp][key] == 1 ? "Manager" : "Employee";
// 							row.appendChild(cell);
// 							break;
// 						default:
// 							continue;
// 					}
// 				}
// 				table.appendChild(row);
// 			}
// 			document.getElementById("main").appendChild(table);
// 		}
// 	}
// 	xhr.open('get', '../viewEmployees.employeeDo', true);
// 	xhr.send();
// }

function viewProfile() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		
	}
	xhr.open("get", "../updateEmployee.employeeDo", true);
	xhr.send();
}