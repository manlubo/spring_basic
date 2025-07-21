## 2025.07.16
- 스프링에서 Bean 등록 방식은 수동 등록(XML)과 자동 등록(Component Scan)이 있음.
- 수동 등록은 `<bean>` 태그로 명시적으로 등록하며, 클래스에 애노테이션이 필요 없음.
- 자동 등록은 `@Component` 계열 애노테이션과 `<context:component-scan>`을 통해 클래스들을 자동 스캔해 Bean으로 등록함.
- 수동은 명시적이고 예측 가능하지만 설정이 번거롭고, 자동은 간결하지만 애노테이션 누락 주의가 필요함.

## 2025.07.21
- JPA 에러 분석 (`Failed to configure a DataSource`)
  → 원인: `db.yml` 접속 정보 누락  
  → 해결: `spring.datasource` 설정 추가 필요

- `spring.jpa.hibernate.ddl-auto` 옵션 정리  
  → 테이블 자동 생성/갱신 설정 (`update`, `create`, `validate`, `none` 등)

- `spring.jpa.show-sql` 옵션 정리  
  → 콘솔에 실행되는 SQL 쿼리 출력

- Hibernate SQL + 파라미터 로그 출력 설정 방법  
  → `org.hibernate.SQL`, `org.hibernate.type.descriptor.sql` 로그 설정 추가

- `EntityManager` 주요 메서드 정리  
  → `persist`, `find`, `merge`, `remove`, `flush`, `clear`, `detach`, `contains` 등

- `persist()` 개념 정리  
  → 새로운 엔티티를 영속성 컨텍스트에 등록하여 DB 저장 대상에 포함시킴

- `row`, `tuple`, `record` 개념 비교  
  → 용도와 맥락 차이 정리 (실무, 이론, SQL 표준/프로그래밍 관점)

- Gradle 의존성 스코프 정리  
  → `implementation`, `runtimeOnly`, `testImplementation`, `developmentOnly`, `annotationProcessor`, `testAnnotationProcessor` 등 역할 설명

- QueryDSL 개념 정리  
  → JPA 기반의 타입 안정성 있는 쿼리 빌더

- ORM vs MyBatis vs QueryDSL 비교  
  → SQL 자동화 정도, 쿼리 유연성, 실무 적용 상황별 장단점 비교