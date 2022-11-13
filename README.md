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

## PRUEBAS CON SERVICIOS

Creando un servicio de tipo Nodeport

```bash
kubectl create deployment apache1 --httpd
```

Ver los detalles del componente creado

```bash
kubectl get all
```
```bash
kubectl expose deploy apache1 --port=80 --type=Nodeport
```

Listar los servicios creados
```bash
kubectl get svc
```

Si ingresamos en la ip local: 192.168.59.100:32150
