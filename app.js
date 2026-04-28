const API="https://mmat-semester-project.onrender.com";

/* -------- DLL BUFFER -------- */
async function addToBuffer(){
let id=document.getElementById("pkgId").value;
let dest=document.getElementById("dest").value;

await fetch(API_URL+"/buffer/add",{
method:"POST",
headers:{"Content-Type":"application/json"},
body:JSON.stringify({id,dest})
});

loadBuffer();
}

async function removeFromBuffer(){
await fetch(API_URL+"/buffer/remove",{method:"POST"});
loadBuffer();
}

async function loadBuffer(){
let res=await fetch(API_URL+"/buffer");
let data=await res.json();

let div=document.getElementById("buffer");
div.innerHTML="";

data.forEach(p=>{
div.innerHTML+=`<div class="item"><span>${p.id}</span><span>${p.destination}</span></div>`;
});
}

/* -------- QUEUE -------- */
async function enqueue(){
await fetch(API_URL+"/queue/enqueue",{method:"POST"});
loadQueue();
}

async function dequeue(){
await fetch(API_URL+"/queue/dequeue",{method:"POST"});
loadQueue();
}

async function loadQueue(){
let res=await fetch(API_URL+"/queue");
let data=await res.json();

let div=document.getElementById("queue");
div.innerHTML="";
data.forEach(p=>{
div.innerHTML+=`<div class="item">${p.id}</div>`;
});
}

/* -------- STACK -------- */
async function pushTruck(){
await fetch(API_URL+"/stack/push",{method:"POST"});
loadStack();
}

async function popTruck(){
await fetch(API_URL+"/stack/pop",{method:"POST"});
loadStack();
}

async function loadStack(){
let res=await fetch(API_URL+"/stack");
let data=await res.json();

let div=document.getElementById("stack");
div.innerHTML="";
data.forEach(p=>{
div.innerHTML+=`<div class="item">${p.id}</div>`;
});
}

/* -------- GRAPH -------- */
async function shortestPath(){
let s=document.getElementById("start").value;
let e=document.getElementById("end").value;

let res=await fetch(`${API_URL}/graph/shortest?start=${s}&end=${e}`);
document.getElementById("graphOut").innerText=await res.text();
}

async function mst(){
let res=await fetch(API_URL+"/graph/mst");
document.getElementById("graphOut").innerText=await res.text();
}

/* -------- AVL -------- */
async function searchAVL(){
let key=document.getElementById("searchKey").value;

let res=await fetch(`${API_URL}/avl/search?key=${key}`);
document.getElementById("avlOut").innerText=await res.text();
}

/* -------- SLL LOG -------- */
async function loadLog(){
let res=await fetch(API_URL+"/log");
let data=await res.json();

let div=document.getElementById("log");
div.innerHTML="";

data.forEach(p=>{
div.innerHTML+=`<div class="item">${p.id} -> ${p.destination}</div>`;
});
}

/* init */
loadBuffer();
loadQueue();
loadStack();
loadLog();
