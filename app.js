const API="https://mmat-semester-project.onrender.com";

async function addShipment(){

let id=document.getElementById("shipId").value;
let addr=document.getElementById("address").value;

await fetch(API+"/shipment/add",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
id:id,
destination:addr
})
});

printAll();
}

async function deleteShipment(){

let id=document.getElementById("shipId").value;

await fetch(API+"/shipment/delete",{
method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({id:id})
});

printAll();
}

async function printAll(){

let res=
await fetch(API+"/shipment/all");

let data=await res.json();

let box=
document.getElementById("shipments");

box.innerHTML="";

data.forEach(s=>{

box.innerHTML+=`
<div class="item">
<span>${s.id}</span>
<span>${s.destination}</span>
</div>
`;

});
}

async function searchAddress(){

let q=
document.getElementById("search").value;

let res=
await fetch(API+"/address/search?key="+q);

document.getElementById("searchOut")
.innerText=await res.text();
}

async function buildRoute(){

let res=
await fetch(API+"/route/optimal");

document.getElementById("routeOut")
.innerText=await res.text();
}

printAll();
