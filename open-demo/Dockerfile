FROM java:8
#FROM openjdk:8-jdk-alpine

#镜像的制作人
LABEL MAINTAINER=eshuo@163.com


#工作目录
#WORKDIR /app/

#在容器中创建挂载点，可以多个
VOLUME /tmp

#声明了容器应该打开的端口并没有实际上将它打开
#EXPOSE 8080

# 设置容器时间
ENV TZ=Asia/Shanghai

#定义参数（build构建时使用）
#ARG JAR_FILE=target/open-demo-0.0.1-SNAPSHOT.jar

#设置环境变量（程序运行时使用）
#ENV JAVA_OPTS=""
#ENV PARAMS=""
ENV OPENAI_CHAT_HOST="";
ENV OPENAI_CHAT_KEY="";


#拷贝本地文件到镜像中
COPY target/open-demo-0.0.1-SNAPSHOT.jar app.jar

#RUN bash -c "touch /app.jar"

#指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
ENTRYPOINT ["java",  "-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom","-jar", "app.jar"]
