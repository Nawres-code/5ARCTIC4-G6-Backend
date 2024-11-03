pipeline {
    agent any
    stages {
        stage('Maven Clone') {
            steps {
                retry(3) { // Retries the Git clone up to 3 times in case of failure
                    git(
                        branch: 'chbinouyosser_5arctic4_G6',
                        url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                    )
                }
            }
        }
stage('JUnit Test') {
    steps {
        script {
            def installCommand = 'mvn clean install -DskipTests=false'
            def testCommand = 'mvn test -Dspring.profiles.active=test'
            
            sh installCommand
            sh testCommand
        }
    }
}

stage('SonarQube Analysis') {
    steps {
        script {
            def sonarCommand = 'mvn clean verify sonar:sonar -Dsonar.projectKey=DevOpsBackend -DskipTests'
            withSonarQubeEnv('sq1') {
                sh sonarCommand
            }
        }
    }
}

stage('Deploy to Nexus') {
    steps {
        script {
            def deployCommand = 'mvn clean deploy -DskipTests'
            sh deployCommand
        }
    }
}
         stage('Build and Deploy') {
            steps {
                script {
                   
                    sh 'docker compose down || true'
                    sh 'docker compose build'
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
