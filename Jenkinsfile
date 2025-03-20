pipeline {
    agent any
     environment {
            // Define Docker Hub credentials ID
            DOCKERHUB_CREDENTIALS_ID = 'Docker-hub-credentials'
            // Define Docker Hub repository name
            DOCKERHUB_REPO = 'topskari/projectcourse2_week1'
            // Define Docker image tag
            DOCKER_IMAGE_TAG = 'latest_v1'
        }
    stages {
        stage('Checkout') {
            steps {
                branch 'main', git 'https://github.com/topskari/project_course2_week1'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

         stage('Build Docker Image') {
                    steps {
                        // Build Docker image
                        script {
                            bat 'docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
                        }
                    }
                }
                stage('Push Docker Image to Docker Hub') {
                    steps {
                        // Push Docker image to Docker Hub
                        script {
                                                    withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, passwordVariable: 'DOCKERHUB_PASSWORD', usernameVariable: 'DOCKERHUB_USERNAME')]) {
                                                        bat "docker login -u %DOCKERHUB_USERNAME% -p %DOCKERHUB_PASSWORD%"
                                                        bat "docker push ${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}"
                                                    }
                        }
                    }
                }
    }
}