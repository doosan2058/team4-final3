const footerMapContainer = document.querySelector('.shopFooterMapDiv');
const footerMapOption = {
    center: new kakao.maps.LatLng(37.48197758360128, 126.8980883453554), level: 1 // 지도의 확대 레벨
};

// 지도 생성
const footerMap = new kakao.maps.Map(footerMapContainer, footerMapOption);

// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
const footerMapTypeControl = new kakao.maps.MapTypeControl();

// 마커가 표시될 위치입니다
const footerMarkerPosition = new kakao.maps.LatLng(37.48197758360128, 126.8980883453554);

// ===========================================================================================================

window.addEventListener('load', initFooter);

// ===========================================================================================================

function initFooter() {
    // 지도 타입 컨트롤을 지도에 표시합니다
    footerMap.addControl(footerMapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

    // 마커를 생성합니다
    const footerMarker = new kakao.maps.Marker({
        position: footerMarkerPosition
    });

// 마커가 지도 위에 표시되도록 설정합니다
    footerMarker.setMap(footerMap);
}

