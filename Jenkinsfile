pipeline {
    agent any
    environment {
        JAVA_HOME='/usr/lib/jvm/java-17-openjdk-17.0.1.0.12-2.el8_5.x86_64'
    }
    stages {
        stage('init'){
            steps {
                sh 'pwd'
                sh 'sudo chmod +x gradlew'
                sh 'printenv'
            }

        }
        stage('Compile') {
            steps {
                gradlew('clean')
                gradlew('classes')
            }
        }
        stage('Unit Tests') {
            steps {
                gradlew('test')
            }
            post {
                always {
                    junit '**/build/test-results/test/TEST-*.xml'
                }
            }
        }
        stage('Assemble') {
            steps {
                gradlew('assemble')
                gradlew('publish')
                stash includes: '**/build/libs/*.jar', name: 'app'
            }
        }
        stage('Promotion') {
            steps {
                timeout(time: 1, unit:'DAYS') {
                    input 'Deploy to Production?'
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker build -t accendl/myweibo .'
                sh 'sudo docker image push accendl/myweibo'
            }
        }
    }
}

def gradlew(String... args) {
    sh "./gradlew :web:${args.join(' ')} -s"
}