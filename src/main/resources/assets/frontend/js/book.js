
getIdFromUrlAndGetBookById();
homeButton();

/** Goes to Home page when home button is clicked */
function homeButton() {

    var button = document.querySelector("button");

    button.onclick = function () {

        location.href = "http://localhost:8080/assets/frontend/books.html";
    }
}

/** Extracts the id parameter from the URL and does a callback for getting a book by the extracted id */
function getIdFromUrlAndGetBookById() {

    var url = location.href;
    var igual = url.indexOf("=");
    var id = url.substring(igual + 1, url.length);
    getBookByIdAndDisplayIt(id);
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