item_form.onsubmit = async (e) => {

  console.log("reached submission point");

  e.preventDefault();

  let item = {};
  let fd = new FormData(item_form);
  fd.forEach( (v,k) => {item[k] = v;});
  console.log(JSON.stringify(item));

  const options = {
    method: 'POST',
    body: JSON.stringify(claim),
    headers: { 'Content-Type': 'application/json' }
  }

   fetch('/add', options)

     .then(res => res.json())
//     .then(res => updatePage(res.id, res.claimFromWho, res.dueDate, res.claimAmount))
     .catch(err => console.error(err));
};