pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials') // Docker Hub credentials ID in Jenkins
        DOCKER_IMAGE_BACK = "nawreslakhal/devops1:backend"  // Backend image name
        DOCKER_IMAGE_FRONT = "nawreslakhal/devops1:frontend" // Frontend image name
        DOCKER_IMAGE_MYSQL = "nawreslakhal/devops1:mysql"     // MySQL image name
    }
    stages {
        stage('Clone Repositories') {
            steps {
                // Clone the backend repository
                dir('backend') {
                    git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git', branch: 'LakhalBackDevOps'
                }
                // Clone the frontend repository
                dir('frontend') {
                    git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Frontend.git', branch: 'main' // Adjust branch as necessary
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    // Navigate to the backend directory and build the backend Docker image
                    dir('backend') {
                        sh "docker build -t $DOCKER_IMAGE_BACK ."
                    }
                    // Navigate to the frontend directory and build the frontend Docker image
                    dir('frontend') {
                        sh "docker build -t $DOCKER_IMAGE_FRONT ."
                    }
                }
            }
        }

        stage('Pull and Tag MySQL Image') {
            steps {
                script {
                    // Pull the official MySQL image from Docker Hub
                    sh "docker pull mysql:latest"
                    
                    // Tag the MySQL image with your Docker Hub repository name
                    sh "docker tag mysql:latest $DOCKER_IMAGE_MYSQL"
                }
            }
        }

        stage('Push Docker Images to Docker Hub') {
            steps {
                script {
                    // Log in to Docker Hub
                    sh "echo ${DOCKER_HUB_CREDENTIALS.password} | docker login -u ${DOCKER_HUB_CREDENTIALS.username} --password-stdin"
                    
                    // Push each Docker image to the specific repository
                    sh "docker push $DOCKER_IMAGE_BACK"
                    sh "docker push $DOCKER_IMAGE_FRONT"
                    sh "docker push $DOCKER_IMAGE_MYSQL"
                    
                    // Log out from Docker Hub
                    sh "docker logout"
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    // Stop any running containers and remove them, if they exist
                    sh 'docker compose down || true'
                    // Build the Docker images defined in the docker-compose.yml
                    sh 'docker compose build'
                    // Start the containers in detached mode
                    sh 'docker compose up -d'
                }
            }
        }
    }

    post {
        success {
            echo 'Images pushed to Docker Hub successfully and services deployed!'
        }
        failure {
            echo 'Image push or deployment failed!'
        }
    }
}
