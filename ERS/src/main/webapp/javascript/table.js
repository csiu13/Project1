window.onload = () => {
	//console.log("hello");
    let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let table = document.getElementById("employee-table");
			let employees = JSON.parse(xhr.responseText);
			
			for(let emp in employees) {
				let row = document.createElement('tr');
				row.id = emp;
				row.addEventListener('click', createRequestTable);
				for(let key in employees[emp]) {
					let cell = document.createElement('td');
					switch(key) {
						case "e_id":
							cell.id = emp+"-eid";
						case "name":
						case "username":
						case "age":
							cell.innerHTML = employees[emp][key];
							row.appendChild(cell);
							break;
						case "access":
							cell.innerHTML = employees[emp][key] == 1 ? "Manager" : "Employee";
							cell.innerHTML = employees[emp][key] === -1 ? "Janitor" : "Employee";
							row.appendChild(cell);
							break;
						default:
							continue;
					}
				}
				let requestTable = document.createElement('td');
				requestTable.id = emp + "-rTable";
				row.appendChild(requestTable);
				table.appendChild(row);
			}
		}
	}
	xhr.open('get', '../viewEmployees.employeeDo', true);
	xhr.send();
}

function createRequestTable(event) {
	var index = event.target.parentNode.id;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			console.log(index);
            let table = document.getElementById(index+"-rTable");
            //console.log(xhr.responseText);
			let requests = JSON.parse(xhr.responseText);
			if(requests.length === 0) {
				alert("No requests found.");
				return;
			}
			for(let req in requests) {
                let row = document.createElement('tr');
                row.id = req;
                let manCell = document.createElement('td');
                let reqCell = document.createElement('td');
                let comCell = document.createElement('td');
                let amtCell = document.createElement('td');
                let staCell = document.createElement('td');

                
                let reaCell = document.createElement('td');
                let openReason = document.createElement('button');
                openReason.className = 'btn btn-primary collapsed';
                openReason.innerHTML = 'Expand';
                reaCell.appendChild(openReason);

				for(let key in requests[req]) {
					switch(key) {
                        case "manager":
                            manCell.innerHTML = requests[req][key] === "nobody" ? '--' : requests[req][key];
							break;
						case "status":
                            let status = requests[req][key];
                            let span = document.createElement('span');
                            if(status === -1) {
                                span.className = "badge badge-danger";
                                span.innerHTML = "DECLINED";
                            } else {
                                span.className = status === 0 ? "badge badge-secondary" : "badge badge-success";
                                span.innerHTML = status === 0 ? "PENDING" : "ACCEPTED";
                            }
                            staCell.appendChild(span);
							break;
						case "amount":
							amtCell.innerHTML = requests[req][key];
                            break;
                        case "reason":
                            openReason.onclick = () => {
                                alert(requests[req][key]);
                            }
                            break;
                        case "requested":
                            reqCell.innerHTML = formatDate(requests[req][key]);
                            break;
                        case "completed":
                            comCell.innerHTML = formatDate(requests[req][key]);
                            break;
						default:
							continue;
					}
                }
                row.appendChild(reqCell);
                row.appendChild(comCell);
                row.appendChild(manCell);
                row.appendChild(amtCell);
                row.appendChild(staCell);
                row.appendChild(reaCell);
				table.appendChild(row);
			}
		}
	}
	let link = document.getElementById(index+"-eid").innerHTML;
	xhr.open('get', '../viewTheirRequests.employeeDo?e_id=' + link, true);
	xhr.send();
}


function formatDate(date) {
    if(date === "none") {
        return "--";
    }
    let toReturn = date.match(/\d+\-\d+\-\d+/)
    return toReturn + ' ' + date.match(/\d+\:\d+\:\d+/);
}