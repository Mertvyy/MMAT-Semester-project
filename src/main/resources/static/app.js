const API = "/api";

async function refreshAll() {
    await fetchBuffer();
    await fetchLog();
}

async function fetchBuffer() {
    const res = await fetch(API + "/warehouse/buffer");
    const data = await res.json();
    const container = document.getElementById("buffer-log");
    
    if (data.length === 0) {
        container.innerHTML = '<div class="placeholder">Intake Buffer is empty...</div>';
        return;
    }

    container.innerHTML = data.map(p => `
        <div class="item-card">
            <span class="id">${p.id}</span>
            <span class="dest">${p.destination}</span>
        </div>
    `).join('');
}

async function fetchLog() {
    const res = await fetch(API + "/warehouse/log");
    const data = await res.json();
    const container = document.getElementById("audit-log");
    
    if (data.length === 0) {
        container.innerHTML = '<div class="placeholder">Audit log is currently empty...</div>';
        return;
    }

    container.innerHTML = data.map(p => `
        <div class="item-card" style="border-left: 3px solid #10b981">
            <span class="id">${p.id}</span>
            <span class="dest">${p.destination}</span>
        </div>
    `).reverse().join('');
}

async function addShipment() {
    const id = document.getElementById("shipId").value;
    const addr = document.getElementById("address").value;
    
    if (!id || !addr) {
        alert("Please enter ID and Address");
        return;
    }

    await fetch(API + "/shipment/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ id, destination: addr })
    });

    document.getElementById("shipId").value = "";
    document.getElementById("address").value = "";
    refreshAll();
}

async function step1() {
    const res = await fetch(API + "/warehouse/step1-to-queue", { method: "POST" });
    document.getElementById("pipeline-output").innerText = await res.text();
    fetchBuffer();
}

async function step2() {
    const res = await fetch(API + "/warehouse/step2-to-stack", { method: "POST" });
    document.getElementById("pipeline-output").innerText = await res.text();
}

async function step3() {
    const res = await fetch(API + "/warehouse/step3-to-log", { method: "POST" });
    document.getElementById("pipeline-output").innerText = await res.text();
    fetchLog();
}

async function searchAddress() {
    const q = document.getElementById("search-query").value;
    if (!q) return;
    const res = await fetch(API + "/address/search?key=" + q);
    document.getElementById("search-output").innerText = await res.text();
}

async function getShortestPath() {
    const res = await fetch(API + "/route/shortest?start=Meydan");
    document.getElementById("route-output").innerHTML = `<pre>${await res.text()}</pre>`;
}

async function getMST() {
    const res = await fetch(API + "/route/mst");
    document.getElementById("route-output").innerHTML = `<pre>${await res.text()}</pre>`;
}

// Initial load
refreshAll();
