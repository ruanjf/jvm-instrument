# JVM热更新示例

需要先安装[JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)和[Maven](http://maven.apache.org/download.cgi)

## 打包jar
```sh
cd jvm-instrument
mvn package
cd target
```

## 默认启动方式
```sh
java -jar jvm-instrument-1.0-SNAPSHOT.jar 6 10 Demo getInt
```
 
## 启动时设置
```sh
java -javaagent:jvm-instrument-1.0-SNAPSHOT.jar=Demo,/Demo.class.2 -jar jvm-instrument-1.0-SNAPSHOT.jar 6 10 Demo getInt
```

## 运行后热更新
这种方式需要`tools.jar`，请设置正确的路径
```sh
java -jar jvm-instrument-1.0-SNAPSHOT.jar 6 10 Demo getInt
# 新开终端执行下面热更新命令
java -cp /Library/Java/JavaVirtualMachines/jdk1.8.0_152.jdk/Contents/Home/lib/tools.jar:jvm-instrument-1.0-SNAPSHOT.jar:. com.runjf.test.jvm.instrument.AgentAttach jvm-instrument-1.0-SNAPSHOT.jar <pid> Demo,/Demo.class.2
```
