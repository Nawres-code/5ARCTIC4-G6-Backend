pipeline {
    agent any
    stages {
        stage('Maven Clone') {
            steps {
                retry(3) { 
                    git(
                        branch: 'yosserbacknew',
                        url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                    )
                }
            }
        }
                stage('JUnit Test') {
            steps {
                sh '''
                    mvn clean install -DskipTests=false
                    mvn test -Dspring.profiles.active=test
                '''
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sq1') { 
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=DevOpsBackend -DskipTests'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
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
