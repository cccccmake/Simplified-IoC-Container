# Simplified IoC Container

## To the Begin

- This repository is rather a simulation for an IoC container. The structure of this repository is described as follows:

```
root
├─ src/main/resources/applicationContext.xml -> semi applicationContext.xml
├─ src/main/java/com/imooc/spring/ioc/Application.java -> psvm hosts here
├─ src/main/java/com/imooc/spring/ioc/context
│  ├─ ApplicationContext.java -> interface
│  └─ ClassPathXmlApplicationContext.java -> simulating IoC initialization
└─ src/main/java/com/imooc/spring/ioc/entity
   └─ Apple.java -> entity Apple
```

## Reflection in Java


