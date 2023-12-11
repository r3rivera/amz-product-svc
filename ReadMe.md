## Ryan Rivera
## Tech Stack
* GraphQL
* Local Jenkinsfile
* Local Docker Image Builder

## Creating Self-Signed Certificates
```
openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365
openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"