'use strict'
// 마커를 클릭하면 장소명을 표출할 인포윈도우
let infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
// 지도를 표시할 div 
let mapContainer = document.getElementById('mapDiv');
// 지도 중심 좌표, 지도 확대 레벨 
let mapOption = {
    center: new kakao.maps.LatLng(37.482011412817435, 126.89813635095327),
    level: 1
};

//구역 선택 셀렉트
const selectMap = document.querySelector('#selectMap');
//결과 나타낼 구역
const resultDiv = document.querySelector('.resultDiv');
//생성된 마커 담는 배열
let markers = [];
//인포윈도우 테스트 버튼
const closeInfoWindowBtn = document.querySelector('#closeInfoWindowBtn');
//지도
let map;
//==================================================================================================

window.onload = function(){
	// 지도 생성
	map = new kakao.maps.Map(mapContainer, mapOption);
}

//==================================================================================================

//인포윈도우 닫기
closeInfoWindowBtn.addEventListener('click', function () {
    infowindow.close();
   
})
//셀렉트박스 선택시 캠핑장 지도 검색 함수 및 결과 표시 구역 초기화
selectMap.addEventListener('change', function () {
	//초기화
	removeMakers();
	//셀렉트 초기화 옵션
	if ((this.value).trim() == '' || this.value == undefined || this.value == null) {
		console.log('초기화');
		return;
	}
	//셀렉트 구역 옵션
	else {
		console.log(this.value);
		//옵션값으로 검색
		searchPlace(this.value);
	}
})

//==================================================================================================


//지도 마커 지우기, 결과 목록 초기화
function removeMakers() {
    //생성된 마커가 있다면 마커들 삭제
    if (markers.length > 0) {
        markers.forEach((marker) => {
            //마커들 지우기
            marker.setMap(null);
            //마커 배열 초기화
            markers = [];
            resultDiv.innerHTML = '';
        })
    }
}

// 키워드로 장소 검색
function searchPlace(data) {
    // 장소 검색 객체 생성
    let ps = new kakao.maps.services.Places();
    ps.keywordSearch(data + ' 캠핑장', placesSearchCB);
}

// 키워드 검색 완료 시 호출되는 콜백함수
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        let bounds = new kakao.maps.LatLngBounds();
//        console.log(data);
        for (let i = 0; i < data.length; i++) {
            displayMarker(data[i]);
            bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
    }
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {

    // 마커를 생성하고 지도에 표시합니다
    let marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.y, place.x)
    });
    markers.push(marker);
    //결과표시 구역에 캠핑장 이름 표시
    resultDiv.innerHTML += `<a class = 'campingSpan' href='${place.place_url}' target='_blank'>${place.place_name}</a>`;

    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div class="placeDiv">캠피장 : ' + place.place_name + '<br>주소 : ' + place.address_name + '<br>날씨 : 준비중...' + '</div>');

        infowindow.open(map, marker);
        let placeDivs = document.getElementsByClassName('placeDiv');
        //캠핑장 이름 클릭시 이벤트 등록
        for (let item of placeDivs) {
            item.addEventListener('click', function () {
//                console.log(this.innerHTML);
            })
        }
    });
}


//지도 중심 이동 함수 일단 안씀
function moveMap(data) {
    // 주소 -> 좌표 변환 객체를 생성
    let geocoder = new kakao.maps.services.Geocoder();

    // 주소로 좌표 생성
    geocoder.addressSearch(data, function (result, status) {

        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {

            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            let message = 'latlng: new kakao.maps.LatLng(' + result[0].y + ', ';
            message += result[0].x + ')';
//            console.log(message);
            // 이동
            map.setCenter(coords);
            
        }
    });

}

