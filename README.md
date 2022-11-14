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

# Aplicacion de prueba

ERROR: Unable to connect to the server: dial tcp 127.0.0.1:6443: connectex: No connection could be made because the target machine actively refused it.

En windows para corregir este error se establecen valores para el proxy 

```bash
set HTTP_PROXY=http://<proxy hostname:port>
set HTTPS_PROXY=https://<proxy hostname:port>
set NO_PROXY=localhost,127.0.0.1,10.96.0.0/12,192.168.59.0/24,192.168.49.0/24,192.168.39.0/24
```

Luego inicio el minikube

```bash
minikube start -- vm-driver=virtualbox
```

Crear un recurso de PVC

### Mysql

```python
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: db-pvc
  labels:
    name: dbpvc1
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 2Gi
```

### PHP

```python
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: frontend-pvc
  labels:
    name: frontendpvc1
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 2Gi
```
