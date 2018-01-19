FROM java:8
MAINTAINER labsoft-2018

ADD target/graphql-facade-0.0.1-SNAPSHOT-standalone.jar /srv/graphql-facade.jar

EXPOSE 8670

CMD ["java", "-jar", "/srv/graphql-facade.jar"]
