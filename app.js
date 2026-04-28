const API="https://mmat-semester-project.onrender.com";

async function load(){
let res=await fetch(API+"/packages");
let data=await res.json();

let list=document.getElementById("list");
list.innerHTML="";

data.forEach(p=>{
let div=document.createElement("div");
div.innerHTML=`<span>${p.id}</span><span>${p.destination}</span>`;
list.appendChild(div);
});
}

async function enqueue(){
let dest=document.getElementById("dest").value;

await fetch(API+"/enqueue",{
method:"POST",
headers:{"Content-Type":"application/json"},
body:JSON.stringify({destination:dest})
});

load();
}

async function process(){
await fetch(API+"/process",{method:"POST"});
load();
}

async function route(){
let s=document.getElementById("start").value;
let e=document.getElementById("end").value;

let res=await fetch(`${API}/shortest-path?start=${s}&end=${e}`);
let data=await res.text();

document.getElementById("routeResult").innerText=data;
}

async function search(){
let k=document.getElementById("searchKey").value;

let res=await fetch(`${API}/search?key=${k}`);
let data=await res.text();

document.getElementById("searchResult").innerText=data;
}

load();
