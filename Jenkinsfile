pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                retry(3) { // Retries the Git clone up to 3 times in case of failure
                    git(
                        branch: 'yosserbacknew',
                        url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                    )
                }
            }
        }
        
         stage('Build and Deploy') {
            steps {
                script {
                    // Ensure Docker is running before building
                    // Stop and remove any existing containers with the same name
                    sh 'docker compose down || true'

                    // Build Docker images using docker-compose
                    sh 'docker compose build'

                    // Bring up the services defined in docker-compose.yml
                    sh 'docker compose up -d'
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
