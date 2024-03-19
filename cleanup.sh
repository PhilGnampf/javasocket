# Stop the containers
docker stop rps_server_container
docker stop rps_client_container

# Remove the containers
docker rm rps_server_container
docker rm rps_client_container

# Remove the images
docker rmi rpsserver
docker rmi rpsclient

# Remove the network
docker network rm rps_network