pipeline {
    agent any

    stages {
        stage('Clean up') {
            steps {
                deleteDir() // Nettoie le workspace avant de commencer
            }
        }

        stage('Checkout') {
            steps {
                script {
                    retry(3) { // Réessaye jusqu'à 3 fois en cas d'erreur de connexion
                        echo "Cloning repository from branch 'yosserbacknew'"
                        git url: 'https://github.com/Nawres-code/DevOpsBackend.git', branch: 'yosserbacknew'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs() // Nettoie le workspace après exécution du pipeline
        }
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}
