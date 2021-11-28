import {getBackendUrl} from "../js/configuration.js";

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const paramModel = urlParams.get('name');
const paramId = urlParams.get('id');

window.addEventListener('load', async () => {
    await getAndPreset();
    const submit = document.getElementById("submit");

    submit.addEventListener('click', async () => {
        const mileage = document.getElementById("mileage").value;
        const productionYear = document.getElementById("productionYear").value;

        await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes/'+paramId, {
            method: 'PUT',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify({
                mileage,
                productionYear,
            })
        })
    })
})

async function getAndPreset() {
    const request = await fetch(getBackendUrl()+'/models/'+paramModel+'/airplanes/'+paramId);
    const model = await request.json();

    document.getElementById("mileage").value = model.mileage;
    document.getElementById("productionYear").value = model.productionYear;
}