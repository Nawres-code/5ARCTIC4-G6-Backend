pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the Git repository
                git url: 'https://github.com/Nawres-code/DevOpsBackend.git', branch: 'yosserbacknew'
            }
        }

        stage('Build') {
            steps {
                // Build the application using Maven
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy to Nexus') {
            steps {
                // Deploy the application to Nexus with credentials
                withCredentials([usernamePassword(credentialsId: 'your-credential-id', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    // Set the Maven settings file path if needed
                    sh '''
                        mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8082/repository/maven-releases/ \
                        -Dmaven.username=$NEXUS_USERNAME -Dmaven.password=$NEXUS_PASSWORD
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}
