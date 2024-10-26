pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                // Clone the specified branch of the repository
                git branch: 'LakhalBackDevOps', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
            }
        }
        stage('Build and Deploy') {
            steps {
                script {
                    // Build Docker images using docker-compose
                    sh 'docker-compose build'

                    // Bring up the services defined in docker-compose.yml
                    sh 'docker-compose up -d'
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
