# 2022-Spring-FSSN-gRPC
gRPC-Java example Code with Gradle Project

## 프로젝트의 구조 

gRPC의 4가지 방식에 해당하는 각각의 폴더마다, Server와 Client로 이루어져있습니다.

## 실행방법

실행 환경은 Ubuntu라고 가정합니다. Java SDK가 설치되어 있는 MacOS라면 Java SDK 설치 과정을 건너뛰고 Server와 Client 단계부터 실행 가능합니다.

### Java SDK 설치

```bash
$ sudo apt-get update
$ sudo apt-get upgrade

$ sudo apt-get install openjdk-11-jdk
```

### Java 설치 확인

```bash
$ java -version
$ javac -version
```

### Server

1. From lec-07-prg-01-hello_gRPC/Server directory:
   
```bash
$ ./gradlew installDist -PskipAndroid=true
```

2. 빌드 성공 후 실행
   
```bash
$ ./build/install/Server/bin/ServerMain
```

### Client

1. From lec-07-prg-01-hello_gRPC/Client directory:
   
```bash
$ ./gradlew installDist -PskipAndroid=true
```

2. 빌드 성공 후 실행
   
```bash
$ ./build/install/Client/bin/ClientMain
```

나머지 세 개의 폴더에 대해서도 위의 과정을 똑같이 수행하시면 되겠습니다.
