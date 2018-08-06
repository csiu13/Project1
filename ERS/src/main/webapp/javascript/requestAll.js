window.onload = () => {
    getUser();
    document.getElementById('close').addEventListener('click', function() {
        document.getElementById('popup').style.display = "none";
    });
    document.getElementById('approve').addEventListener('click', function() {
        approve('approve');
    });
    document.getElementById('decline').addEventListener('click', function() {
        approve('decline');
    });
}
function makeTable(user) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState === 4 && xhr.status === 200) {
			let name = user.name;
            let table = document.getElementById("request-table");
            //console.log(xhr.responseText);
			let requests = JSON.parse(xhr.responseText);
            //console.log(requests);
            if(requests.length === 0) {
				alert("No requests found.");
				return;
			}
			for(let req in requests) {
                let row = document.createElement('tr');
                row.id = req;
                let ridCell = document.createElement('td');
                ridCell.style.display = "none";
                ridCell.id = req + "-rid";
                let manCell = document.createElement('td');
                let empCell = document.createElement('td');
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
                        case "r_id":
                            ridCell.innerHTML = requests[req][key];
                            break;
                        case "manager":
                            manCell.innerHTML = requests[req][key] === "nobody" ? '--' : requests[req][key];
                            break;
                        case "employee":
                        	var tempName = requests[req][key];
                            empCell.innerHTML = requests[req][key];
                            break;
						case "status":
                            let status = requests[req][key];
                            let span = document.createElement('span');
                            span.id = req + '-status'
                            if(status === -1) {
                                span.className = "badge badge-danger";
                                span.innerHTML = "DECLINED";
                            } else {
                                span.className = status === 0 ? "badge badge-secondary" : "badge badge-success";
                                span.innerHTML = status === 0 ? "PENDING" : "ACCEPTED";
                            }
                            if(tempName === name) {
                                span.onclick = alertSameName;
                            } else if (status !== 0) {
                                span.onclick = alertApproved;
                            } else {
                                span.onclick = openPopup;
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
                row.appendChild(ridCell);
                row.appendChild(reqCell);
                row.appendChild(comCell);
                row.appendChild(empCell);
                row.appendChild(manCell);
                row.appendChild(amtCell);
                row.appendChild(staCell);
                row.appendChild(reaCell);
				table.appendChild(row);
			}
		}
	}
	xhr.open('get', '../viewAllRequests.employeeDo', true);
	xhr.send();
}

function formatDate(date) {
    if(date === "none") {
        return "--";
    }
    let toReturn = date.match(/\d+\-\d+\-\d+/)
    return toReturn + ' ' + date.match(/\d+\:\d+\:\d+/);
}

function alertApproved() {
    alert("Request cannot further declined/approved!");
}

function alertSameName() {
    alert("You cannot approve your own requests!");
}

function getUser() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            makeTable(JSON.parse(xhr.responseText));
        }
    }
    xhr.open('get', "../*.employeeDo?check=true", true);
    xhr.send();
}

function openPopup(event) {
    console.log(event);
    document.getElementById('popup').style.display = "inline";
    document.getElementById('popup').throwaway = event.target.parentNode.parentNode.id;
}

function approve(event) {
    document.getElementById('popup').style.display = "none";
    //console.log(event);
     let xhr = new XMLHttpRequest();
     xhr.onreadystatechange = function() {
        if(xhr.readyState === 4 && xhr.status === 200) {
            let r = JSON.parse(xhr.responseText);
            let xhr2 = new XMLHttpRequest();
            xhr2.onreadystatechange = function() {
                if(xhr2.readyState === 4 && xhr2.status === 200) {
                    if(xhr.responseText !== "fail") {
                        alert("Request has been successfully " + event +"d! Please refresh page to see effects! (WIP)");
                    }
                    // let requests = JSON.parse(xhr.responseText);
                    // //console.log(requests);
                    // for(let req in requests) {
                    //     //console.log(req);
                    //     if(requests[req].r_id === r.r_id) {
                    //         let span = document.getElementById(req+"-status");
                    //         span.setAttribute("class", event === 'decline' ? "badge badge-danger" : "badge badge-success");
                    //         span.setAttribute("innerHTML", event === 'decline' ? "DECLINED" : "ACCEPTED");
                            
                            
                    //     }
                    // }
                } 
            }
            xhr2.open('get', '../*.employeeDo?checkList=true', true);
            xhr2.send();
        }
     }
     let throwaway = document.getElementById('popup').throwaway;
     let link = document.getElementById(throwaway +'-rid').innerHTML;
     let a = event === 'approve' ? 1 : -1;
     //console.log(link + " " + throwaway + " " + a);
     xhr.open('get', '../approveRequest.employeeDo?r_id='+link+'&approve='+a+'&completed='+JSON.stringify(new Date()), true);
     xhr.send();
}