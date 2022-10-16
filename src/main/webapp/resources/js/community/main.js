let camping_title = document.querySelectorAll(".camping_title"); // 자유게시글 제목 (상세페이지로 넘어가게)
let board_title = document.querySelectorAll(".board_title"); // 모임게시글 제목 (상세페이지로 넘어가게)
let weather_img = document.querySelector(".weather_img");//날씨정보 이미지
let wind_speed = document.querySelector(".wind_speed");//풍속
let humidity = document.querySelector(".humidity");//습도
let clouds = document.querySelector(".clouds");//구름
let weather = document.querySelector(".weather");//날씨
let temp = document.querySelector(".temp");//온도
let country = document.querySelector(".location");//장소
let location_select = document.querySelector(".section2_map_list");// 지역 선택
let weather_area = document.querySelector(".section2_map_content");// 날씨 표시 영역
let right_btn = document.querySelector(".right_btn"); // 캐러셀 오른쪽 버튼
let left_btn = document.querySelector(".left_btn"); // 캐러셀 왼쪽 버튼
let carousel = document.querySelector(".section2_event_content_carousel"); // 캐러셀
let carousel_count = document.querySelectorAll(".section2_event_content_info").length; // 캐러셀 갯수
let carousel_num=0;// 캐러셀에 사용할 변수

//캐러셀 오른쪽 버튼
right_btn.addEventListener("click",function(){
	if(carousel_num<carousel_count-1){
		carousel_num ++;
		left_btn.style.display = "block";
		carousel.style.left = -(740*carousel_num)+"px";
		if(carousel_num == carousel_count-1){
			this.style.display = "none";
		}
	}
})

//캐러셀 왼쪽 버튼
left_btn.addEventListener("click",function(){
	if(carousel_num>=0){
		carousel_num --;
		right_btn.style.display = "block";
		carousel.style.left = -(740*carousel_num)+"px";
		if(carousel_num == 0){
			this.style.display = "none";
		}
	}
})

//날씨 상태에 따른 날씨 이미지 바뀌게
var change_weather = function(){
	if(weather.textContent == 'clear sky'){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455017.mp4";
	
	} else if(weather.textContent == 'few clouds' || weather.textContent == 'scattered clouds'){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455053.mp4";
	
	} else if(weather.textContent == 'broken clouds'){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455024.mp4";
	
	} else if(weather.textContent == "overcast clouds"){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455054.mp4";
	
	} else if(weather.textContent == 'shower rain'||weather.textContent == "light rain"||weather.textContent == "moderate rain"||weather.textContent == "ligth intensity shower rain"||weather.textContent == "heavy intensity shower rain"||weather.textContent == "ragged shower rain"
			||weather.textContent == "light intensity drizzle"||weather.textContent == "drizzle"||weather.textContent == "heavy intensity drizzle"||weather.textContent == "light intensity drizzle rain"||weather.textContent == "shower rain and drizzle"
			 ||weather.textContent == "shower drizzle"){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455057.mp4";
	
	} else if(weather.textContent == 'rain'||weather.textContent == "heavy intensity rain"||weather.textContent == "very heavy rain"||weather.textContent == "extreme rain"||weather.textContent == "freezing rain"
			||weather.textContent == "heavy shower rain and drizzle"){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455055.mp4";
	
	} else if(weather.textContent == 'thunder storm'||weather.textContent == "thunderstorm with light rain"||weather.textContent == "thunderstorm with rain"||weather.textContent == "thunderstorm with heavy rain"||weather.textContent == "light thunderstorm"
			||weather.textContent == "heavy thunderstorm"||weather.textContent == "ragged thunderstorm"||weather.textContent == "thunderstorm with light drizzle"||weather.textContent == "thunderstorm with drizzle"||weather.textContent == "thunderstorm with heavy drizzle"){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455012.mp4";
	
	} else if(weather.textContent == 'snow'||weather.textContent == 'light snow'||weather.textContent == 'heavy snow'||weather.textContent == 'sleet'||weather.textContent == 'shower sleet'||weather.textContent == 'light rain and snow'
			||weather.textContent == 'rain and snow'||weather.textContent == 'light shower snow'||weather.textContent == 'shower snow'||weather.textContent == 'heavy shower snow'){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6455/6455058.mp4";
	
	} else if(weather.textContent == 'mist'||weather.textContent == 'smoke'||weather.textContent == 'haze'||weather.textContent == 'sand, dust whirls'||weather.textContent == 'fog'||weather.textContent == 'sand'||weather.textContent == 'dust'||weather.textContent == 'volcanic ash'
			||weather.textContent == 'squalls'){
		weather_img.src = "https://cdn-icons-mp4.flaticon.com/512/6454/6454995.mp4";
	}
}

