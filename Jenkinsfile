pipeline {
  agent any
  stages {
    stage('Unit Testing') {
      steps {
        sh 'echo "Testing"'
      }
    }

    stage('Build') {
      steps {
        sh '''npm install
npm build'''
      }
    }

    stage('Docker ') {
      steps {
        sh '''docker build .
docker push'''
      }
    }

  }
}