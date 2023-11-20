# SGeoKubernetes

## Kubernetes Porject App

RestAPI that uses MaxMind's GeoIP2 Enterprise database to find geolocation by IP adress.
->shows data by IP from local MySQL db if avaliable
-> if not avaliable then uses GeoIP2 to get data, saves to local DB and then shows to client

2 options for deployment:
1) docker-compose file

2) mysql-deployment.yaml+ app-deployment.yaml to deploy SpringBoot + MySQL to Kubernetes (Minikube)

P.S.
-MySQL + Java 8
-Flyway (add ConfigMap, Secret, Job)
