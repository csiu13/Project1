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
        let requested = JSON.stringify(new Date());
        amount = parseFloat(amount);
        console.log(amount.toFixed(2));
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if(xhr.responseText === "success") {
                alert("Request successully created!");
                document.getElementById("reason").value = "";
                document.getElementById("amount").value = 0;
            }
        }
        let link = `requested=${requested}&reason=${reason}&amount=${amount}`;
        xhr.open('get', '../makeRequest.employeeDo?' + link, true);
        xhr.send();
    }
    
}