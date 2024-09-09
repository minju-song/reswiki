
const blocks = document.getElementById('blocks');

// 페이지 처음 로딩될 때
document.addEventListener('DOMContentLoaded', function(){
    blocks.innerHTML = '';
    getResList();
});

// 식당 데이터 가져오기
function getResList() {
    fetch('/restaurant/getNewList')
    .then(response => {
        if(!response.ok) {
            throw new Error('네트워크 응답 안좋음');
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        data.forEach(restaurant => {
            drawResBlock(restaurant);

        })
    })
}

// resBlock 그리는 함수
function drawResBlock(restaurant) {
    const resBlock = document.createElement('div');
    resBlock.className = 'resBlock';

    const resImg = document.createElement('img');
    resImg.className = 'resImg';
    const imgName = encodeURIComponent(restaurant.restaurantImg);
    resImg.setAttribute('src', '/img/restaurant/'+imgName);

    const resInfo = document.createElement('div');
    resInfo.className = 'resInfo';

    const resName = document.createElement('h4');
    resName.className = 'resName';
    resName.textContent = restaurant.restaurantName;

    const resAddr1 = document.createElement('div');
    resAddr1.className = 'resAddr1';
    resAddr1.textContent = restaurant.restaurantAddr1;

    const resDate = document.createElement('div');
    resDate.className = 'resDate';
    resDate.textContent = '최신 수정일 : ' + new Date(restaurant.restaurantUpdate).toLocaleDateString();

    resInfo.appendChild(resName);
    resInfo.appendChild(resAddr1);
    resInfo.appendChild(resDate);
    resBlock.appendChild(resImg);
    resBlock.appendChild(resInfo);
    blocks.appendChild(resBlock);
};