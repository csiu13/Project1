window.onload = function () {
    
    document.getElementById("request-form").onsubmit = (event) => {
        createRequest(event);
    }
}

function createRequest(event) {
    event.preventDefault();
    let reason = document.getElementById("reason").value;
    let amount = document.getElementById("amount").value;
    if(reason === "" || amount === "") {
        alert("Please fill out all of the fields!");
    } else {
        let currPerson = JSON.parse(xhr.responseText);
        let requested = JSON.stringify(new Date());
        amount = parseFloat(amount).toFixed(2);
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            console.log(xhr.responeText);
        }
        let link = `requested=${requested}&reason=${reason}&amount=${amount}`;
        xhr.open('get', '../makeRequest.employeeDo?' + link, true);
        xhr.send();
    }
    
}