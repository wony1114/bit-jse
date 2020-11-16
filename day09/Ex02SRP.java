package day09;
// 객체지향 5원칙 중 단일책임원칙
// "클래스는 단 1가지 책임을 져야한다."
// 프로그램이 여러가지 파트로 나누어질 수 있는데
// UI, 데이터 처리, UI-데이터처리 연결 등등
// 단일책임원칙이 제대로 지켜진 클래스라면 UI 담당클래스는 UI만 책임진다
// 데이터베이스로부터 데이터를 받아 객체로 만드는 클래스는 그것만 책임진다.
// UI가 요청한 데이터를 클래스로 만들어서 다시 UI쪽으로 보내주는 클래스는 그것만 책임진다.

// 하지만 단일 책임 원칙이 제대로 지켜지지 않은 경우에는
// UI 담당 클래스인데 UI도 책임지고, 데이터를 왔다갔다 하는 것도 책임지고
// 데이터를 객체로 만들어주는 클래스는 UI도 띄어주고
// 연결 역할을 해야하는 클래스는 연결 뿐만 아니라 UI, 데이터가공까지 책임지게 되면?

// 만약 우리가 UI를 수정애야되는 경우에 단일책임이 제대로 지켜진 경우라면 UI를 담당하는 클래스만 수정하면 된다.
// 하지만 단일책임원칙이 제대로 지켜지지 않은 경우면 모든 클래스를 다 수정해야 한다.

// 디자인 패턴 중 이러한 단일책임원칙을 지키도록
// 프로그램을 3가지 종류로 나누어 개발하는 패턴이 있는데
// 그것은 MVC패턴이다.

// MVC패턴은 프로그램을 다음과 같이 나눈다
// Model - View - Controller

// Model : 데이터베이스의 데이터를 자바 클래스 객체로 가공해준다.
//         데이터베이스같은 경우에는 해당 프로젝트에서 종류에 따라 여러가지 테이블이 만들어진다.
//         예시 : 회원 테이블, 게시글 테이블, 댓글 테이블......
//         Model의 경우, 클래스 하나가 테이블 하나를 담당한다.
//         Model 클래스의 필드는 테이블의 컬럼과 똑같이 일치시켜준다.
//         예시 : 글번호-id, 글제목-title, 글내용 - content......
//         Model 클래스는 POJO 클래스로써 필드, getter/setter, Object 메소드 오버라이드 외에는
//         코드가 들어가지 않으며, 또한 필드의 데이터 타입들은 
//         기본형 데이터타입, String, DateTime(Calendar) 
//         이정도 외에는 절대 사용자가 만든 클래스로 필드를 만들어주지 않는다.

//         POJO란? Plain Old Java Object
//         즉 평범한 옛날 자바 객체의 줄임말로써
//         클래스를 복잡하지 않고 최대한 간단하고 다른 클래서 import를 최소화해서
//         그 클래스를 다른 프로젝트에서도 편하게 재사용하자하는 그런 개념이다.
//         모델 클래스의 접미사는 DTO(Date Transfer Object) 혹은 VO(Value Object)가 붙는다. 

// View : View의 경우 클래스가 아니고 사용자가 보는 화면이 된다.
//        만약 여러분들이 웹 프로그래밍을 하게 된다면, 사용자가 보게되는 웹페이지가 View가 된다.
//        View는 데이터베이스로부터 데이터를 직접 받아오지 않는다.
//        대신 뷰는 컨트롤러한테 요청해서 컨트롤러가 데이터베이스의 데이터를 모델로 가공해서
//        뷰에게 전달해주고 뷰는 그 데이터를 사용하거나
//        뷰가 보내준 데이터를 컨트롤러가 모델로 가공해서 데이터베이스에 보내주게 된다.
//        우리의 경우, 콘솔 프로그래밍을 하고 있기 때문에 콘솔에 출력되는 쪽이 뷰가 되게 된다.
//        뷰어 클래스의 경우 Viewer라는 접미사가 붙는다.

// Controller : 컨트롤러는 뷰에서 받은 요청을 실질적으로 처리하는 역할
//              뷰가 데이터를 요청했을 경우에는 데이터베이스로부터 받은 데이터를 모델로 바꾸고
//              그 모델 객체를 뷰에 보내주거나 뷰가 보낸 데이터를 모델로 바꾸고 그리고 해당 객체를 데이터베이스에 보낸다.
//              컨트롤러 클래스의 경우 Controller라는 접미사가 붙는다.

// MVC패턴의 경우 다음과 같은 흐름도를 가지게 된다.
// 1. 사용자가 출력을 요청한 경우
//    A. 사용자가 뷰에서 보고 싶은 데이터를 선택(예시 : 학생 목록 보여주세요.)
//    B. 뷰는 컨트롤러에게 사용자가 원하는 데이터가 무엇인지 컨트롤러한테 말한다.
//    C. 컨트롤러는 뷰러부터 받은 요청을 처리해서 뷰한테 객체로 다시 보내준다.
//    D. 뷰는 컨트롤러가 보내준 데이터를 화면에 출력해준다. 

// 2. 사용자가 입력, 수정, 삭제를 요청한 경우
//    A. 사용자가 입력, 수정, 삭제를 선택해서 뷰에 입력한다.
//    B. 뷰는 입력받은 값들을 모델 객체의 형태로 컨트롤러한테 보내준다.
//       이때는 입력, 수정, 삭제에 따라서 호출되는 컨트롤러 메소드가 다르다.
//    C. 컨트롤러는 뷰로부터 전달받은 모델 객체를 메소드에 따라서
//       데이터베이스에 입력, 수정, 삭제 한다.
public class Ex02SRP {

}





















