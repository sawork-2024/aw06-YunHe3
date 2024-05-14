# Micro WebPoS 

1. 架构介绍
   ![](micropos.drawio.png)
    该微服务架构的webpos项目由5个服务组成
   1. api-gateway，网关服务，用于服务的重定向
   2. discovery-service，用于服务的注册，类似与DNS
   3. product-service，商品服务，为webpos提供可扩展的商品管理服务
   4. cart-service，购物车服务，为webpos提供可扩展的购物车管理服务
   5. counter-service，柜台服务，为购物车提供可扩展的复杂的计算服务

2. 技术实现细节
   1. 数据库
      * cart-service和product-service的数据库管理通过h2实现
      * product-service的数据来源为JD.com并且缓存通过redis实现
   2. 服务注册
      * discovery-service通过eureka实现
   3. 负载均衡
      * cart-service对于counter-service通过restTemplate来实现负载均衡访问
   4. 断路器
      * TBD
   5. 可在kubernetes或者minikube上进行部署。
      * TBD
   6. 压力测试实验验证对单个微服务进行水平扩展（而无需整个系统所有服务都进行水平扩展）可以提升系统性能
      * TBD
