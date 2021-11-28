import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from "../js/configuration.js";

window.addEventListener('load', async () => {
    await fetchAndShow();
})

async function fetchAndShow() {
    const request = await fetch(getBackendUrl()+'/models');
    const models = await request.json();
    console.log(models);
    displayModels(models.airplaneModels);
}

function displayModels(models) {
    let element = document.getElementById('models');
    clearElementChildren(element);
    models.forEach(model => {
        element.appendChild(createTextCell(model));
        element.appendChild(createButtonCell('remove',async () => await deleteModel(model)));
        element.appendChild(document.createElement('br'));
        element.appendChild(createLinkCell('edit','http://127.0.0.1:8083/airplaneModelsList/edit.html?name='+model))
        element.appendChild(document.createElement('div'));
        element.appendChild(createLinkCell('view','http://127.0.0.1:8083/airplanesList/index.html?name='+model))
        element.appendChild(document.createElement('br'));
    })
}

async function deleteModel(model) {
    const request = await fetch(getBackendUrl()+'/models/'+model, {
        method: 'DELETE'
    })
}