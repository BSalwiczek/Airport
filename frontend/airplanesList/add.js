import {getBackendUrl} from "../js/configuration.js";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const paramModel = urlParams.get('name');

window.addEventListener('load', async () => {
    const submit = document.getElementById("submit");
    submit.addEventListener('click', async () => {
        const mileage = document.getElementById("mileage").value;
        const productionYear = document.getElementById("productionYear").value;

        await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes', {
            method: 'POST',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify({
                mileage,
                productionYear,
                airplaneModel: paramModel
            })
        })
    })
})