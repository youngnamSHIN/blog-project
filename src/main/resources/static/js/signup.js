
async function isDuplicatedEmail() {
    // 0. 비동기 통신을 위해 ajax가 아닌 axios를 사용함 => jQuery 의존성 제거

    // 1. 사용자가 입력한 이메일 정보 추출
    const element = document.getElementById("fi"); // fi를 통해 element로 요소 접근 가능
    const email = element.value; // 로 값을 가져올수 있음

    // 2. 서버쪽에 /auth/email에 GetMapping을 통해 이메일 중복여부를 검사해주는 로직이 작성되어 있음
    //    => 해당 로직은 쿼리스트링 key값으로 email을 받아서 처리하고 있으므로,
    //       1번에서 추출한 값과 쿼리스트링을 조합하여 요청을 전송
    const res = await axios.get('http://localhost:8888/auth/email?email='+email); // 그냥 문법이라고 생각해라 다음 작업을 할동안 기다린다는 뜻
    if(res.data === true){ // 응답은 res의 data필드값으로 오기 때문에 해당 값을 기반으로 alert 처리 수행
        alert("이미 사용중인 이메일 입니다.");
    }else{
        alert("사용 가능한 이메일 입니다.");
    }
    // 쿼리스트링으로 키 : email?email=' + 바뀌는 값 변수 value
}

async function register() {
    const email = document.getElementById("fi").value;
    const password = document.getElementById("pw").value;
    const name = document.getElementById("nm").value;
    const phone = document.getElementById("ph").value;
    const address = document.getElementById("ad").value;

    const req = {
        email: email, // 원칙적으로는 이런식으로 key: value 형식으로 작성해줘야 하지만,
        password,   // 변수명하고 key값하고 동일하면 이런식으로 선언해도 된다. (const 해주면서)
        name,
        phone,
        address  // 변수명틀려서 오류났었음
    }

    //실제 요청해서 회원가입 되도록 하기
    const res = await axios.post('http://localhost:8888/signup',req); // uri랑 데이터
    console.log(res);

     if (res.data > 0) { //정상적으로 데이터를 넣어주고 진행됬다면 index화면 렌더링
         alert("성공"); // 다음 메인페이지로 이동
         location.href = "/";
     } else {
         alert("실패하셨습니다.");
     }

    // console.log(email,password,name,phone,adress); 데이터가 제데로 보내졌는지 출력하는 습관

}
