# HoardR Collection Tracker

ToDo:
- Add registration / login / authenticated user pages
- Add proper data models
- Create test data
- Implement MongoDb
- Protect endpoints with spring security (JWT)


- Admin Page
  - Add series
  - Add Expansion Sets to Series
  - Add Cards to Expansion Sets
  - Edit every entity
  - Delete every entity

- Additional Ideas
  - Link to card data on top of Expansion Set Page
  - Total amount of cards value for sets
  - How was the card collected value for sets (e.g. pulled or bought as single)
  - If the card was bought as single, for how much was it bought
  - Option to upload multiple cards at once (via csv?)

GraphQl Crud tutorial: https://www.youtube.com/watch?v=AgSO3rcSuHE  
Spring GraphQl Setup tutorial: https://spring.io/guides/gs/graphql-server  

MongoDB UI:
```text
http://localhost:8081
```

GraphQl Playground:
```text
http://localhost:8080/graphiql
```

[Example Queries (CRUD Operations)](GraphQL_Queries.md)

---

## Project Baseline
Initial application plan:

### **Tech Stack**
- Spring Boot
- GraphQL
- MongoDB
- Thymeleaf frontend
- JWT-protected endpoints (static token)

### **Initial Goal**
- Convert Pokémon card markdown expansionSet into dedicated application
- CRUD operations for sets and cards
- Track which cards you own
- Display/manage price indications

---

## Expansion Ideas

### **1. Data Import & Management**
- Parse and import your existing markdown expansionSet
- Admin-only endpoint to upload/sync collections
- GraphQL mutations for manual card editing
- Optionally maintain multiple collections or profiles

### **2. Frontend Application (Optional but Valuable)**
- Web UI using React or Next.js
- Apollo Client for GraphQL operations
- Card gallery with:
    - Search
    - Filters (series, rarity, illustrator, price)
    - Collected/missing badges
- Set overview pages

### **3. Price Tracking Features**
- Scheduled background job to check price updates
- API client or scraper for Pokellector / TCGPlayer / Cardmarket
- Store historical price data (time-series in Mongo)
- Visual price trends (↑ / ↓)

### **4. Collection Analytics**
- Total expansionSet value
- Total value of missing cards
- Completion percentage per series or series
- Stats by:
    - Illustrator
    - Rarity
    - Pokémon type
- “Top 10 most expensive missing cards.”

### **5. Image Handling Enhancements**
- Cache external images locally or in S3
- Automatic thumbnail generation
- Fallback images if a URL breaks
- Optional offline mode for images

### **6. Search & Filtering Tools**
- Full-text search (MongoDB text index)
- Filters for:
    - Illustrator
    - Set
    - Rarity
    - Price range
    - Collected / missing
- Dedicated “cards I still need” query/page

### **7. Mobile or Desktop Client (Optional)**
- React Native mobile app
- Barcode/QR scanning to update expansionSet
- Electron desktop app for a standalone PC client

### **8. Security & Deployment**
- Static JWT validation for all API requests
- Dockerized backend + MongoDB
- Deployment options:
    - Fly.io
    - Render
    - DigitalOcean
    - Home server / Raspberry Pi

### **9. Quality of Life Improvements**
- Export expansionSet to:
    - Markdown
    - JSON
    - CSV
- Auto-generate markdown tables like your original format
- Notifications when card price crosses thresholds
- Illustrator details page with statistics

---

## Ultimate Scope Summary
A personal, feature-rich expansionSet tracker that can grow into:

- A GraphQL-driven card database
- Automated price tracker
- Beautiful gallery UI
- Analytics + insights
- Optional mobile access
- Secure private API

Let me know if you'd like:
- A MongoDB schema
- A GraphQL schema
- A sample Spring Boot project structure
- Or code scaffolding to get started!
