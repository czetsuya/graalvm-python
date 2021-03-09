# GraalVM Python

This is a demonstration project on how you can run other programming languages such as Javascript and Python
in a Java application using Quarkus framework.

## Preparing the local environment

You must be running on Ubuntu OS.

1. Download and configure GraalVM.
https://www.graalvm.org/docs/getting-started/linux/

Set GRAALVM_HOME to where you extract the GraalVM tar file.

Set JAVA_HOME to GRAALVM_HOME.

Add JAVA_HOME to the PATH variable.

Refer to https://www.czetsuyatech.com/2021/03/install-openjdk-11-in-ubuntu.html

2. Navigate inside GraalVM/bin folder.

3. Install the native image. 
```shell
./gu install native-image
```

4. Install Python in GraalVM.
```shell
./gu install python

// check the available packages
./graalpython -m ginstall install -h
```

5. Package and run the application.
```shell
./mvnw package -Dquarkus.package.type=uber-jar
java -jar target/graalvm-python-1.0.0-SNAPSHOT-runner.jar .
```

6. Testing

Fire up your URL, access the following URLs and check the logs (for JS).

- http://localhost:8080/poly/js
- http://localhost:8080/poly/py