pipeline {
    agent any

    stages {
        stage('Maven Clone') {
            steps {
                script {
                    retry(3) {
                        // Cloner le dépôt Git
                        git(
                            branch: 'chbinouyosser_5arctic4_G6',
                            url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                        )
                    }
                }
            }
        }

        stage('JUnit Test') {
            steps {
                script {
                    // Exécuter les tests JUnit
                    sh 'mvn clean install -DskipTests=false'
                    sh 'mvn test -Dspring.profiles.active=test'
                }
            }
        }


        stage('Deploy to Nexus') {
            steps {
                script {
                    // Déployer le projet sur Nexus
                    sh 'mvn clean deploy -DskipTests'
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    // Arrêter tous les conteneurs en cours d'exécution
                    sh 'docker compose down || true'
                    // Construire les images Docker
                    sh 'docker compose build'
                    // Démarrer les conteneurs Docker
                    sh 'docker compose up -d'
                }
            }
        }
    }

    post {
        success {
            echo 'Le déploiement a réussi !'
        }
        failure {
            echo 'Le déploiement a échoué. Veuillez vérifier les logs pour plus de détails.'
        }
    }
}
