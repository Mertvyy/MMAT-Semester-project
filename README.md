## Critical Deployment Instructions for Render:

**ERROR 127 FIX**: If you see "Exited with status 127", it means you have not selected the **Java Runtime**.

1.  **Environment**: Go to Render Dashboard -> Settings -> **Runtime**.
2.  **Change to**: **Java** (NOT Static Site).
3.  **Build Command**: `mvn clean install`
4.  **Start Command**: `java -jar target/cargo-1.7.jar`
4.  **Environment Variables**: 
    - `PORT` = `8080` (Render usually sets this automatically)

### Current Version: 1.7 (BRAT_FIX)
- Added Master Inventory scrollable window.
- Added Backend Connectivity Check.
- Added Automatic File Recovery for Render.