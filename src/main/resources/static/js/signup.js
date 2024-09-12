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
        return response.text();        
    })
    .then(data => {
        window.location.href = data; 
        // 원하는 리다이렉트 URL로 이동

    })
    .catch(error => {
        console.log("Error : ", error);
    })
})