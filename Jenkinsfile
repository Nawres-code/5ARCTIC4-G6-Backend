pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                script {
                    // Clone the repository
                    git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git', branch: 'LakhalBackDevOps'
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    // Stop any running containers and remove them
                    sh 'docker compose down || true'
                    // Build the Docker images
                    sh 'docker compose build'
                    // Start the containers in detached mode
                    sh 'docker compose up -d'
                }
            }
        }

        stage('Configure MySQL Metrics') {
            steps {
                script {
                    // Reload Prometheus config to pick up the MySQL exporter
                    sh 'docker exec prometheus sh -c "kill -HUP 1"' // Reload Prometheus config
                    echo 'MySQL metrics configuration completed in Prometheus.'
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Deployment Failed!'
        }
    }
}