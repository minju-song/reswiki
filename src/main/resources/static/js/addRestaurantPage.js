// 검색 버튼
const searchRes = document.getElementById('searchRes');

searchRes.addEventListener('click', searchResApi);

// 이름으로 가게 검색
async function searchResApi() {
    const { Map, places } = await google.maps.importLibrary("maps");

    map = new Map(document.getElementById("map"), {
            center: { lat: 37.5665, lng: 126.978 },
            zoom: 15,
        });
    let restaurantName = document.getElementById('restaurantName').value;    console.log(restaurantName);

    //요청
    let request = {
        query: restaurantName,
        fields: ['name', 'geometry', 'place_id'],
    };

    var service = new google.maps.places.PlacesService(map);

    service.findPlaceFromQuery(request, function (results, status) {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
            for (var i = 0; i < results.length; i++) {
                // 이름을 이용하여 place_id 받은 뒤 세부 요청 보내기
                var request = {
                    placeId: results[i].place_id,
                    fields: ['place_id', 'name', 'rating', 'formatted_phone_number', 'geometry', 'formatted_address', 'reviews', 'photos']
                    };

                    service = new google.maps.places.PlacesService(map);
                    service.getDetails(request, callback);

                    function callback(place, status) {
                        if (status == google.maps.places.PlacesServiceStatus.OK) {
                            // 결과 그려주기
                            drawRes(place);
                        }
                    }
            }
            map.setCenter(results[0].geometry.location);
        } else {
            console.error('Place search was not successful due to: ' + status);
        }
    });
}

// 검색 결과 그려주는 함수
function drawRes(place){
    const resInfo = document.getElementById('resInfo');
    resInfo.innerHTML = '';

    // 가게 사진
    const resImg = document.createElement('img');
    resImg.setAttribute('src', place.photos[0].getUrl());
    resImg.className = 'resImg';

    // 가게 이름
    const resName = document.createElement('h4');
    resName.textContent = place.name;

    // 가게 주소
    const resAddr = document.createElement('p');
    resAddr.textContent = '주소 : ' + place.formatted_address;

    // 가게 연락처
    const resPhone = document.createElement('p');
    resPhone.textContent = '연락처 : ' + place.formatted_phone_number;

    // 등록 버튼
    const addBtn = document.createElement('button');
    addBtn.textContent = '새 리뷰 등록';
    addBtn.onclick = () => addReview(place);

    resInfo.appendChild(resImg);
    resInfo.appendChild(resName);
    resInfo.appendChild(resAddr);
    resInfo.appendChild(resPhone);
    resInfo.appendChild(addBtn);

    // // 해당 장소에 대한 리뷰가 있으면 리뷰도 그려주기
    // if(place.reviews.length > 0) {
    //     const googleReviews = document.getElementById('googleReviews');
    //     googleReviews.innerHTML = '';
    //     for (var j = 0; j < place.reviews.length; j++) {
    //         let div = document.createElement('div');

    //         // 리뷰 작성자 이름
    //         let author_name = document.createElement('p');
    //         author_name.textContent = '작성자 : ' + place.reviews[j].author_name;

    //         // 리뷰 별점
    //         let rating = document.createElement('p');
    //         rating.textContent = '별점 : ' + place.reviews[j].rating + "점";

    //         // 리뷰 내용
    //         let revText = document.createElement('strong');
    //         revText.textContent = place.reviews[j].text;

    //         let line = document.createElement('hr');

    //         div.appendChild(author_name);
    //         div.appendChild(rating);
    //         div.appendChild(revText);
    //         div.appendChild(line);

    //         googleReviews.appendChild(div);
    //     }
    // }
}

function addReview(place) {
    
    console.log(place);
}

