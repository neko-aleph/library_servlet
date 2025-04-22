let catalog = document.getElementById("catalog-table")
let my = document.getElementById("my-table")

fetch('http://localhost:8081/java_servlet_war/book')
    .then(response => {
        if (!response.ok) throw new Error('Network response was not ok');
        return response.json();
    })
    .then(data => {
        console.log('Response from servlet:', data);
        data.forEach((item) => {
            if (!item["isBorrowed"]) {
                catalog.innerHTML += `<tr><td>${item['title']}</td><td>${item['author']}</td><td><button onclick="return(${item['id']})">Вернуть</button></td></tr>`;
            } // else if(item["borrowerId"] == id) {
            //  my.innerHTML += `<tr><td>${item['title']}</td><td>${item['author']}</td><td><button onclick="return(${item['id']})">Вернуть</button></td></tr>`;
            //}
        });
    })
    .catch(error => {
        console.error('Error:', error);
    });

function returnBook(id) {

}