# myazeroth

## Quick Start

### Run with source code (recommended)

#### Prerequisite
1. 64bit OS, Linux/Unix/Mac is recommended;
2. 64bit JDK 17+;
3. 需要nacos服务;
4. 需要seata服务;
5. 需要rocketmq服务;
6. web 可以用 Gradle bootrun 启动，其他在idea-Services启动即可;

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

### Run with docker

#### Prerequisite
1. 64bit OS, Linux/Unix/Mac is recommended;
2. 4G memory;
3. 需要nacos服务;
4. 需要seata服务;
5. 需要rocketmq服务;

#### docker network create
```shell
docker需要 --subnet=172.20.0.0/16 的网段, 否则要修改com.accendl.myazeroth.compose.yaml
的很多关于网络的配置
```

#### docker volume create
```shell
docker volume create accendl-mail-data
docker volume create myazeroth-log-data
```

#### Pull from [docker hub(accendl)](https://hub.docker.com/u/accendl)

```shell
docker compose pull 
```

#### Run it (remember to modify to your own service configuration)

```shell
docker compose up --detach
```

#### Stop it
```shell
docker compose down
```

## License
[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html) Copyright (C) Apache Software Foundation