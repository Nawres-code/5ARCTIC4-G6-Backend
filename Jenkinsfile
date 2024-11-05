pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE_BACK = "firassaafi/firassaafi:myback"
        DOCKER_IMAGE_MYSQL = "firassaafi/firassaafi:mysql"
    }
    stages {
        stage('Clone Backend Repo') {
            steps {
                dir('myback')
                {  retry(3)
                 {
                    git branch: 'SaafiFiras-5ARCTIC4-G6', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                 }
                }
            }
        }
   /*      stage('Pull and Tag MySQL Image') {
                    when { expression { env.BUILD_MYSQL_IMAGE == 'true' } }
                    steps {
                        script {
                            sh "docker pull mysql:latest"
                            sh "docker tag mysql:latest $DOCKER_IMAGE_MYSQL"
                        }
                    }
                } */

        stage('Build Backend') {
            steps {
                dir('myback') {
                    script {
                        sh 'mvn clean package -DskipTests' // Build the backend, skipping tests
                    }
                }
            }
        }

        stage('Docker Compose Up') {
                            steps {
                                echo 'Starting Docker Compose...'
                                script {
                                    sh 'docker build -t firassaafi/firassaafidocker:myback .'
                                    // Now that the JAR is available in target/, Docker can build the image
                                    sh 'docker-compose up -d'
                                }
                            }
                        }
         stage('Unit test') {
                     steps {
                            dir ('myback') {
                                def installCommand = 'mvn -Dtest=com.Parking.GestionParking.services.GestionParkingApplicationTests test'

                                sh installCommand
                            }
                        }
        }

    }

    post {
        success {
            echo 'Backend repository cloned and built successfully!'
        }
        failure {
            echo 'Failed to clone the backend repository or build it!'
        }
    }

}
