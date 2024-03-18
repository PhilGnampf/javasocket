# Use the official OpenJDK 11 base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /workspace/client

# Copy the source code to the container
COPY . .

# Copy the application source code to the container
RUN javac -d . RPSGameClient.java

# Set the entry point to run your Java application
CMD ["java", "client.RPSGameClient"]
