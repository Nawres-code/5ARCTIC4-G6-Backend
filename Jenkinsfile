pipeline {
    agent any

    stages {
        stage('Clean up') {
            steps {
                // Clean up workspace before starting the pipeline
                deleteDir()
            }
        }
        stage('Checkout') {
            steps {
                git url: 'https://github.com/Nawres-code/DevOpsBackend.git', branch: 'yosserbacknew'
            }
        }
    }

    post {
        always {
            // Always clean the workspace after the pipeline execution
            cleanWs()
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
