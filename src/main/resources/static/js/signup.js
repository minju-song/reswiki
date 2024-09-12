const signupForm = document.getElementById("signupForm");

// 회원가입 버튼 클릭
signupForm.addEventListener('submit', function(e) {
    e.preventDefault();

    const formData = new FormData(this);

    fetch('/member/join', {
        method : 'POST',
        body : formData
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/'; // 원하는 리다이렉트 URL로 이동
        } else {
            // 오류 처리
            alert('회원 가입에 실패했습니다.');
        }
    })
    .catch(error => {
        console.log("Error : ", error);
    })
})