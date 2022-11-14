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

## Crear un recurso de PVC

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

## Deployment
### Base de datos

```python
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mydb-deployment
spec:
  replicas: 1
  selector:
    matchLabels:
      env: production-db
  template:
    metadata:
      name: mydb-pod
      labels:
        env: production-db
    spec:
      volumes:
        - name: db-vol
          persistentVolumeClaim:
            claimName: db-pvc
      containers:
      - name: database
        image: ragh19/phpproject:mysql_v1
        volumeMounts:
          - mountPath: /var/lib/mysql
            name: db-vol
```

### PHP

```python
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myphp-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      env: production-frontend
  template:
    metadata:
      name: myfrontend-pod
      labels:
        env: production-frontend
    spec:
      volumes:
        - name: front-vol
          persistentVolumeClaim:
            claimName: frontend-pvc
      containers:
      - name: frontend
        image: ragh19/phpproject:web_v1
        volumeMounts:
          - mountPath: /var/www/html
            name: front-vol
```

## Servicios
### Creando Servicio para Database

```python
apiVersion: v1
kind: Service
metadata:
  name: mydb-service
spec:
  type: NodePort
  ports:
    - targetPort: 3306
      port: 3306
      nodePort: 30008
  selector:
    env: production-db
```

Aquí el servicio de base de datos está expuesto al puerto 30008. Puede acceder a este servicio de base de datos por IP y Puerto teniendo el nombre de usuario de la base de datos como root y la contraseña es redhat.


### Servicio para PHP

```python
apiVersion: v1
kind: Service
metadata:
  name: myphp-service
spec:
  type: NodePort
  ports:
    - targetPort: 80
      port: 80
      nodePort: 30009
  selector:
    env: production-frontend
```
