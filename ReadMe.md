## Ryan Rivera
## Tech Stack
* Spring Boot with Spring Security
* GraphQL using Spring GraphQL
* Local Jenkinsfile
* Local Docker Image Builder

## Creating Self-Signed Certificates
Used to enable HTTPS in local and development environment
```
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365

openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"
```
## Creating JWT Certificate
Use to generate the JWT
### Creates the Private Key
```
openssl genrsa -out private_key.key 2048
```

### Extract the Public Key from Private Key
``` 
openssl rsa -in private_key.key -pubout -out public_key.pub
```



