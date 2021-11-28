import {getBackendUrl} from "../js/configuration.js";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const paramModel = urlParams.get('name');
const paramId = urlParams.get('id');

window.addEventListener('load', async () => {
    await getAndPreset();
})

async function getAndPreset() {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes/'+paramId);
    const model = await request.json();

    document.getElementById("element1").innerText = model.id;
    document.getElementById("element2").innerText = model.mileage;
    document.getElementById("element3").innerText = model.productionYear;
}