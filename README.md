##### entity 수정시 주의사항

- entity 수정시 batch project의 entity도 똑같이 변경이 되어야 합니다. (의존 관계)

- 추후 멀티모듈 gradle로 변경하여 entity 관련이나 공통으로 사용하는 모듈은 분리하여 프로젝트 분배
[멀티모듈 설계 이야기 with Spring, Gradle](https://woowabros.github.io/study/2019/07/01/multi-module.html#3-%EC%A0%91%EA%B7%BC-%EA%B0%9C%EB%B0%A9%ED%8F%90%EC%87%84)

global.aop 
- 전역 로깅처리  - ```RequestLogginAspect```
- 전역 트랜잭션처리 - ```TransactionAspect```
- 관리자 사용로그 - ```CUDPolicyAspect```
- 부서계층조회 - ```BeforeOrgCodesAspect```



