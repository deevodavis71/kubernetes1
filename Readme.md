Testing
=======

mvn spring-boot:run

Build
=====

mvn clean
mvn versions:set -DnewVersion=0.0.2-SNAPSHOT
mvn package

Docker Build
============

docker build -t sjd300671/kubernetes1 .

Testing Docker Build
====================

docker run -p 8080:8080 -t --name kub1 --rm sjd300671/kubernetes1

Pushing to Docker Hub
=====================

docker login
docker push sjd300671/kubernetes1

Installing Kubernetes
=====================

brew update && brew install kubectl && brew cask install docker minikube virtualbox

docker --version                # Docker version 1.12.3, build 6b644ec
docker-compose --version        # docker-machine version 0.8.2, build e18a919
docker-machine --version        # docker-compose version 1.8.1, build 878cff1
minikube version                # minikube version: v0.12.2
kubectl version --client        # Client Version: version.Info{Major:"1",

https://gist.github.com/kevin-smets/b91a34cea662d0c523968472a81788f7 

Starting the cluster
====================

minikube start
minikube dashboard

Deployment to Kubernetes
========================

kubectl create -f deployment.yml 
kubectl describe deployment kubernetes1-deployment
kubectl get pods

Connecting to Pods
==================

kubectl get pods
kubectl exec -it steves-nginx-3419353887-2j4qb -- /bin/bash

Installing Curl (may not work!)
===============================

sudo apt-get update
sudo apt-get install curl

Restarting a Pod
================

kubectl get pods
kubectl delete pod steves-nginx-3419353887-2j4qb --now

Creating a Service
==================

kubectl expose deployment kubernetes1-deployment --type=LoadBalancer --name=my-service

kkuy-service
NAME         CLUSTER-IP   EXTERNAL-IP   PORT(S)          AGE
my-service   10.0.0.30    <pending>     8080:31438/TCP   36s

-- OR -- (try not to use this one)

kubectl expose deployment/kubernetes1-deployment
kubectl get svc kubernetes1-deployment
kubectl describe svc kubernetes1-deployment

-- OR --

kubectl create -f service.NodePort.yml
minikube service kubernetes1-deployment

Testing the Service
===================

NOTE: IP Address 192.168.99.100 is per the url "minikube dashboard --url"

kubectl describe svc kubernetes1-deployment
curl 192.168.99.100:31798/api/sayHello/World

Update the Docker Images
========================

kubectl describe pods
<returns containers>

kubectl set image deployment/kubernetes1-deployment kubernetes1=sjd300671/kubernetes1:latest

Scale the deployment
====================

kubectl scale deployments/kubernetes1-deployment --replicas=4
kubectl get deployments
kubectl scale deployments/kubernetes1-deployment --replicas=3
kubectl get deployments






Useful References
=================
https://www.brosinski.com/post/deploying-spring-boot-app-kubernetes/
https://kubernetes.io/docs/tutorials/stateless-application/expose-external-ip-address/
https://www.dontpanicblog.co.uk/2016/07/08/building-tagging-and-pushing-docker-images-with-maven/
https://spring.io/guides/gs/service-registration-and-discovery/
https://fabric8.io/guide/javaLibraries.html

Eureka/Zuul
-----------
http://ryanjbaxter.com/2015/09/21/building-cloud-native-apps-with-spring-part-2/

Debug
-----
http://blog.netgloo.com/2014/12/11/logging-in-spring-boot/