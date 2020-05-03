#!/bin/sh
helm install --name helm --namespace devops -f 03-chartmuseum-conf.yaml stable/chartmuseum

helm plugin install https://github.com/chartmuseum/helm-push

helm lint 02-charts/backend-scm/
helm push 02-charts/backend-scm/ questcode

helm lint 02-charts/backend-user/
helm push 02-charts/backend-user/ questcode

helm lint 02-charts/frontend/
helm push 02-charts/frontend/ questcode

helm repo update
