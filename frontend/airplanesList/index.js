import {getBackendUrl} from "../js/configuration.js";
import {clearElementChildren, createButtonCell, createLinkCell, createTextCell} from "../js/dom_utils.js";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const paramModel = urlParams.get('name');

window.addEventListener('load', async () => {
    await fetchAndShowModels();
    await fetchAndShowElements();
    document.getElementById("addlink").href = 'http://127.0.0.1:8083/airplanesList/add.html?name='+paramModel;
})

async function fetchAndShowModels() {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel);
    const model = await request.json();

    document.getElementById("model1").innerText = model.name;
    document.getElementById("model2").innerText = model.numberOfSeats;
    document.getElementById("model3").innerText = model.fuelCapacity;
    document.getElementById("model4").innerText = model.maxWeight;
}

async function fetchAndShowElements() {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes');
    const elements = await request.json();
    console.log(elements);
    displayElements(elements.airplanes);
}

function displayElements(elements) {
    let element = document.getElementById('elements');
    clearElementChildren(element);
    elements.forEach(airplane => {
        element.appendChild(createTextCell("Airplane with id: "+airplane));
        element.appendChild(createButtonCell('remove',async () => await deleteAirplane(airplane)));
        element.appendChild(document.createElement('br'));
        element.appendChild(createLinkCell('edit','http://127.0.0.1:8083/airplanesList/edit.html?name='+paramModel+'&id='+airplane))
        element.appendChild(document.createElement('div'));
        element.appendChild(createLinkCell('view','http://127.0.0.1:8083/airplanesList/view.html?name='+paramModel+'&id='+airplane))
        element.appendChild(document.createElement('br'));
    })
}

async function deleteAirplane(airplane) {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes/'+airplane, {
        method: 'DELETE'
    })
}