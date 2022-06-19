import createFormString from "./createFormString.js";

//Retrieves the search form
const emailForm = document.getElementById("form");

//Sets the action to do when submitting the search form
emailForm.onsubmit = (e) => {
    e.preventDefault();
    btnSend();
};

//Handler function for the userForm form
async function btnSend() {
    const resp = await fetch("http://localhost:8080/user", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
        },
        body: createFormData(),
    })
        .catch(() => console.log("Rejected"))
        .then((response) => {
            return response.json();
        });

    if (resp.sent) {
        var x = document.getElementById("snackbar");
        x.className = "show";
        setTimeout(function () {
            x.className = x.className.replace("show", "");
        }, 3000);
    }
}

//Creates string from the data retrieved from the search form
const createFormData = () => {
    let keys = [];
    let values = [];

    keys.push("costumerEmail");
    values.push(document.getElementById("costumerEmail").value);
    keys.push("subject");
    values.push(document.getElementById("subject").value);
    keys.push("massage");
    values.push(document.getElementById("massage").value);

    return createFormString(keys, values);
};
