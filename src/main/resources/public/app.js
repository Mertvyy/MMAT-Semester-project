const API = "";

function showToast(message, type = "success") {
    const container = document.getElementById("toast-container");
    const toast = document.createElement("div");
    toast.className = `toast ${type}`;
    toast.innerText = message;
    container.appendChild(toast);
    setTimeout(() => toast.remove(), 3000);
}

async function refreshAll() {
    try {
        await fetchBuffer();
        await fetchLog();
        await fetchGlobalList();
        await checkHealth();
    } catch (e) {
        console.error("Refresh failed", e);
    }
}

async function fetchGlobalList() {
    try {
        const res = await fetch(API + "/shipment/all");
        if (!res.ok) throw new Error();
        const data = await res.json();
        const container = document.getElementById("global-parcel-list");
        container.innerHTML = data.map(p => `
            <div class="item-card" style="border-bottom:1px solid #111; padding:10px;">
                <span style="color:#8ACE00; font-weight:900;">${p.id}</span>
                <span style="color:#FF69B4;">${p.destination}</span>
            </div>
        `).join('');
    } catch (e) {
        document.getElementById("global-parcel-list").innerHTML = "Failed to load master data.";
    }
}

async function checkHealth() {
    try {
        const res = await fetch(API + "/system/health");
        const data = await res.json();
        document.getElementById("health-status").innerText = data.status;
        document.getElementById("health-buffer").innerText = data.buffer_size;
        document.getElementById("health-file").innerText = data.packageData_exists ? "LOADED" : "ERROR";
        document.getElementById("health-file").style.color = data.packageData_exists ? "#8ACE00" : "#FF69B4";
    } catch (e) {
        document.getElementById("health-status").innerText = "OFFLINE";
    }
}

async function fetchBuffer() {
    const res = await fetch(API + "/warehouse/buffer");
    if (!res.ok) throw new Error("Failed to fetch buffer");
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
    if (!res.ok) throw new Error("Failed to fetch log");
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
        showToast("Registration failed: Missing data", "failed");
        return;
    }

    try {
        const res = await fetch(API + "/shipment/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ id, destination: addr })
        });
        const msg = await res.text();
        showToast(msg, res.ok ? "success" : "failed");
        
        document.getElementById("shipId").value = "";
        document.getElementById("address").value = "";
        refreshAll();
    } catch (e) {
        showToast("Registration failed: Server error", "failed");
    }
}

async function step1() {
    try {
        const res = await fetch(API + "/warehouse/step1-to-queue", { method: "POST" });
        const msg = await res.text();
        showToast(msg, res.ok ? "success" : "failed");
        fetchBuffer();
    } catch (e) {
        showToast("Step 1 failed", "failed");
    }
}

async function step2() {
    try {
        const res = await fetch(API + "/warehouse/step2-to-stack", { method: "POST" });
        const msg = await res.text();
        showToast(msg, res.ok ? "success" : "failed");
    } catch (e) {
        showToast("Step 2 failed", "failed");
    }
}

async function step3() {
    try {
        const res = await fetch(API + "/warehouse/step3-to-log", { method: "POST" });
        const msg = await res.text();
        showToast(msg, res.ok ? "success" : "failed");
        fetchLog();
    } catch (e) {
        showToast("Step 3 failed", "failed");
    }
}

async function processAll() {
    showToast("STARTING AUTO-PROCESS...", "success");
    try {
        const res = await fetch(API + "/warehouse/process-all", { method: "POST" });
        const msg = await res.text();
        showToast(msg, res.ok ? "success" : "failed");
        refreshAll();
    } catch (e) {
        showToast("Process All failed", "failed");
    }
}

async function searchAddress() {
    const q = document.getElementById("search-query").value;
    if (!q) return;
    try {
        const res = await fetch(API + "/address/search?key=" + q.toLowerCase());
        const text = await res.text();
        document.getElementById("search-output").innerText = text;
        showToast("SEARCH COMPLETED: " + q.toUpperCase(), "success");
    } catch (e) {
        showToast("SEARCH FAILED", "failed");
    }
}

async function getShortestPath() {
    try {
        const res = await fetch(API + "/route/shortest?start=Meydan");
        const text = await res.text();
        document.getElementById("route-output").innerHTML = `<pre>${text}</pre>`;
        showToast("Shortest path calculated successfully", "success");
    } catch (e) {
        showToast("Calculation failed", "failed");
    }
}

async function getMST() {
    try {
        const res = await fetch(API + "/route/mst");
        const text = await res.text();
        document.getElementById("route-output").innerHTML = `<pre>${text}</pre>`;
        showToast("MST calculated successfully", "success");
    } catch (e) {
        showToast("Calculation failed", "failed");
    }
}

// Initial load
refreshAll();
