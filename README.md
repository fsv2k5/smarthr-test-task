# smart hr app

1. install Postgres or run DB with Docker
docker run --name smarthr-postgres -e POSTGRES_PASSWORD=password -d -p 54322:54322 postgres:alpine

2. enable annotation processing in IDEA

3. config yaml for DB connection or change profile to H2

4. fix @Type for BaseEntity (Postgres only)

5. install Postman for using REST API
    import SmartHr.postman_collection.json file into postman
    
6. run application