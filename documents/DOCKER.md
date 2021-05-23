## Docker

mvn package -Dmaven.test.skip=true  

docker build -t spring-boot-h2db-cache .  
docker images  

docker run -p 8080:8080 --name spring-boot-h2db-cache spring-boot-h2db-cache  
docker ps  
docker ps -a  

docker stop spring-boot-h2db-cache  

docker rm spring-boot-h2db-cache  
docker image rm -f spring-boot-h2db-cache  

docker tag spring-boot-h2db-cache anantha/spring-boot-h2db-cache:latest  
docker push anantha/spring-boot-h2db-cache:latest  

