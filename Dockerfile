FROM java:8
VOLUME /tmp
ADD /target/DimpleBlog.jar DimpleBlog.jar
EXPOSE 8888
ENTRYPOINT [ "java", "-jar", "/DimpleBlog.jar" ]