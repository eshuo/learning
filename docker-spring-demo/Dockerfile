FROM openjdk:8-jdk-alpine

#镜像的制作人
LABEL MAINTAINER=eshuo@163.com

#在容器中创建挂载点，可以多个
VOLUME /tmp

#声明了容器应该打开的端口
EXPOSE 8080


# 设置用户
ENV user=eshuo
# 设置容器时间
ENV TZ=Asia/Shanghai

# 添加用户：赋予sudo权限，指定密码
#RUN useradd --create-home --no-log-init --shell /bin/bash ${user} \
#    && adduser ${user} sudo \
#    && echo "${user}:1" | chpasswd \

# 改变用户的UID和GID
# RUN usermod -u 1000 ${user} && usermod -G 1000 ${user}


# 设置用户
ENV user=eshuo

# Alpine Linux通常使用adduser而不是useradd。
RUN adduser -D ${user}

# 指定容器起来的登录用户
USER ${user}

# 指定容器起来的工作目录
WORKDIR /home/${user}


#拷贝本地文件到镜像中
COPY target/docker-spring-demo-1.0-SNAPSHOT.jar app.jar

#指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
ENTRYPOINT ["java",  "-Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom","-jar", "app.jar"]



