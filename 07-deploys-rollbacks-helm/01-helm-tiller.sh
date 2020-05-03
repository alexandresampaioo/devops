curl https://raw.githubusercontent.com/kubernetes/helm/master/scripts/get | bash
#helm init
kubectl apply -f 01-tiller-account.yaml

helm init --service-account tiller

#kubectl patch deployment tiller-deploy -n kube-system --patch "$(cat 01-tiller-patch.yaml)"