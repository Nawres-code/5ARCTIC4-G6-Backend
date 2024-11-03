pipeline {
    agent any
    
    stages {
        stage('Maven Clone') {
            steps {
                retry(3) { 
                    git(
                        branch: 'chbinouyosser_5arctic4_G6',
                        url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                    )
                }
            }
        }

        stage('JUnit Test') {
            steps {
                script {
                    // Use mvn clean install to build and test the project
                    sh 'mvn clean install -DskipTests=false'
                    // Execute tests with the specified profile
                    sh 'mvn test -Dspring.profiles.active=test'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    // Retrieve the token from Jenkins credentials
                    def sonarToken = credentials('sonarqube-token')
                    def sonarCommand = "mvn clean verify sonar:sonar -Dsonar.projectKey=DevOpsBackend -DskipTests -Dsonar.login=admin -Dsonar.password=${sonarToken}"
                    
                    // Run SonarQube analysis
                    withSonarQubeEnv('sq1') {
                        sh """${sonarCommand}"""
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
                    // Bring down any running containers safely
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
