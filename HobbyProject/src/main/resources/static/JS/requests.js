const URL = '';
// http://localhost:8080
(function () {
    const comicOutput = document.getElementById('comicOutput');
    document.getElementById("comicForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const comic = {};
        comic.title = this.title.value;
        comic.writer = this.writer.value;
        comic.artist = this.artist.value;
        comic.publisher = this.publisher.value;
        comic.issue = this.issue.value;
        axios.post(URL + "/comic/createComic", comic)
            .then(res => comicOutput.innerText = res.data)
            .catch(error => console.log(error));
    });


    document.getElementById("readComicsButton").addEventListener("click", function () {
        axios.get(URL + '/comic/readComic')
            .then(res => {
                comicOutput.innerText = "";
                res.data.forEach((comic, i) => {
                    const comicDiv = makeElement('div', '', comicOutput);
                    comicDiv.id = 'comic' + i;
                    comicDiv.addEventListener('click', function () {
                        window.location = './index.html?id=' + comic.id;
                    });
                    makeElement('h6', comic.title, comicDiv);
                    makeElement('p', `writer: ${comic.writer}`, comicDiv);
                    makeElement('p', `Cover Artist: ${comic.artist}`, comicDiv);
                    makeElement('p', `publisher: ${comic.publisher}`, comicDiv);
                    makeElement('p', `issue: ${comic.issue}`, comicDiv);
                    makeElement('p', `universe: ${comic.universe}`, comicDiv);
                });
            })
            .catch(err => console.log(err));
    });

    function makeElement(eleType, text, appendTo) {
        const element = document.createElement(eleType);
        element.innerText = text;
        appendTo.appendChild(element);
        return element;
    };

    document.getElementById("comicForm").addEventListener("submit", function (event) {
        event.preventDefault();
        const comic = {};
        comic.title = this.title.value;
        comic.writer = this.writer.value;
        comic.artist = this.artist.value;
        comic.publisher = this.publisher.value;
        comic.issue = this.issue.value;
        axios.put(URL + "/comic/updateComic", comic)
            .then(res => comicOutput.innerText = res.data)
            .catch(error => console.log(error));
    });

    document.getElementById('delete-button').addEventListener("click", function () {
        axios.delete(URL + "/comic/deleteComic" + document.getElementById("input-delete").value)
            .then(res => output.innerText = res.data)
            .catch(error => console.log(error));
    });
})();