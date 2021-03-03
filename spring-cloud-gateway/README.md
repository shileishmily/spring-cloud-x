#通过网关调用dubbo
1、请求URL：http://localhost:9001/dubbo
````
{

    "interfaceName":"com.x.demo.api.HelloDubboService",
    "method":"sayHi",
    "version":"1.0.0",
    "param":["Leo"],
    "address":"zookeeper://localhost:2181"
}
````