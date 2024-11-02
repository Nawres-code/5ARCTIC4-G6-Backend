pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials') // Docker Hub credentials ID in Jenkins
        DOCKER_IMAGE_BACK = "nawreslakhal/devops1-back"
        DOCKER_IMAGE_FRONT = "nawreslakhal/devops1-front"
        DOCKER_IMAGE_MYSQL = "nawreslakhal/devops1-mysql"
    }
    stages {
        stage('Clone') {
            steps {
                // Clone the repository to access Dockerfiles
                git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git', branch: 'LakhalBackDevOps'
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Build Docker images for each service
                    sh "docker build -t $DOCKER_IMAGE_BACK:latest -f Dockerfile-back ."
                    sh "docker build -t $DOCKER_IMAGE_FRONT:latest -f Dockerfile-front ."
                    sh "docker build -t $DOCKER_IMAGE_MYSQL:latest -f Dockerfile-mysql ."
                }
            }
        }

        stage('Push Docker Images to Docker Hub') {
            steps {
                script {
                    // Log in to Docker Hub
                    sh "echo ${DOCKER_HUB_CREDENTIALS.password} | docker login -u ${DOCKER_HUB_CREDENTIALS.username} --password-stdin"
                    
                    // Push each Docker image to Docker Hub
                    sh "docker push $DOCKER_IMAGE_BACK:latest"
                    sh "docker push $DOCKER_IMAGE_FRONT:latest"
                    sh "docker push $DOCKER_IMAGE_MYSQL:latest"
                    
                    // Log out from Docker Hub
                    sh "docker logout"
                }
            }
        }
    }

    post {
        success {
            echo 'Images pushed to Docker Hub successfully!'
        }
        failure {
            echo 'Image push failed!'
        }
    }
}
