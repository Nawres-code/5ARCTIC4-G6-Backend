pipeline {
    agent any
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE_BACK = "nawreslakhal/devops:backend"
        DOCKER_IMAGE_FRONT = "nawreslakhal/devops:frontend"
        DOCKER_IMAGE_MYSQL = "nawreslakhal/devops:mysql"
    }
    stages {
        stage('Clone Repositories') {
            parallel {
                stage('Clone Frontend Repo') {
                    steps {
                        dir('frontend') {
                            git branch: 'LakhalDevOpsFrontend', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Frontend.git'
                        }
                    }
                }
                stage('Clone Backend Repo') {
                    steps {
                        dir('backend') {
                            git branch: 'LakhalBackDevOps', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                        }
                    }
                }
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    script {
                        sh 'mvn clean package -DskipTests' // Build the backend, skipping tests
                    }
                }
            }
        }
         stage('Run Unit Tests') {
            steps {
                dir('backend') {
                    sh 'mvn test'  // Runs unit tests with JUnit and Mockito
                }
            }
        }



        stage('Rapport JaCoCo') {
            steps {
                sh 'mvn test'
                sh 'mvn jacoco:report'
            }
        }


        stage('JaCoCo coverage report') {
            steps {
                step([$class: 'JacocoPublisher',
                      execPattern: '**/target/jacoco.exec',
                      classPattern: '**/classes',
                      sourcePattern: '**/src',
                      exclusionPattern: '/target/**/,**/*Test,**/*_javassist/**'
                ])
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir('backend') {
                    withSonarQubeEnv('sonar') {  // Replace 'SonarQube' with the name of your SonarQube server in Jenkins
                        sh 'mvn sonar:sonar -Dsonar.projectKey=my-backend-project'
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml \
                        -Dsonar.test.inclusions=src/test/java/com/Parking/GestionParking/**
                    '''
                    }
                }
            }
        }

        stage('Build and Deploy to Nexus') {
            steps {
                // Deploy the artifact to Nexus repository
                sh 'mvn clean deploy -DskipTests'
            }
        }

        stage('Check Docker Images') {
            steps {
                script {
                    def imageExists = { imageName ->
                        return sh(script: "docker manifest inspect ${imageName} > /dev/null 2>&1", returnStatus: true) == 0
                    }

                    // Check for backend image
                    if (imageExists(DOCKER_IMAGE_BACK)) {
                        echo "Backend image already exists in Docker Hub."
                    } else {
                        echo "Backend image does not exist. Proceeding with build."
                        env.BUILD_BACK_IMAGE = 'true'
                    }

                    // Check for frontend image
                    if (imageExists(DOCKER_IMAGE_FRONT)) {
                        echo "Frontend image already exists in Docker Hub."
                    } else {
                        echo "Frontend image does not exist. Proceeding with build."
                        env.BUILD_FRONT_IMAGE = 'true'
                    }

                    // Check for MySQL image
                    if (imageExists(DOCKER_IMAGE_MYSQL)) {
                        echo "MySQL image already exists in Docker Hub."
                    } else {
                        echo "MySQL image does not exist. Proceeding with tag and push."
                        env.BUILD_MYSQL_IMAGE = 'true'
                    }
                }
            }
        }

        stage('Build Docker Images') {
            when { expression { env.BUILD_BACK_IMAGE == 'true' || env.BUILD_FRONT_IMAGE == 'true' } }
            steps {
                script {
                    if (env.BUILD_BACK_IMAGE == 'true') {
                        dir('backend') {
                            sh "docker build -t $DOCKER_IMAGE_BACK ."
                        }
                    }
                    if (env.BUILD_FRONT_IMAGE == 'true') {
                        dir('frontend') {
                            sh "docker build -t $DOCKER_IMAGE_FRONT ."
                        }
                    }
                }
            }
        }

        stage('Pull and Tag MySQL Image') {
            when { expression { env.BUILD_MYSQL_IMAGE == 'true' } }
            steps {
                script {
                    sh "docker pull mysql:latest"
                    sh "docker tag mysql:latest $DOCKER_IMAGE_MYSQL"
                }
            }
        }

        stage('Push Docker Images to Docker Hub') {
            when { expression { env.BUILD_BACK_IMAGE == 'true' || env.BUILD_FRONT_IMAGE == 'true' || env.BUILD_MYSQL_IMAGE == 'true' } }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"
                        if (env.BUILD_BACK_IMAGE == 'true') {
                            sh "docker push $DOCKER_IMAGE_BACK"
                        }
                        if (env.BUILD_FRONT_IMAGE == 'true') {
                            sh "docker push $DOCKER_IMAGE_FRONT"
                        }
                        if (env.BUILD_MYSQL_IMAGE == 'true') {
                            sh "docker push $DOCKER_IMAGE_MYSQL"
                        }
                        sh "docker logout"
                    }
                }
            }
        }

        stage('Build and Deploy') {
            steps {
                script {
                    dir('backend') {
                        sh 'docker compose down || true'
                        sh 'docker compose build'
                        sh 'docker compose up -d'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Images checked, built if necessary, pushed to Docker Hub, and services deployed successfully!'
        }
        failure {
            echo 'Image check, push, or deployment failed!'
        }
    }
}
