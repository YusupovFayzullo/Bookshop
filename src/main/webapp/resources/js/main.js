
function update(category_id) {
    fetch('http://localhost:8080/category/get/' + category_id)
        .then(response => response.json())
        .then(json => {
            document.getElementById("u_id").value = json.id;
            document.getElementById("u_firstName").value = json.name;

        });

}