## Critical Deployment Instructions for Render (DOCKER):

Since Java is not showing in your list, we will use **Docker**.

1.  **New Web Service**: Connect your GitHub.
2.  **Runtime**: Select **Docker**.
3.  **Everything Else**: Keep as default. Docker will automatically use the `Dockerfile` I just added to build and run your Java app.
4.  **Environment Variables**: 
    - `PORT` = `8080` (Render usually sets this automatically)

### Current Version: 1.7 (BRAT_FIX)
- Added Master Inventory scrollable window.
- Added Backend Connectivity Check.
- Added Automatic File Recovery for Render.