let location_info ={ // 날씨 api에 사용할 객체
		seoul : [37.540705,126.956764],
		gyunggi : [37.567167,127.190292],
		gangwon : [37.555837,128.209315],
		chungnam : [36.557229,126.779757],
		chungbuk : [36.628503,127.929344],
		gyungnam : [35.259787,128.664734],
		gyungbuk : [36.248647,128.664734],
		jeonnam : [34.819400,126.893113],
		jeonbuk : [35.716705,127.144185],
		jeju : [33.364805,126.542671]
	}

for(let i=0; i<board_title.length; i++){ // 제목 누르면  상세페이지로(자유게시판)
	board_title[i].addEventListener("click",function(){
		this.parentNode.nextElementSibling.submit();
	})
}

for(let i=0; i<camping_title.length; i++){ // 제목 누르면 상세페이지로(모임게시판)
	camping_title[i].addEventListener("click",function(){
		this.parentNode.nextElementSibling.submit();
	})
}

//날씨 정보 불러올 api 주소
var apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.seoul[0]+"&lon="+location_info.seoul[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";

var get_weather = function () { // 날씨 정보 불러오기
$.ajax({
    url: apiURI,
    dataType: "json",
    type: "GET",
    async: "false",
    success: function(resp) {
        temp.textContent = (resp.main.temp- 273.15).toFixed(1);
        weather.textContent = resp.weather[0].description;
        wind_speed.textContent = resp.wind.speed;
        humidity.textContent = resp.main.humidity;
        clouds.textContent = resp.clouds.all;
        country.textContent = location_select.value;
        change_weather();
    }
})
}

get_weather();

location_select.addEventListener("change",function(){ //지역 선택 시 날씨 바뀌게
	if(this.value == '서울'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.seoul[0]+"&lon="+location_info.seoul[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '경기도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.gyunggi[0]+"&lon="+location_info.gyunggi[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '강원도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.gangwon[0]+"&lon="+location_info.gangwon[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '전라북도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.jeonbuk[0]+"&lon="+location_info.jeonbuk[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '전라남도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.jeonnam[0]+"&lon="+location_info.jeonnam[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '경상북도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.gyungbuk[0]+"&lon="+location_info.gyungbuk[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '경상남도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.gyungnam[0]+"&lon="+location_info.gyungnam[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '충청북도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.chungbuk[0]+"&lon="+location_info.chungbuk[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '충청남도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.chungnam[0]+"&lon="+location_info.chungnam[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}else if(this.value == '제주도'){
		apiURI = "https://api.openweathermap.org/data/2.5/weather?lat="+location_info.jeju[0]+"&lon="+location_info.jeju[1]+"&appid=3f7f94f1b2b4c677113b0861b730afff";
	}
	
	$.ajax({
	    url: apiURI,
	    dataType: "json",
	    type: "GET",
	    async: "false",
	    success: function(resp) {
	        temp.textContent = (resp.main.temp- 273.15).toFixed(1);
	        weather.textContent = resp.weather[0].description;
	        wind_speed.textContent = resp.wind.speed;
	        humidity.textContent = resp.main.humidity;
	        clouds.textContent = resp.clouds.all;
	        country.textContent = location_select.value;
	        change_weather();
	    }
	})
})
