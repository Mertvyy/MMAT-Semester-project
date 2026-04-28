import { useState } from "react";

export default function App(){
  const [logs,setLogs]=useState([
    {id:"PKG_KYS_001",dest:"Talas"},
    {id:"PKG_KYS_002",dest:"Belsin"}
  ]);

  return(
    <div className="min-h-screen bg-black flex items-center justify-center p-6">
      <div className="w-full max-w-xl bg-[#0f0f0f] rounded-3xl shadow-xl border border-[#39ff14]/30 p-6">
        <h1 className="text-[#39ff14] text-2xl font-semibold mb-4 tracking-wide">
          Urban Logistics System
        </h1>

        <div className="bg-[#111] rounded-2xl p-4 mb-4 border border-[#39ff14]/20">
          <h2 className="text-[#39ff14] mb-2">Package Queue</h2>
          <div className="space-y-2">
            {logs.map(p=>
              <div key={p.id} className="flex justify-between bg-black/60 p-2 rounded-xl border border-[#39ff14]/10">
                <span className="text-white text-sm">{p.id}</span>
                <span className="text-[#39ff14] text-sm">{p.dest}</span>
              </div>
            )}
          </div>
        </div>

        <div className="flex gap-3">
          <button className="flex-1 bg-[#39ff14] text-black rounded-2xl py-2 font-medium hover:opacity-80">
            Enqueue
          </button>
          <button className="flex-1 border border-[#39ff14] text-[#39ff14] rounded-2xl py-2 hover:bg-[#39ff14]/10">
            Process
          </button>
        </div>
      </div>
    </div>
  )
}
