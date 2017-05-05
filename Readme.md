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

kubectl get services my-service
NAME         CLUSTER-IP   EXTERNAL-IP   PORT(S)          AGE
my-service   10.0.0.30    <pending>     8080:31438/TCP   36s

curl 192.168.99.100:31438/api/sayHello/World

-- OR -- (try not to use this one)

kubectl expose deployment/kubernetes1-deployment
kubectl get svc kubernetes1-deployment
kubectl describe svc kubernetes1-deployment

-- OR --

kubectl create -f service.yml
minikube service kubernetes1-deployment