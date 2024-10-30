pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                retry(3) { // Retries the Git clone up to 3 times in case of failure
                    git(
                        branch: 'yosserbacknew',
                        url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                    )
                }
            }
        }
         stage('Build and Deploy to Nexus') {
            steps {
                // Deploy the artifact to Nexus repository
                sh 'mvn clean deploy -DskipTests'
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
