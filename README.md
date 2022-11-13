# PRUEBAS CON KUBERNETES

## PRUEBAS CON DEPLOYMENTS

### Modo declarativo

Creando un deply a partir de un .yaml

```bash
kubectl apply -f deploy_nginx.yaml
```
```bash
kubectl get pods
```
```bash
kubectl get deploy
```

Prueba de escalamiento del despliegue 
```bash
kubectl scale deploy nginx-d --replicas=3
```
