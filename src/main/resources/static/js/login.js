async function login(){
    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;

    const req = { // 이 부분은 axios로 post요청할 Controller가 어떤 데이터를 받는지 => LoginReq를 받고 있음
        email,      // 근데 LoginReq를 보면 String email, String password가 정의되어 있음
        password    // 근데 비동기 통신을 수행하기 때문에 RequestBody를 사용해서 key(변수), value(값)으로 할당하기 위해
    }               // req라는 객체를 만들어서 전달하는 것.

    const res = await axios.post('http://localhost:8888/login',req);
    if(res.data === true) {
        alert("로그인에 성공했습니다.");
        location.href = "/main";
    } else {
        alert("아이디 또느 비밀번호가 일치하지 않습니다.")
    }



}