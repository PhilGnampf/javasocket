# Use the official OpenJDK 11 base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /workspace/server

# Copy the source code to the container
COPY . .

# Compile the Java source files and organize them into packages
RUN javac -d . RPSGameServer.java RPSGameHandler.java

# Expose the port your server listens on
EXPOSE 8888

# Set the entry point to run your Java application
CMD ["java", "server.RPSGameServer"]
