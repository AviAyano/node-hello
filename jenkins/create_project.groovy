job('NodeJS Docker example') {
    scm {
        git('https://github.com/AviAyano/node-hello.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('AviAyano')
            node / gitConfigEmail('AviAyano@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('Node 17.0.1') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('aviayano/jenkinspipeline')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

