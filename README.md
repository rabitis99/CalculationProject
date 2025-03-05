스파르타 미니프로젝트 계산기 만들기
위 계산기는 아래에 조건에 맞게 제작되었으며, gui를 이용한 계산이 가능하다

Lv 1. 클래스 없이 기본적인 연산을 수행할 수 있는 계산기 만들기
 
    ✅ 양의 정수(0 포함)를 입력받기
 
    ✅ 사칙연산 기호(➕,➖,✖️,➗)를 입력받기
 
    ✅ 위에서 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기

Lv 2. 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기
 
    ✅ Lv 1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
 
    ✅ App 클래스의 main 메서드에서 Calculator 클래스의 연산 결과를 저장하고 있는 컬렉션 필드에 직접 접근하지 못하도록 수정 (캡슐화)
 
    ✅ 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 클래스를 생성
 
    ✅ Calculator 클래스에 저장된 연산 결과들 중 가장 나중에 저장된 데이터를 삭제하는 기능을 가진 메서드를 구현한 후 App 클래스의 main 메서드에 삭제 메서드가 활용될 수 있도록 수정

Lv 3. Enum, 제네릭, 람다 & 스트림을 이해한 계산기 만들기
 
    ✅ Enum 타입을 활용하여 연산자 타입에 대한 정보를 관리하고 이를 사칙연산 계산기 ArithmeticCalculator 클래스에 활용 해봅니다
 
    ✅ 저장된 연산 결과에 평균,합계 람다&스트림으로 표현하기
 
    ❌ 실수, 즉 double 타입의 값을 전달 받아도 연산이 수행하도록 만들기
        👉 String을 이용하여 "."을 통해 실수를 표현하는 과정이 존재하여 실수와 정수로 판단이 필요한데, 이는 지금 상황에선 불가하다고               판단
        👉 어떻게 "."이 없으면 int, "."이 있으면 double 또는 decimal로 표현할 수 있는가에 의문점이 주말에 공부가 필요한 상황


    
    
   ★마무리★
   
-------------------목,금,토----------------------------------------------

1. 정렬 구현해보가

2. 미적,적분

3. 삼각함수 사용해보기

4. decimal,int 구분짓기

-------------------일요일----------------------------------------------

5.제네릭,enum,람다&스트림 공부해보기 
