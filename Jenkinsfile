pipeline {
    agent any
    stages {
        stage('Build Backend') {
            steps {
                script {
                    // Build the backend Docker image
                    docker.build('nawreslakhal/backend')
                }
            }
        }
        stage('Run MySQL and Backend') {
            steps {
                script {
                    // Ensure network exists
                    docker.network.create('app-network')

                    // Run MySQL container
                    docker.image('mysql:latest').run('--network app-network --name mysql-container -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=mydb -d')

                    // Run Backend container in the same network
                    docker.image('nawreslakhal/backend').run('--network app-network --name backend-container -p 8080:8080 -d')
                }
            }
        }
    }
}
