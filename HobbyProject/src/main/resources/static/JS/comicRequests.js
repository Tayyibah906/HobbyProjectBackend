const BASE_URL = "http://localhost:8080";

(function () {

    function displayOutput(element, {data}) {
        document.getElementById(element).innerText = JSON.stringify(data, undefined, 2);
    }

    document.getElementById('createButton').addEventListener('click', function () {
        let data = {};
        document.querySelectorAll('#create > input').forEach(el => data[el.name] = el.value);
        data.universe={
            id:data.universe
        }
        axios.post(BASE_URL + '/comic/createComic', data)
            .then(res => displayOutput("createOutput", res)
            ).catch(err => console.error(err));
    });

    document.getElementById('readButton').addEventListener('click', function () {
        axios.get(BASE_URL + '/comic/getAll')
            .then(res =>
                document.getElementById('readOutput').innerText = JSON.stringify(res.data)
            ).catch(err => console.error(err));
    });

    document.getElementById('deleteButton').addEventListener('click', function () {
        axios.delete(`${BASE_URL}/comic/deleteComic/${document.getElementById('deleteInput').value}`)
            .then(res =>
                displayOutput("deleteOutput", res)
            ).catch(err => console.error(err));
    });

    document.getElementById("updateForm").addEventListener('submit', function (event) {
        event.preventDefault();
        const comic = {};
        comic.title = this.title.value;
        comic.writer = this.writer.value;
        comic.publisher = this.publisher.value;
        comic.issue = this.issue.value;
        comic.universe = this.universe.value;
        comic.universe={
            id: comic.universe
        }
        axios.put(BASE_URL + "/comic/updateComic?id=" + this.comicID.value, comic)
            .then(res => displayOutput("updateOutput", res))
            .catch(error => console.log(error));
    });

  
})();