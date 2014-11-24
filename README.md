tchalk
======

* http://www.vorburger.ch/tchalk/tchalk-server-vertx/src/main/resources/web/index.html

$ redis-server
$ mvn install
$ java -jar tchalk-server-vertx/target/tchalk-server-vertx-1.0-SNAPSHOT-fat.jar

NOTE: You MUST mvn install, package is not sufficient,
because vertx-maven-plugin's pullInDeps will only look in the local repository, not the reactor.
