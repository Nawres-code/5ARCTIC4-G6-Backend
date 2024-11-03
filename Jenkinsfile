pipeline {
    agent any

    stages {
        stage('Maven Clone') {
            steps {
                script {
                    // Retry cloning the repository if it fails
                    retry(3) {
                        git(
                            branch: 'chbinouyosser_5arctic4_G6',
                            url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                        )
                    }
                }
            }
        }

        stage('JUnit Test') {
            steps {
                script {
                    // Build the project and run tests
                    sh 'mvn clean install -DskipTests=false'
                    // Execute tests with the specified Spring profile
                    sh 'mvn test -Dspring.profiles.active=test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Retrieve the token from Jenkins credentials
                    def sonarToken = credentials('sonarqube-token')
                    // Define the SonarQube command without parameters in the environment
                    withSonarQubeEnv('sq1') {
                        sh "mvn clean verify sonar:sonar -Dsonar.projectKey=DevOpsBackend -DskipTests -Dsonar.login=${sonarToken}"
                    }
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    // Deploy the project to Nexus
                    sh 'mvn clean deploy -DskipTests'
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    // Stop any running containers, if any
                    sh 'docker compose down || true'
                    // Build Docker images
                    sh 'docker compose build'
                    // Start Docker containers
                    sh 'docker compose up -d'
                }
            }
        }
    }

    post {
        success {
            echo 'Le déploiement a réussi !'
        }
        failure {
            echo 'Le déploiement a échoué. Veuillez vérifier les logs pour plus de détails.'
        }
    }
}
