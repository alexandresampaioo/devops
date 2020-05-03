kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v1.10.1/src/deploy/recommended/kubernetes-dashboard.yaml

# Criando Service Account e associando permissao 'cluster-admin'
kubectl create serviceaccount kubeadmin -n kube-system 
kubectl create clusterrolebinding kubeadmin-binding --clusterrole=cluster-admin --serviceaccount=kube-system:kubeadmin

kubectl describe sa kubeadmin -n kube-system
kubectl get secret <TOKEN-ID> -n kube-system -o yaml
echo `echo <TOKEN> | base64 --decode`

