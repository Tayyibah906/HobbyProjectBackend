<<<<<<< HEAD
const params = new URLSearchParams(window.location.search);
const URL = '';
//http://localhost:8080

function postComic() {
    document.getElementById("comicForm").addEventListener("submit", function(event){
      debugger
=======
const requestData = XMLHttpRequest();
const params = new URLSearchParams(window.location.search);
const BASE_URL = "http://localhost:8080"

function postComic() {
    document.getElementById("comicForm").addEventListener("submit", function(event)){
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
        event.preventDefault();
        const comic = {};
        comic.title = this.title.value;
        comic.writer = this.writer.value;
        comic.artist = this.artist.value;
        comic.publisher = this.publisher.value;
        comic.issue = this.issue.value;
<<<<<<< HEAD
        axios.post(URL + "/comic/createComic", comic)
            .then(response => output.innerText = res.comic)
            .catch(error => console.log(error));
    });
    
=======
        axios.post(`${BASE_URL}/hobby/createComic`, comic)
            .then(response => output.innerText = res.comic)
            .catch(error => console.log(error));
    };
    let insertmsg = document.querySelector(".insertmsg");
    getMsg(flag, insertmsg);
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
};

function getComic() {
    const comicOutput = document.getElementById('comicOutput');
<<<<<<< HEAD
    axios.get(URL +'/comic/getAll')
=======
    axios.get(`${BASE_URL}/hobby/getAll`)
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
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
};

function makeElement(eleType, text, appendTo) {
    const element = document.createElement(eleType);
    element.innerText = text;
    appendTo.appendChild(element);
    return element;
};

function putComic() {
<<<<<<< HEAD
    document.getElementById("comicForm").addEventListener("submit", function(event){
=======
    document.getElementById("comicForm").addEventListener("submit", function(event)){
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
        event.preventDefault();
        const comic = {};
        comic.title = this.title.value;
        comic.writer = this.writer.value;
        comic.artist = this.artist.value;
        comic.publisher = this.publisher.value;
        comic.issue = this.issue.value;
<<<<<<< HEAD
        axios.put(URL + "/comic/updateComic", comic)
            .then(response => output.innerText = res.comic)
            .catch(error => console.log(error));
    });
=======
        axios.put(`${BASE_URL}/hobby/updateComic`, comic)
            .then(response => output.innerText = res.comic)
            .catch(error => console.log(error));
    };
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
    let updatemsg = document.querySelector(".updatemsg");
      getMsg(get, updatemsg);
};

document.getElementById('delete-button').addEventListener("click", function(){
<<<<<<< HEAD
    axios.delete(URL + "/comic/deleteComic" + document.getElementById("input-delete").value)
=======
    axios.delete(`${BASE_URL}/deleteComic` + document.getElementById("input-delete").value)
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
    .then(response => output.innerText = res.comic)
    .catch(error => console.log(error));

    let deletemsg = document.querySelector(".deletemsg");
  getMsg(true, deletemsg);
});

<<<<<<< HEAD
postComic();
getComic();
putComic();
=======


function postUniverse() {
    document.getElementById("universeForm").addEventListener("submit", function(event)){
        event.preventDefault();
        const universe = {};
        universe.name = this.name.value;
        axios.post(`${BASE_URL}/hobby/createUniverse`, universe)
            .then(response => output.innerText = res.universe)
            .catch(error => console.log(error));
    };

function getUniverse() {
    const universeOutput = document.getElementById('universeOutput');
    axios.get(`${BASE_URL}/hobby/getAllUniverse`)
        .then(res => {
            universeOutput.innerText = "";
            res.data.forEach((universe, i) => {
                const universeDiv = makeElement('div', '', universeOutput);
                universeDiv.id = 'universe' + i;
                universeDiv.addEventListener('click', function () {
                    window.location = './index.html?id=' + universe.id;
                });
                makeElement('p', universe.name, universeDiv);
            });
        })
        .catch(err => console.log(err));
};

function makeElement(eleType, text, appendTo) {
    const element = document.createElement(eleType);
    element.innerText = text;
    appendTo.appendChild(element);
    return element;
};

function putUniverse() {
    document.getElementById("universeForm").addEventListener("submit", function(event)){
        event.preventDefault();
        const universe = {};
        universe.name = this.name.value;
        axios.put(`${BASE_URL}/hobby/updateUniverse`, universe)
            .then(response => output.innerText = res.universe)
            .catch(error => console.log(error));
    };

document.getElementById('delete-button').addEventListener("click", function(){
    axios.delete(`${BASE_URL}/deleteUniverse` + document.getElementById("input-delete").value)
    .then(response => output.innerText = res.universe)
    .catch(error => console.log(error));
  })
 }
}
>>>>>>> 71d5ef02330a1fca406b8a1f397d42e3842491f6
