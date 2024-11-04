pipeline {
    agent any
    stages {
        stage('Clone Repositories') {
            parallel {
                stage('Clone Frontend Repo') {
                    steps {
                        dir('frontend') {
                            git branch: 'SaafiFiras-5ARCTIC4-G6', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Frontend.git'
                        }
                    }
                }
                stage('Clone Backend Repo') {
                    steps {
                        dir('backend') {
                            git branch: 'SaafiFiras-5ARCTIC4-G6', url: 'https://github.com/Nawres-code/5ARCTIC4-G6-Backend.git'
                        }
                    }
                }
            }
        }
    }
    post {
        success {
            echo 'Repositories cloned successfully!'
        }
        failure {
            echo 'Failed to clone repositories!'
        }
    }
}