FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

EXPOSE 4567

CMD ["java","-cp","./classes:./dependency/*","edu.escuelaing.arep.SparkWebApp"]