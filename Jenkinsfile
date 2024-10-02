pipeline {
    agent any

    // Load external configuration file
    environment {
        CONFIG = readYaml(file: 'pipeline-app1-config.yml') // Load YAML config file
    }

    stages {
        stage('Checkout') {
            steps {
                // Step to checkout the code from repository
                git url: 'https://github.com/hareeshgit2024/jenkins-pipeline.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                script {
                    // Based on the above above loaded config details, here dynamic build command based on that external config
                    sh "mvn clean package -Dmaven.test.skip=${CONFIG.skipTests}"
                }
            }
        }

        // Dynamically create test stages based on configuration
        stage('Test') {
            when {
                expression { CONFIG.runTests }
            }
            steps {
                script {
                    // Loop through test suites and run them based on config
                    for (suite in CONFIG.testSuites) {
                        sh "mvn test -Dsuite=${suite}"
                    }
                }
            }
        }

        // Dynamic Deployment Stage
        stage('Deploy') {
            steps {
                script {
                    if (CONFIG.deploy) {
                        deployApp(CONFIG.environment)
                    } else {
                        echo "Skipping deployment as per config."
                    }
                }
            }
        }
    }
}

// Define a function for deployment
def deployApp(String environment) {
    echo "Deploying to ${environment}"
    // Add your deployment command here
    sh "echo Deployment command for ${environment}"
}
