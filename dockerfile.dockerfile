FROM unbutu

RUN add-apt-repository ppa:webupd8team/java

RUN apt-get update

RUN apt-get install oracle-java8-installer

RUN apt-get install oracle-java8-set-default

RUN curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -

RUN apt-get  apt-get install -y nodejs

RUN apt-get install -y build-essential

COPY D:/task-manager /etc/task-manager

EXPOSE 3000

ENTRYPOINT ["java","-jar","/etc/task-manager/task_webapi/taskWebApi.jar"]
