FROM ubuntu

RUN apt-get update && apt-get install -y openjdk-8-jre

RUN apt-get install -y nodejs

RUN apt-get install -y build-essential

RUN apt-get install npm

COPY /task-manager /etc/task-manager/

EXPOSE 3000

CMD ["java","-jar","/etc/task-manager/task_webapi/taskWebApi.jar"]
