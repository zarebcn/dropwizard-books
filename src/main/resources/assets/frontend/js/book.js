
//getBookByIdAndDisplayIt(getIdFromUrl());

let booksPromise = loadBook(getIdFromUrl());

booksPromise.then(book => {
    displayBook(book);
});

homePageRedirect();


/** Goes to Home page when home button is clicked */
function homePageRedirect() {

    var button = document.querySelector("button");

    button.onclick = function () {

        location.href = "http://localhost:8080/assets/frontend/books.html";
    }
}

function loadBook(bookid) {

    let url = '/api/books/' + bookid;

    // We return the promise that fetch() gives us
    return fetch(url)
        .then(response => response.json())
        .catch(error => {
            console.log("AJAX request finished with an error :(");
            console.error(error);
        });
}

/** Extracts the id parameter from the URL and returns it*/
function getIdFromUrl() {

    var url = location.href;
    var equalSignIndex = url.indexOf("=");
    var id = url.substring(equalSignIndex + 1, url.length);
    return id;
}

function getBookByIdAndDisplayIt(bookid) {

    let url = '/api/books/' + bookid;

    fetch(url)
        .then(response => response.json())
        .then(book => {
            console.log("AJAX request finished correctly :)");
            displayBook(book);
        })
        .catch(error => {
            console.log("AJAX request finished with an error :(");
            console.error("ERROR:", error);
        });
}

function displayBook(book) {

    let html = "";

    html += book.title + "<br>";

    html += book.author;

    const resultDiv = document.getElementById("result");
    resultDiv.innerHTML = html;
}