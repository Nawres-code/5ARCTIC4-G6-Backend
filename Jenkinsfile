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
