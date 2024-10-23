pipeline {
    agent any

    tools {
        jdk 'JDK20'  
        maven 'Maven_3.6.3' 
    }

    environment {
        GIT_REPO = 'https://github.com/Nawres-code/DevOpsBackend.git'
    }

    stages {
        stage('Cloner le dépôt') {
            steps {
                git branch: 'main', url: "${GIT_REPO}"
            }
        }
        stage('Construire le projet') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Exécuter les tests') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Déployer') {
            steps {
                echo 'Déploiement...'
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
        success {
            echo 'Pipeline exécuté avec succès.'
        }
        failure {
            echo 'Le pipeline a échoué.'
        }
    }
}
