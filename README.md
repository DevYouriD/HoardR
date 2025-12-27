# HoardR Pokemon TCG Collection Tracker

---

## Table of Contents
- [Additional Features](#additional-features)
- [Deployment Strategy](#deployment-strategy)
- [Tech Stack](#tech-stack)
- [Links](#links)

---

### Additional Features
  - **Application**
    - Link to card data on top of Expansion Set Page
    - Implement Card **Search** and **Filtering**
    - Total amount of cards value for sets
    - How was the card collected value for sets (e.g. pulled or bought as single)
    - If the card was bought as single, for how much was it bought
    - Option to upload multiple cards at once (via csv?)
    - Fix fallback images and icons
    - Dedicated “cards I still need” query/page
    - Export Collection to:
      - JSON
      - CSV
  
  
  - **Price Tracking**
    - Implement Price Tracking
    - Scheduled background job to check price updates
    - API client or scraper for Pokellector / TCGPlayer / Cardmarket
    - Store historical price data (time-series in Mongo)
    - Visual price trends (↑ / ↓)
    
  
  - **Frontend**
    - Abstract frontend into separate application (Next.js?)
    

  - **Security**
    - Protect endpoints with spring security (JWT)
    

  - **Analytics**
    - Total collected
    - Total collected value
    - Total value of missing cards
    - Completion percentage per series or series
    - Notifications when card price crosses thresholds
    

---

### Deployment strategy

1. Build Docker image with GitHub Actions
2. Push image to Docker Registry
3. SSH into your Oracle VM
4. Pull the new image
5. Restart application container

---

### Tech Stack
- Spring Boot
- GraphQL
- MongoDB
- Thymeleaf frontend
- JWT-protected endpoints (static token)

---

### Links
MongoDB UI:
```text
http://localhost:8081
```

GraphQl Playground:
```text
http://localhost:8080/graphiql
```

[Example Queries (CRUD Operations)](documentation/Example_GraphQL_Queries.md)

GraphQl Crud tutorial: https://www.youtube.com/watch?v=AgSO3rcSuHE  
Spring GraphQl Setup tutorial: https://spring.io/guides/gs/graphql-server

---

![image info](https://external-preview.redd.it/utVbTmdvBvpDHFn3dZ8LxDoeZKLD0lJtQNEYBPVRs-M.jpg?width=1080&crop=smart&auto=webp&s=b4356675840e6734ecfdf9c89682f719fb880b10)