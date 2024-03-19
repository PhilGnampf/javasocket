# Containered Rock-Paper-Scissors
Working Docker Engine installation needed!

## Auto Setup (Powershell)
```bash
# initializes network, images and containers
# existing containers will be rebuild
.\startup.ps1
. startup.sh

# cleans network, images and containers
.\cleanup.ps1
. cleanup.sh
```

## Manual Setup 

### Docker Network
```bash
# create docker network
docker network create rps_network
```

### Docker Build
```bash
# build server image
docker build -t rpsserver server

# build client image
docker build -t rpsclient client
```

### Docker Run
```bash
# Start server container
docker run --network rps_network --name rps_server_container -p 8888:8888 rpsserver

# Start interactive client container
docker run -it --network rps_network --name rps_client_container rpsclient
```

### IP and Port
```bash
IP:     rps_server_container
Port:   8888
```

![Diagram](ServerSocketJava.drawio.svg)