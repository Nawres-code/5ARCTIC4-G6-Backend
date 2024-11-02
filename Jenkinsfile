pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials') 
        DOCKER_IMAGE_BACK = "nawreslakhal/devops1:backend"  
        DOCKER_IMAGE_FRONT = "nawreslakhal/devops1:frontend" 
        DOCKER_IMAGE_MYSQL = "nawreslakhal/devops1:mysql"     
    }
    stages {
        stage('Clone Repositories') {
            parallel {
                stage('Clone Frontend Repo') {
                    steps {
                        dir('frontend') {
                            git branch: 'LakhalDevOpsFrontend', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Frontend.git'
                        }
                    }
                }
                stage('Clone Backend Repo') {
                    steps {
                        dir('backend') {
                            git branch: 'LakhalBackDevOps', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                        }
                    }
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

        stage('Build Docker Images') {
            steps {
                script {
                    dir('backend') {
                        sh "docker build -t $DOCKER_IMAGE_BACK ."
                    }
                    dir('frontend') {
                        sh "docker build -t $DOCKER_IMAGE_FRONT ."
                    }
                }
            }
        }

        stage('Pull and Tag MySQL Image') {
            steps {
                script {
                    sh "docker pull mysql:latest"
                    sh "docker tag mysql:latest $DOCKER_IMAGE_MYSQL"
                }
            }
        }

        stage('Push Docker Images to Docker Hub') {
    steps {
        script {
            // Using 'withCredentials' for accessing the credentials
            withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                sh "docker push $DOCKER_IMAGE_BACK"
                sh "docker push $DOCKER_IMAGE_FRONT"
                sh "docker push $DOCKER_IMAGE_MYSQL"
                sh "docker logout"
            }
        }
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
            echo 'Images pushed to Docker Hub successfully and services deployed!'
        }
        failure {
            echo 'Image push or deployment failed!'
        }
    }
}
