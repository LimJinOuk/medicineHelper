document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault(); // 폼 기본 제출 막기

    const formData = new FormData(this);
    const jsonData = {};
    formData.forEach((value, key) => {
        jsonData[key] = value;
    });

    // JSON 콘솔 출력 (디버깅용)
    console.log("회원가입 JSON:", JSON.stringify(jsonData));

    // JSON 전송 (fetch 사용)
    fetch("doRegister", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(jsonData)
    })
        .then(response => {
            if (response.ok) {
                return response.json(); // 응답 JSON 변환
            } else {
                throw new Error("회원가입 실패");
            }
        })
        .then(data => {
            alert("회원가입 성공!");
            window.location.href = "/login"
            console.log("서버 응답:", data);
        })
        .catch(error => {
            console.error(error);
            alert("오류 발생: " + error.message);
        });
});