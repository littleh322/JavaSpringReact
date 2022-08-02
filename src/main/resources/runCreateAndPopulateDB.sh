docker cp ./createAndPopulateDB.sql psql2:/docker-entrypoint-initdb.d/createAndPopulateDB.sql
docker exec -u postgres psql2 psql postgres postgres -f docker-entrypoint-initdb.d/createAndPopulateDB.sql