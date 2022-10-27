FROM openjdk:8

ENV wordir=/opt/java/
COPY . ${wordir}
WORKDIR ${wordir}
EXPOSE 8761
CMD ['java','-jar','eureka-server-1.0.jar']