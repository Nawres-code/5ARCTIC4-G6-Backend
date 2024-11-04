pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE_BACK = "firassaafi/firassaafi:backend"
        DOCKER_IMAGE_MYSQL = "firassaafi/firassaafi:mysql"
    }
    stages {
        stage('Clone Backend Repo') {
            steps {
                dir('backend') {
                    git branch: 'SaafiFiras-5ARCTIC4-G6', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    script {
                        sh 'mvn clean package -DskipTests' // Build the backend, skipping tests
                    }
                }
            }
        }
        stage('Unit test') {
                    steps {
                        // Test the project
                        sh 'mvn test'
                    }
        }
    }
    post {
        success {
            echo 'Backend repository cloned and built successfully!'
        }
        failure {
            echo 'Failed to clone the backend repository or build it!'
        }
    }
}
