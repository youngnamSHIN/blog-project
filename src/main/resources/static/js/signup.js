function test() {
    alert("ddd");
}


async function isDuplicatedEmail() {
    const element = document.getElementById("fi"); // element 에는 사용자가 입력한 아이디정보가 들어있겠지?
    const email = element.value;
    const res = await axios.get('http://localhost:8888/auth/email?email='+email);
    if(res.data === true){
        alert("이미 사용중인 이메일 입니다.");
    }else{
        alert("사용 가능한 이메일 입니다.");
    }


    // 쿼리스트링으로 키 : email?email=' + 바뀌는 값 변수 value

}

/*   async function test() {
         const res = await axios.get('http://localhost:8888/test');
         // async는 동기적으로 처리한다는 의미이고, 비동기 함수의 결과를 기다린다는(await) 의미임.
         // 응답되는 데이터는 res.data에 적재된다.
        console.log(res.data);
     }

     */