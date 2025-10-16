pipeline {
    agent any

    stages {

        // ===== FRONTEND BUILD =====
        stage('Build Frontend') {
            steps {
                dir('React') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        // ===== FRONTEND DEPLOY =====
        stage('Deploy Frontend to Tomcat') {
            steps {
                bat '''
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charity-ui" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charity-ui"
                )
                mkdir "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charity-ui"
                xcopy /E /I /Y React\\dist\\* "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charity-ui"
                '''
            }
        }

        // ===== BACKEND BUILD =====
        stage('Build Backend') {
            steps {
                dir('SpringBoot') {
                    bat 'mvn clean package'
                }
            }
        }

        // ===== BACKEND DEPLOY =====
        stage('Deploy Backend to Tomcat') {
            steps {
                bat '''
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charityfundapi.war" (
                    del /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charityfundapi.war"
                )
                if exist "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charityfundapi" (
                    rmdir /S /Q "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\charityfundapi"
                )
                copy "SpringBoot\\target\\*.war" "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1\\webapps\\"
                '''
            }
        }

    }

    post {
        success {
            echo 'Deployment Successful!'
        }
        failure {
            echo 'Pipeline Failed.'
        }
    }
}