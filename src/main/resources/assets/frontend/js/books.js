
let booksPromise = loadBooks();

booksPromise.then(books => {
	displayBooks(books);
	//getBookWhenClickOnLink();
});


// Equivalent to the code above
// loadBooks().then(displayBooks);


/** Gets books from API and returns a promise of books */
function loadBooks() {

	let url = '/api/books';

	// We return the promise that fetch() gives us
	return fetch(url)
		.then(response => response.json())
		.catch(error => {
			console.log("AJAX request finished with an error :(");
			console.error(error);
		});
}

/** Displays books on the HTML */
function displayBooks(books) {

	let html = "<ul>";

	for (const book of books) {
		//html += "<li><a href='' data-id=" + book.id + ">" + book.title + "</a></li>";
		//html += '<li><a href="" data-id="' + book.id + '">' + book.title + '</a></li>';
		html += '<li><a href=http://localhost:8080/assets/frontend/book.html?bookid=' + book.id + '>' + book.title + '</a></li>';
	}

	html += "</ul>";

	const resultDiv = document.getElementById("result");
	resultDiv.innerHTML = html;
}

// We're not using this function.
// It's ok to do it this way,
// although the way we use above is a bit more elegant.

/** Gets books from API and displays them */
function loadBooksAndDisplayThem() {

	let url = '/api/books';

	fetch(url)
		.then(response => response.json())
		.then(books => {
			console.log("AJAX request finished correctly :)");
			const result = `Found ${books.length}`;
			console.log(result);
			displayBooks(books);
		})
		.catch(error => {
			console.log("AJAX request finished with an error :(");
			console.error("ERROR:", error);
		});
}

/** Does a callback for getting a book by its id property when clicking on the link */
function getBookWhenClickOnLink() {

	$('a').click(function(event) {
        event.preventDefault();
        var id = $(this).data('id');
		getBookByIdAndDisplayIt(id);
	});
}

function displayBook(book) {

    let html = "";

    html += book.title + "<br>";

    html += book.author;

	const title = document.getElementById("title");
    const resultDiv = document.getElementById("result");
    title.innerHTML = "Book";
    resultDiv.innerHTML = html;
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

