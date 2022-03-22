

function getDropDownMenu(res) {
    let const dropdown = document.getElementById("dropdown")

    let myArray = res["models"]


    let select = document.getElementById("select")

        for (var i = 0; i < myArray.length; i++) {

             var model = myArray[i]
             var el = document.createElement("option");
//
//            console.log(json);
//            console.log(JSON.parse(json));

            var optn = elmts[""];

            el.textContent = model["manufacturer"] + model["series"] + model["modelNumber"]
            + model["gbStorage"]
            el.value = optn;
            select.appendChild(el);


  console.log("reached submission point");

  e.preventDefault();

  const options = {
    method: 'GET',
//    body: JSON.stringify(claim),
    headers: { 'Content-Type': 'application/json' }
  }

   fetch('/models', options)

     .then(res => res.json())
     .then(res => getDropDownMenu(res))
     .catch(err => console.error(err));
