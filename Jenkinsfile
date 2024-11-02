pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('github-credentials') // Docker Hub credentials ID in Jenkins
        DOCKER_IMAGE_BACK = "nawreslakhal/devops1-back"
        DOCKER_IMAGE_FRONT = "nawreslakhal/devops1-front"
        DOCKER_IMAGE_MYSQL = "nawreslakhal/devops1-mysql"
    }
    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git', branch: 'LakhalBackDevOps'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=DevOpsBackend -DskipTests'
                }
            }
        }

        stage('Test') {
            steps {
                sh '''
                    mvn clean install -DskipTests=false
                    mvn test -Dspring.profiles.active=test
                '''
            }
        }

        stage('Build and Deploy to Nexus') {
            steps {
                sh 'mvn clean deploy -DskipTests'
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

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    sh 'docker compose down || true'
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
