# myazeroth

## Quick Start

### Run with docker

#### Prerequisite
1. 64bit OS, Linux/Unix/Mac is recommended;
2. 4G memory;

#### docker network create
```shell
docker network create --driver=bridge --subnet=172.20.0.0/16 azeroth
```

#### docker volume create
```shell
docker volume create accendl-mail-data
```

#### Pull from [docker hub(accendl)](https://hub.docker.com/u/accendl)

```shell
docker image pull accendl/account
docker image pull accendl/azeroth
docker image pull accendl/mail
docker image pull accendl/rocketmq-producer
docker image pull accendl/rocketmq-consumer
docker image pull accendl/web
```

#### Run it (remember to order run)

```shell
1
docker run --name accendl-account --rm --detach  \
--privileged --network azeroth --network-alias docker \
 --ip 172.20.0.5   \
 --publish 8105:8105 --publish 20885:20885 \
  accendl/account 

2
docker run --name accendl-azeroth --rm --detach  \
--privileged --network azeroth --network-alias docker \
 --ip 172.20.0.4   \
 --publish 8104:8104 --publish 20884:20884 \
  accendl/azeroth 
  
3
docker run --name accendl-mail --rm --detach  \
--privileged --network azeroth --network-alias docker \
 --ip 172.20.0.6   \
 --volume accendl-mail-data:/image:ro \
 --publish 8106:8106 --publish 20886:20886 \
  accendl/mail 

4
docker run --name accendl-rocketmq-producer --rm --detach  \
--privileged --network azeroth --network-alias docker \
--ip 172.20.0.7   \
 --publish 8107:8107 --publish 20887:20887  \
  accendl/rocketmq-producer
  
5
docker run --name accendl-rocketmq-consumer --rm --detach  \
--privileged --network azeroth --network-alias docker \
--ip 172.20.0.8   \
 --publish 8108:8108 --publish 20888:20888 \
  accendl/rocketmq-consumer

6  
docker run --name accendl-web --rm --detach  \
--privileged --network azeroth --network-alias docker \
--ip 172.20.0.2   \
 --publish 8102:8102 --publish 20882:20882   \
  accendl/web
```

### Run with source code


#### Prerequisite
1. 64bit OS, Linux/Unix/Mac is recommended;
2. 64bit JDK 17+;
3. web 可以用 Gradle bootrun 启动，其他在idea-Services启动即可;

记得修改配置文件(你自己的nacos服务的ip和port，rocketmq服务的ip和port)和加入jvm参数
```shell
--add-opens java.base/java.lang.reflect=ALL-UNNAMED 
--add-opens java.base/java.lang=ALL-UNNAMED 
--add-opens java.base/java.math=ALL-UNNAMED
```

seata需要在seata.properties中加入你自己的配置，默认是default
```shell
service.vgroupMapping.my_azeroth_tx_group=default
```

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation