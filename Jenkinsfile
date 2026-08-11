pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                git(
                    url: 'git@github.com:yeshpuliventla1/inventory-management-system.git',
                    credentialsId: 'github-ssh',
                    branch: 'main'
                )
            }
        }

        stage('Credentials Demo') {
            steps {
                withCredentials([
                    string(
                        credentialsId: 'inventory-demo-secret',
                        variable: 'DEMO_SECRET'
                    )
                ]) {
                    sh '''
                        echo "Secret is available to Jenkins"
                        echo "Secret length: ${#DEMO_SECRET}"
                    '''
                }
            }
        }

        stage('Environment Check') {
            steps {
                sh '''
                    whoami
                    hostname
                    pwd
                    java -version
                    mvn -version
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                recordCoverage(
                    tools: [[
                        parser: 'JACOCO',
                        pattern: 'target/site/jacoco/jacoco.xml'
                    ]]
                )
            }
        }

        stage('Publish Test Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('JaCoCo Coverage') {
            steps {
                archiveArtifacts artifacts: 'target/site/jacoco/**',
                                 fingerprint: true
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifact') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar',
                                 fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Inventory Management CI Pipeline completed successfully!'
        }

        failure {
            echo 'Inventory Management CI Pipeline FAILED!'
        }

        always {
            echo 'Pipeline execution completed.'
        }
    }
}