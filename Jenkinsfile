pipeline {
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                // Nettoyer l'espace de travail avant de démarrer le pipeline
                cleanWs()
            }
        }
        stage('Checkout') {
            steps {
                // Clone le dépôt GitHub et vérifie la branche 'yosserbacknew'
                git url: 'https://github.com/Nawres-code/DevOpsBackend.git', branch: 'yosserbacknew'
            }
        }
        stage('Build') {
            steps {
                echo 'Build process would go here'
                // Ajoutez ici les étapes de build si nécessaire
            }
        }
        stage('Test') {
            steps {
                echo 'Test process would go here'
                // Ajoutez ici les étapes de test si nécessaire
            }
        }
    }

    post {
        always {
            // Nettoyer l'espace de travail après l'exécution du pipeline
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
