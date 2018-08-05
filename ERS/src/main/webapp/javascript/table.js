window.onload = () => {
	console.log("hello");
   let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let table = document.getElementById("employee-table");
			let thead = document.createElement('tr');
			let tempRow = document.createElement('th');
			tempRow.innerHTML = 'Employee ID';
			thead.appendChild(tempRow);
			tempRow = document.createElement('th');
			tempRow.innerHTML = 'Name';
			thead.appendChild(tempRow);
			tempRow = document.createElement('th');
			tempRow.innerHTML = 'Username';
			thead.appendChild(tempRow);
			tempRow = document.createElement('th');
			tempRow.innerHTML = 'Age';
			thead.appendChild(tempRow);
			tempRow = document.createElement('th');
			tempRow.innerHTML = 'Position';
			thead.appendChild(tempRow);
			table.appendChild(thead);
			
			let employees = JSON.parse(xhr.responseText);
			
			for(let emp in employees) {
				let row = document.createElement('tr');
				for(let key in employees[emp]) {
					let cell = document.createElement('td');
					switch(key) {
						case "e_id":
						case "name":
						case "username":
						case "age":
							cell.innerHTML = employees[emp][key];
							row.appendChild(cell);
							break;
						case "access":
							cell.innerHTML = employees[emp][key] == 1 ? "Manager" : "Employee";
							row.appendChild(cell);
							break;
						default:
							continue;
					}
				}
				table.appendChild(row);
			}
		}
	}
	xhr.open('get', '../viewEmployees.employeeDo', true);
	xhr.send();
}