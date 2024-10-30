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
                // Deploy the application to Nexus directly using the username and password
                sh '''
                    mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8082/repository/maven-releases/ \
                    -Dmaven.username=admin -Dmaven.password=223JFT4672
                '''
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
