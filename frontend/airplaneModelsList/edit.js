import {getBackendUrl} from "../js/configuration.js";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const paramModel = urlParams.get('name');

window.addEventListener('load', async () => {
    await getAndPreset();
    const submit = document.getElementById("submit");

    submit.addEventListener('click', async () => {
        const numberOfSeats = document.getElementById("numberOfSeats").value;
        const fuelCapacity = document.getElementById("fuelCapacity").value;
        const maxWeight = document.getElementById("maxWeight").value;

        await fetch(getBackendUrl()+'/models/'+paramModel, {
            method: 'PUT',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify({
                numberOfSeats,
                fuelCapacity,
                maxWeight
            })
        })
    })
})

async function getAndPreset() {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel);
    const model = await request.json();

    document.getElementById("numberOfSeats").value = model.numberOfSeats;
    document.getElementById("fuelCapacity").value = model.fuelCapacity;
    document.getElementById("maxWeight").value = model.maxWeight;
}