# Check if the network exists and create it if not
if [ -z "$(docker network ls --filter name=rps_network -q)" ]; then
    docker network create rps_network
fi

# Build the Docker images
docker build -t rpsserver server
docker build -t rpsclient client

# Check if the server container exists, if so, stop and remove it
if [ -n "$(docker ps --filter name=rps_server_container -a -q)" ]; then
    docker stop rps_server_container
    docker rm rps_server_container
fi

# Run the server container
docker run -d --network rps_network --name rps_server_container -p 8888:8888 rpsserver

# Check if the client container exists, if so, stop and remove it
if [ -n "$(docker ps --filter name=rps_client_container -a -q)" ]; then
    docker stop rps_client_container
    docker rm rps_client_container
fi

# Run the client container
docker run -it --network rps_network --name rps_client_container rpsclient