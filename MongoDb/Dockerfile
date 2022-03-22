FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

EXPOSE 27017


CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.SparkWebMongo"]
