pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git', branch: 'LakhalBackDevOps'
            }
        }


        stage('Build and Deploy') {
            steps {
                script {
                    sh 'docker compose down || true'
                    sh 'docker compose build'
                    sh 'docker compose up -d'
                }
            }
        }
          stage('Configure MySQL Metrics') {
            steps {
                script {
                    // Ensure the MySQL Exporter service is up and configured in Prometheus
                    sh 'docker exec -it prometheus sh -c "kill -HUP 1"' // Reload Prometheus config
                    echo 'MySQL metrics configuration completed in Prometheus.'
                }
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
