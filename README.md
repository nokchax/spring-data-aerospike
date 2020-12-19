# spring data aerospike
# Project Setting
#### Docker aerospike
```
$ docker pull aerospike
$ docker run -d --name aerospike -p 3000:3000 -p 3001:3001 -p 3002:3002 -p 3003:3003 aerospike
```

#### Aerospike Customizing
에어로 스파이크 설정을 커스터마이징 하길 권장하고 있습니다. [링크](https://hub.docker.com/_/aerospike) 의 Custom Aerospike Configuration 섹션을 참고해주세요

### Configuration
```java
@Configuration
@EnableAerospikeRepositories(basePackageClasses = TestRepository.class)
public class AerospikeConfig extends AbstractAerospikeDataConfiguration {
    @Value("${aerospike.hosts}")
    private List<String> hosts;
    @Value("${aerospike.port}")
    private int port;
    @Value("${aerospike.namespace}")
    private String namespace;

    @Override
    protected Collection<Host> getHosts() {
        return hosts.stream()
                .map(host -> new Host(host, port))
                .collect(Collectors.toSet());
    }

    @Override
    protected String nameSpace() {
        return namespace;
    }
}
```

docker로 올린 aeropsike의 기본 namespace는 test입니다.
```yaml
aerospike:
  hosts: localhost
  port: 3000
  namespace: test
```

### Domain
```java
@Getter
@Setter
@Document(collection = "domain", expiration = 1)
@AllArgsConstructor
public class TestDomain {
    @Id
    private long id;
    private String value;
}
```
@Document 어노테이션을 사용해서, set의 이름과 도매인이 저장됐을때 expire time을 지정할 수 있습니다.

### Repository
```java
public interface TestRepository extends CrudRepository<TestDomain, Long> {
}
```
