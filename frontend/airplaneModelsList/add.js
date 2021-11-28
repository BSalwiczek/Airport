import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load', async () => {
    const submit = document.getElementById("submit");
    submit.addEventListener('click', async () => {
        const name = document.getElementById("name").value;
        const numberOfSeats = document.getElementById("numberOfSeats").value;
        const fuelCapacity = document.getElementById("fuelCapacity").value;
        const maxWeight = document.getElementById("maxWeight").value;

        await fetch(getBackendUrl()+'/models', {
            method: 'POST',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify({
                name,
                numberOfSeats,
                fuelCapacity,
                maxWeight
            })
        })
    })
})