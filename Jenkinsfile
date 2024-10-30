pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git url: 'https://github.com/Nawres-code/DevOpsBackend.git', branch: 'LakhalBackDevOps'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonar') { // Replace 'sonar' with your SonarQube server name if different
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
                // Deploy the artifact to Nexus repository
                sh 'mvn clean deploy -DskipTests'
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
