const API="https://mmat-semester-project.onrender.com";


/* -------- SHIPMENTS -------- */

async function addShipment(){
let id=document.getElementById("shipId").value;
let addr=document.getElementById("address").value;

await fetch(API+"/shipment/add",{
method:"POST",
headers:{"Content-Type":"application/json"},
body:JSON.stringify({id,address:addr})
});

printAll();
}

async function deleteShipment(){
let id=document.getElementById("shipId").value;

await fetch(API+"/shipment/delete",{
method:"POST",
headers:{"Content-Type":"application/json"},
body:JSON.stringify({id})
});

printAll();
}

async function printAll(){
let res=await fetch(API+"/shipment/all");
let data=await res.json();

let box=document.getElementById("shipments");
box.innerHTML="";

data.forEach(s=>{
box.innerHTML+=`<div class="item">
<span>${s.id}</span>
<span>${s.address}</span>
</div>`;
});
}

/* -------- ADDRESS SEARCH (AVL) -------- */

async function searchAddress(){
let q=document.getElementById("search").value;

let res=await fetch(API+"/address/search?key="+q);
document.getElementById("searchOut").innerText=await res.text();
}

/* -------- OPTIMAL ROUTE (DIJKSTRA / KRUSKAL) -------- */

async function buildRoute(){
let res=await fetch(API+"/route/optimal");
let data=await res.text();

document.getElementById("routeOut").innerText=data;
}

printAll();

