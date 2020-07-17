const BASE_URL = "http://localhost:8080";

(function () {

    function displayOutput(element, {data}) {
        document.getElementById(element).innerText = JSON.stringify(data, undefined, 2);
    }

    document.getElementById('createButton').addEventListener('click', function () {
        let data = {};
        document.querySelectorAll('#create > input').forEach(el => data[el.name] = el.value);
        axios.post(BASE_URL + '/universe/createUniverse', data)
            .then(res => displayOutput("createOutput", res)
            ).catch(err => console.error(err));
    });

    document.getElementById('readButton').addEventListener('click', function () {
        axios.get(BASE_URL + '/universe/getAll')
            .then(res =>
                document.getElementById('readOutput').innerText = JSON.stringify(res.data)
            ).catch(err => console.error(err));
    });

    document.getElementById('deleteButton').addEventListener('click', function () {
        axios.delete(`${BASE_URL}/universe/deleteUniverse/${document.getElementById('deleteInput').value}`)
            .then(res =>
                displayOutput("deleteOutput", res)
            ).catch(err => console.error(err));
    });

    document.getElementById("updateForm").addEventListener('submit', function (event) {
        event.preventDefault();
        const universe = {};
        universe.name = this.name.value;
        axios.put(BASE_URL + "/universe/updateUniverse?id=" + this.universeID.value, universe)
            .then(res => displayOutput("updateOutput", res))
            .catch(error => console.log(error));
    });


  
})();