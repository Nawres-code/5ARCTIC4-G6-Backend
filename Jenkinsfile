
pipeline {
    agent any

    tools {
        jdk 'JAVA_HOME' // Ensure that 'JAVA_HOME' is defined in Jenkins
        maven 'M2_HOME' // Ensure that 'M2_HOME' is defined in Jenkins
    }

    environment {
        SONARQUBE_TOKEN = credentials('sonarqube-token') // Use the correct credentials ID
        SONARQUBE_URL = 'http://192.168.56.50:9000' // Your SonarQube server URL

    }


    stages {
        stage('Checkout') {
            steps {
                // Checkout the specified branch from Git
                git(
                    url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git',
                    branch: 'chaima',
                    credentialsId: 'github-credentials'
                )
            }
        }

        stage('Build') {
            steps {
                // Build the project with Maven
                sh 'mvn clean install'
            }
        }

        stage('JUNIT/MOCKITO') {
            steps {
                // Test the project
                sh 'mvn test'
            }
        }


            stage('Build and Deploy to Nexus') {
            steps {
                // Deploy the artifact to Nexus repository
                sh 'mvn clean deploy -DskipTests'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                // Run SonarQube analysis
                sh """
                    mvn sonar:sonar \
                        -Dsonar.projectKey=DevOps:test \
                        -Dsonar.host.url=${SONARQUBE_URL} \
                        -Dsonar.login=${SONARQUBE_TOKEN} \
                        -Dsonar.ws.connectionTimeout=180000 \
                        -Dsonar.ws.readTimeout=180000
                """
                }
            }



    }

    post {
        success {
            echo 'Nexus completed successfully.'
        }
        failure {
            echo 'Nexus  failed.'
        }
    }
}
