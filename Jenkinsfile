pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'github-credentials', url: 'https://github.com/Tobyawo/appointment-service']]])
                // Run Maven on a Unix agent.
                sh "mvn clean install"
            }
        }

        stage("Build Docker Image") {
            steps {
                script {
                    sh 'docker build -t tobyawo/appointment-service .'
                }
            }
        }

        stage("Push Docker Image to Docker Hub") {
            steps {
                script {
                    // Use 'withCredentials' to access both username and password
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        // Login to Docker Hub using the credentials
                        sh '''
                            echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                            docker push tobyawo/appointment-service
                        '''
                    }
                }
            }
        }

        stage("Remove Local Docker Image") {
            steps {
                script {
                    sh 'docker rmi tobyawo/appointment-service'
                }
            }
        }
    }
}
