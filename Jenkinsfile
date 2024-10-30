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
                withCredentials([usernamePassword(credentialsId: '379d5c13-02ec-472d-b621-0ad495454d92', usernameVariable: 'admin', passwordVariable: '223JFT4672')]) {
                    // Set the Maven settings file path if needed
                    sh '''
                        mvn deploy -DskipTests -DaltDeploymentRepository=deploymentRepo::default::http://localhost:8082/repository/maven-releases/ \
                        -Dmaven.username=admin -Dmaven.password=223JFT4672
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
