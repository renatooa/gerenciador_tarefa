FROM ubuntu/jre/node

COPY /task-manager /etc/task-manager/

EXPOSE 8080

CMD ["java","-jar","/etc/task-manager/task_webapi/taskWebApi.jar"]
