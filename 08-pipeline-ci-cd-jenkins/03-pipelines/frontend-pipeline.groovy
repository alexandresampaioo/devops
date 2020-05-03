podTemplate(containers: [containerTemplate(args: 'cat', command: '/bin/sh -c', image: 'docker', livenessProbe: containerLivenessProbe(execArgs: '', failureThreshold: 0, initialDelaySeconds: 0, periodSeconds: 0, successThreshold: 0, timeoutSeconds: 0), name: 'questcode', resourceLimitCpu: '', resourceLimitMemory: '', resourceRequestCpu: '', resourceRequestMemory: '', ttyEnabled: true, workingDir: '/home/jenkins/agent')], label: 'questcode', namespace: 'devops', volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]) {
    node('questcode') {
        def repos
        stage('Build') {
            echo 'Iniciando clone do repositorio'
            repos = git credentialsId: 'github', url: 'git@github.com:alexandresampaioo/frontend.git'
        }
        stage('Package') {
            container('questcode') {
                echo 'Iniciando empacotamento com docker'
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USER')]) {
                    sh label: '', script: "docker login -u ${DOCKER_HUB_USER} -p ${DOCKER_HUB_PASSWORD}"
                    sh label: '', script: "docker build -t alexandresampaio/frontend:alpha . --build-arg NPM_ENV='staging'"
                    sh label: '', script: "docker push alexandresampaio/frontend:alpha"
                }               
            }
        }
        stage('Deploy') {
            echo 'Iniciando Deploy com helm'
            sh label: '', script: 'ls -ltra'
        }
    }
}
