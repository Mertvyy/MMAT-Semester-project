# LOAD404 CARGO - Operations Command

## Critical Deployment Instructions for Render:

Your site is currently failing because the **Java Backend is not being started**. 

Please ensure your Render service is configured as a **Web Service** (NOT a Static Site) with these settings:

1.  **Runtime**: `Java` (Select Java 17 if asked)
2.  **Build Command**: `mvn clean install`
3.  **Start Command**: `java -jar target/cargo-1.7.jar`
4.  **Environment Variables**: 
    - `PORT` = `8080` (Render usually sets this automatically)

### Current Version: 1.7 (BRAT_FIX)
- Added Master Inventory scrollable window.
- Added Backend Connectivity Check.
- Added Automatic File Recovery for Render.