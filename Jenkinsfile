pipeline {
    agent any 

    environment {
        NEXUS_CREDENTIALS = credentials('nexus-credentials') // Replace with your Jenkins credentials ID
        MVN_HOME = tool name: 'Maven 3.6.3', type: 'maven' // Ensure this matches your Maven installation in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout your source code from the repository
                git url: 'https://github.com/yourusername/yourrepository.git', branch: 'main' // Replace with your repo URL
            }
        }
        
        stage('Build') {
            steps {
                script {
                    // Run Maven clean package
                    sh "'${MVN_HOME}/bin/mvn' clean package"
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                script {
                    // Deploy the JAR file to Nexus
                    sh "'${MVN_HOME}/bin/mvn' deploy"
                }
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
