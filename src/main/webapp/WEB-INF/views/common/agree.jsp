<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/resources/css/common/agree.css">
<!-- 글로벌 css -->
<link rel="stylesheet" href="/resources/css/global.css">
<!-- 구글 아이콘 닷닷닷 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

<title>회원가입</title>

</head>

<body>
	<!-- 진행상태 -->
	<div class="progressDiv">
		<span class="material-symbols-outlined one"> looks_one </span> 
		<span class="material-symbols-outlined oneDot"> more_horiz </span> 
		<span class="material-symbols-outlined"> looks_two </span> 
		<span class="material-symbols-outlined"> more_horiz </span> 
		<span class="material-symbols-outlined"> looks_3 </span>
	</div>
	<!--회원가입 컨테이너-->
	<div class="loginContainer">
		<!--회원가입 헤더-->
		<div class="loginHeader">
			<h1>회원가입</h1>
		</div>
		<!--모두 동의-->
		<div class="allAgreeDiv">

			<div class="allAgreeDivLeft">
				<input type="checkbox" name="" id="allAgreeCheckBox">
			</div>

			<div class="allAgreeDivRight">
				<h1>
					<span class="EmphasisSpan">(필수)</span>사이트 이용약관, <span class="EmphasisSpan">(필수)</span>개인정보 수집 및 이용, (선택)프로모션 정보 email 수신 에 모두 동의합니다.
				</h1>
			</div>

		</div>
		<!--사이트 이용약관 동의-->
		<div class="serviceAgreeDiv">

			<div class="serviceAgreeDivLeft">
				<input type="checkbox" name="" id="" class="agreeCheckBoxs">
			</div>
			<!--사이트 이용약관 동의 오른쪽-->
			<div class="serviceAgreeDivRight">
				<!--
		                    장 : h3
		                    조 : h4
		                    본문 : p
                -->
				<h2>
					<span class="EmphasisSpan">(필수)</span>서비스 이용약관
				</h2>

				<h3>제 1 장 일반 조건</h3>

				<h4>제 1 조 (목적)</h4>
				<p>본 서비스 약관(이하 “약관”)은 구트아카데미 회사(이하 “회사”) 및 회사 서비스 웹사이트(http://localhost:8080)에 접근 또는 기타 서비스를 이용하거나, 혹은 그 서비스(아래 정의)를 이용하는 고객의 (이하 “고객”) 권리와 의무 및 기타 이에 관련한 사항을 명시하는 것을 목적으로 합니다.</p>

				<h4>제 2 조 (용어 정의)</h4>
				<p>본 약관에서 사용하는 용어의 정의는 다음과 같습니다.</p>
				<p>① “서비스”라 함은 팀4프로젝트의 서비스 및 기타 서비스를 이용하는 것을 의미합니다.</p>
				<p>② “고객”이라 함은 ‘회사’의 서비스에 접속하여 본 약관에 따라 ‘회사’와 이용계약을 체결하고 ‘회사’가 제공하는 서비스를 이용하는 개인 또는 개인사업자, 법인, 공공기관 등을 말합니다.</p>
				<p>③ “아이디(ID)”라 함은 고객의 식별과 서비스 이용을 위하여 고객이 정하고 회사가 승인하는 문자와 숫자의 조합 또는 고객의 이메일 주소를 의미합니다. 보통 회원 가입 시 입력한 이메일이 아이디가 됩니다.</p>
				<p>④ “비밀번호”라 함은 고객이 설정한 아이디(ID)와 일치되는 고객임을 확인하고 비밀보호를 위해 고객 자신이 정한 문자 또는 숫자의 조합을 의미합니다.</p>
				<p>⑤ “고객의 정보”라 함은 고객이 회사의 서비스에 저장하는 정보(『국가정보화기본법』 제 3 조 제 1호에 따른 정보, 개인인 경우에는 개인정보와 신용정보를 포함합니다.)로써 고객이 소유 또는 관리하는 정보를 의미합니다.</p>
				<p>⑥ “영업일”이라 함은 대한민국 내 위치한 시중 은행이 실제 영업하는 날을 의미합니다.</p>

				<h4>제 3 조 (약관의 게시 및 수정)</h4>
				<p>① 약관은 회사의 서비스 웹사이트(http://localhost:8080)에서 확인 가능합니다. 약관은 고객이 회사의 서비스 웹사이트에 접속 또는 이용할 때 그리고 서비스 계약(아래 제 5 조 제 1 항에서 정의한 바에 따름) 성립에 따라 제공되는 서비스에 적용됩니다. 고객이 회사 서비스 웹사이트에 접속 또는 이용하기 위해서는 약관에 동의해야 합니다.</p>
				<p>② 회사는 ‘약관의 규제에 관한 법률’, ‘정보통신망이용촉진 및 정보보호 등에 관한 법률’, ‘클라우드 컴퓨팅 발전 및 이용자보호에 관한 법률’ 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.</p>
				<p>③ 회사는 회사의 서비스 웹사이트 내 수정 약관의 게시 등 합리적인 방법을 통해 약관을 수정할 수 있습니다. 이 때 회사는 약관 수정 사항의 효력이 발생하기 최소 7 일 전에 효력 발생일과 자세한 내용을 명시하여 회사 서비스 웹사이트를 통해 이를 공지합니다. 단, 수정 사항이 고객의 권리 및 의무와 관련해 불리하게 작용하는 경우 회사는 제7조 제1항에 명시된 바에 따라 효력 발생일 최소 30 일 이전에 해당 고객에게 이를 통지합니다.</p>
				<p>④ 회사가 전항에 따라 개정약관을 공지 또는 통지하면서 고객에게 30 일 기간 내에 의사표시를 하지 않으면 의사표시가 표명된 것으로 본다는 뜻을 명확하게 공지 또는 통지하였음에도 고객이 명시적으로 거부의 의사표시를 하지 아니한 경우, 고객이 개정 약관에 동의한 것으로 봅니다.</p>
				<p>⑤ 고객이 개정약관의 적용에 동의하지 않는 경우 회사 또는 고객은 이용계약을 해지할 수 있습니다. 이 경우 회사는 고객에게 서면, 전자우편 또는 이에 준하는 방법으로 해지 사유, 해지일, 환급 비용을 통지합니다.</p>

				<h4>제 4 조 (약관에 명시되지 않은 사항)</h4>
				<p>① 본 약관에서 정하지 아니한 사항은 준거법, 규정, 상관습 및 서비스 웹사이트 내 개별서비스 운영정책(해당 시)에 따릅니다. 오해의 소지를 피하기 위해 부연하자면, 회사는 각각의 개별서비스에 대한 별도 운영정책을 실시할 수 있습니다.</p>
				<p>② 회사는 개별서비스에 적용될 사항을 정하여 개별 약관으로 운영할 수 있습니다. 개별 약관의 내용이 본 약관과 상충할 경우 별도로 정한 바가 없다면 개별 약관이 우선 적용됩니다.</p>
				<p>③ 개별 약관에 규정이 없는 경우 본 약관에 따릅니다.</p>

				<h3>제 2 장 서비스 이용</h3>

				<h4>제 5 조 (서비스 이용의 신청)</h4>
				<p>① 서비스 이용 신청자(“신청인”)가 약관에 동의한 후 서비스 이용을 신청하고, 회사가 이에 승인을 함으로써 서비스 이용계약(“서비스 계약”)이 성립합니다.</p>
				<p>② 서비스 이용 신청 시 신청인은 정확한 개인정보(이메일, 필요할 경우 실명과 연락처)를 제공해야 합니다. 신청인이 잘못된 이메일을 사용하거나 거짓 정보를 제공한 경우에는 약관에서 정한 권리를 누리거나 주장할 수 없으며, 회사는 서비스 계약을 해지할 수 있습니다.</p>
				<p>③ 신청인이 서비스 신청 시 제공한 개인 정보는 준거법 및 규정, 회사의 개인정보보호 정책에 따라 보호됩니다.</p>
				<p>④ 신청인이 민법상 미성년자인 경우 법정대리인(보호자)의 동의가 있을 경우 서비스 이용이 가능하며, 회사는 미성년자의 서비스 신청을 거절할 수 있습니다. 고객은 서비스를 이용함으로써 미성년자가 아님을 회사에 확인할 수 있습니다.</p>

				<h4>제 6 조 (서비스 계약의 성립)</h4>
				<p>① 신청인이 상기 제 5 조에서 명시한 정보를 성실하고 정확하게 작성하면 회사는 특별한 사유가 없는 한 신청을 승인합니다.</p>
				<p>② 신청을 접수한 이후 다음과 같은 사항에 해당하는 경우, 회사는 서비스 이용 신청 승인을 거부하거나 연기할 수 있으며 또는 서비스 계약을 해지할 수 있습니다.</p>
				<p>1. 신청인이 잘못된 이메일을 사용하거나 타인의 정보나 명의를 이용한 경우</p>
				<p>2. 신청인이 거짓 정보를 제공하거나 회사가 요구하는 정보를 제출하지 않은 경우</p>
				<p>3. 신청인이 법률 위반 또는 불법 행위 등 부정한 용도로 서비스를 이용하려 하는 경우</p>
				<p>4. 신청인의 귀책 사유로 인해 신청의 승인이 불가하거나, 신청이 회사의 정책에 위반하는 경우</p>
				<p>5. 신청인이 회사에 대해 납부할 금액이 있는 경우</p>
				<p>6. 신청인이 서비스 이용요금(“이용요금”)를 체불하거나 부적절한 서비스 이용 내역이 있는 경우</p>
				<p>7. 그밖에 제 1 호에서 제 6 호까지에 준하는 사유로써, 승낙하는 것이 상당히 부적절하다고 판단되는 경우</p>
				<p>③ 회사가 서비스 이용 신청을 거부하거나 연기할 것을 결정한 경우 신청인에게 이를 통지합니다. 통지가 불가능할 경우 통지를 생략할 수 있습니다.</p>

				<h4>제 7 조 (고객에 대한 통지)</h4>
				<p>① 회사가 고객에게 통지해야 하는 경우, 회사는 약관에서 별도로 명시하지 않는 한 서비스 이용 신청 시 고객이 등록한 이메일 주소 또는 사이트 안내 메세지 이용하여 통지합니다.</p>
				<p>② 고객 전체에 통지해야 하는 경우, 회사는 상기 제 1 항에서 명시한 방식 대신 서비스 웹사이트에 7 일 이상 공지함으로써 통지를 대신할 수 있습니다. 단, 고객의 권리 및 의무와 관련해 불리한 변경 사항을 통지하는 경우에는 본 조항이 적용되지 않습니다.</p>
				<p>③ 고객은 회사의 통지 사항을 수신할 수 있도록 연락처 정보(이메일 주소, 휴대폰 번호, 유선 전화번호 등)를 항상 최신 상태로 유지할 의무가 있습니다. 연락처 정보가 업데이트 되어 있지 않아 고객에게 발생하는 불이익에 대해 회사는 어떠한 책임도 지지 않습니다.</p>

				<h4>제 8 조 (개별서비스의 유형 및 변경)</h4>
				<p>① 회사는 개별서비스의 종류, 세부 사항 및 가격뿐만 아니라 개별서비스의 이용약관, 개별서비스, 운영 규칙 등을 포함한 세부 사항을 서비스 웹 사이트에 게시합니다. 고객은 서비스 웹사이트에 게재된 세부 사항을 충분히 숙지하고 관련 정보를 바탕으로 서비스를 신청해야 합니다.</p>
				<p>② 서비스 웹사이트 상에 새로운 정보를 게재하거나 제 7 조에 따라 고객에게 통지하는 경우에 한해, 회사는 개별서비스의 형태 또는 가격을 추가하거나 변경할 수 있습니다. 오해의 소지를 피하기 위해 부연하자면, 변경사항이 고객의 권리 및 의무에 불리하게 작용할 수 있는 경우 고객은 제 7 조에 명시된 조건에 따라 이를 통지 받으며 제 3 조 제 5 항에 명시된 권리를 보유합니다.</p>
				<p>③ 서비스가 종료되는 경우 회사는 서비스가 종료되기 60 일 이전에 만료 또는 종료 사실을 통지합니다.</p>


				<h4>제 9 조 (서비스 제공)</h4>
				<p>① 원칙적으로 회사는 하루 24 시간, 주 7 일의 서비스를 제공합니다. 단, 설비의 점검 등 회사가 필요한 경우 또는 설비의 장애, 서비스 이용의 폭주 등 불가항력 사항으로 인하여 서비스 이용에 지장이 있는 경우 예외적으로 서비스 이용의 전부 또는 일부를 제한할 수 있습니다.</p>
				<p>② 서비스 제공에 필수적인 경우 회사는 정기점검을 실시할 수 있고 그 일정은 서비스 웹사이트 또는 커뮤니티 사이트(네이버 카페 등)에 공지한 바에 따릅니다.</p>
				<p>③ 회사가 제공하는 인프라서비스를 이용하여 고객이 저장하는 정보 또는 데이터는 고객이 소유 또는 관리하며 회사는 고객의 동의 없이 이에 접근하지 않습니다.</p>

				<h4>제 10 조 (고객 계정 관리)</h4>
				<p>① 고객은 자신의 ID 및 비밀번호를 관리하고 제 3 자가 이용하지 못하도록 방지할 책임이 있습니다.</p>
				<p>② 고객은 자신의 ID 및 비밀번호가 도용된 사실 또는 제 3 자가 이용한 사실을 알게 된 경우, 해당 사실을 회사에 즉시 이를 알리고 회사의 가이드라인에 따릅니다.</p>
				<p>③ 회사는 개인정보가 무단으로 공개 또는 유출될 위험이 있는 경우 또는 ID 가 회사 또는 회사 운영자와 연관되어 있는 것처럼 오해되는 경우 ID 이용을 제한할 수 있습니다.</p>
				<p>④ 본 조의 의무를 위반하여 발생하는 모든 책임은 고객에게 있습니다. 고객이 본 조의 의무를 위반하거나 회사 지침 또는 가이드라인을 따르지 않아 발생하는 불이익에 대하여 회사는 책임을 지지 않습니다.</p>
				<p>⑤ 회사는 개별 고객이 회사의 정책에 따라 보유할 수 있는 계정의 수를 제한할 수 있습니다. 상세한 내용은 서비스 홈페이지에 공지한 바에 따릅니다.</p>

				<h4>제 11 조 (고객 개인정보의 변경)</h4>
				<p>① 고객은 서비스 웹사이트의 개인정보 관리 페이지에서 언제든 개인정보를 확인하고 수정할 수 있습니다. 그러나, 서비스 관리 상의 필수 정보는 임의 수정이 불가합니다.</p>
				<p>② 서비스 이용 신청이 제출된 이후 고객 정보가 바뀌면 고객은 서비스 웹사이트 상에서 정보를 수정하거나 이메일 또는 기타 방법을 통해 이를 회사에 알려야 합니다.</p>
				<p>③ 고객이 회사에 제공한 개인정보가 정확하지 않거나 제 2 항의 변경사항을 회사에 알리지 않아 발생한 불이익에 대하여 회사는 책임지지 않습니다.</p>


				<h4>제 12 조 (양도 및 관련 고객 정보 변경)</h4>
				<p>① 어떠한 고객도 임의로 서비스 계약 상의 권리 및 의무 또는 본 계약에서 이용하는 기타 권리를 타인에게 이전하거나 양도, 담보 설정 등으로 처분할 수 없습니다.</p>
				<p>② 고객에게 상속, 합병, 분할의 사유가 발생하여 고객이 아닌 제 3 자(이하 “양수인”)가 고객이 회사와 맺은 서비스 계약에 따른 법적 지위를 승계하는 경우 고객 및 양수인은 즉시 회사에 지위 승계를 입증할 수 있는 서류를 첨부하여 회사가 지정한 방법과 절차에 따라 통지하여야 합니다.</p>
				<p>③ 상기 제 2 항에 따른 고객 정보 변경의 경우, 양수인은 승계 이전에 약관 및 서비스 계약의 조건들을 완전히 이행할 책임이 있습니다. 승계와 관련하여 문제가 발생하는 경우, 고객과 양수인이 연대하여 책임을 집니다.</p>

				<h4>제 13 조 (회사에 의한 서비스 이용의 정지 또는 종료)</h4>
				<p>① 회사는 다음 각호의 사유에 해당하는 경우 사전 통지 없이 고객의 서비스 이용을 정지할 수 있습니다.</p>
				<p>1. 고객이 요금을 납부 월까지 납입하지 않은 경우</p>
				<p>2. 안정된 서비스 운용을 저해할 수 있는 다량의 정보 전송, 광고성 정보를 전송 또는 매개하는 행위를 하거나 이러한 행위가 발생하는 경우</p>
				<p>3. 고객 서비스 이용/접속으로인해 다른 고객 시스템의 운영에 피해가 발생하거나, 회사의 서비스 운영에 지장 또는 장애가 발생한 경우 또는 그러한 피해 내지 지장, 장애가 발생할 위험이 있는 경우</p>
				<p>4. 고객이 서비스를 이용하여 비정상 과다 트래픽이 발생하여 네트워크에 영향을 끼치는 경우</p>
				<p>5. 고객의 서비스 이용이 회사의 서비스 운영에 위험요소로 판단되는 경우</p>
				<p>6. 고객이 국가적 이익이나 공익에 반하는 목적을 위해 서비스를 이용하는 경우</p>
				<p>7. 고객의 서비스 이용이 관련 법령을 위반하거나 공공 윤리나 질서에 반하는 경우</p>
				<p>8. 고객의 행위가 타인의 명예를 손상시키거나 불이익을 초래하는 경우</p>
				<p>9. 고객이 이용중인 장비가 바이러스에 감염 또는 해킹되었거나 그러한 것으로 의심되는 경우</p>
				<p>10.서비스를 통해 고객이 처리하는 고객의 정보에 대해 제 3 자가 권리 침해를 주장하는 등 (이를 포함하되 이에 한정 짓지 않음) 고객의 서비스 이용을 정지시킬 만한 법적 근거가 있는 경우</p>
				<p>11.법적 절차에 따라 정부기관이 서비스의 일시적 이용 정지를 요청 또는 명령하는 경우</p>
				<p>12.고객이 회사가 서비스 이용을 위해 정한 조건, 서비스 이용과 관련하여 고지한 제약 또는 제한 사항을 회피하는 방식(예를 들면, 무료로 서비스를 이용하거나 특가 혜택을 받기 위하여 복수의 회사 계정을 생성하거나 필수 절차를 무단으로 회피 등)으로 본건 서비스에 접근하거나 사용하는 경우</p>
				<p>13.반복적으로 환불 처리하며 환불 정책을 악용하여 부당하게 서비스를 이용한 경우</p>
				<p>14.기타 관련 법령이나 회사가 정한 이용약관, 이용조건에 위배되는 경우</p>
				<p>② 상기 제 1 항에 따른 사유로 인해 고객의 서비스 이용을 정지시키는 경우, 회사는 해당 고객에게 정지 사유, 이용 정지 기간 및 공식적인 이의 제기 수단을 통지합니다. 회사는 제 1 항과 관련한 사유가 제거되거나 더 이상 유효하지 않은 경우 지체 없이 고객의 서비스 이용 권리를 회복시킵니다.</p>
				<p>③ 이용 정지 사유가 계속해서 존재하는 경우 회사는 해당 서비스 계약을 해지할 수 있습니다. 이 경우 회사는 제 7 조에 명시된 방법에 따라 고객에게 통지합니다.</p>
				<p>④ 회사는 본 조에서 기술된 사유로 인해 서비스가 정지된 기간 동안 발생한 요금을 청구할 수 있습니다.</p>

				<h4>제 14 조 (서비스 중단)</h4>
				<p>① 다음에 해당하는 경우 회사는 서비스 제공을 중단할 수 있습니다.</p>
				<p>1. 서비스 제공과 관련된 회사와 제 3 자간의 계약의 종료/해지, 설비 수리 및 기타 유지보수 작업 등 불가피한 사유가 발생한 경우</p>
				<p>2. 국가적 비상사태, 설비 결함 또는 서비스 이용 급증 등으로 정상적인 서비스 운영에 장애가 발생할 위험이 있는 경우</p>
				<p>3. 천재지변, 화재 등 불가피한 사유로 인해 회사가 실질적으로 안정적인 서비스를 제공할 수 없는 경우</p>
				<p>4. 전기통신 서비스 제공업체(전기통신사업법에 명시)가 통신 서비스를 중단한 경우</p>
				<p>② 회사는 상기 제 1 항 제 1 호에 명시된 사항에 해당하는 경우 사전에 고객이 지정한 통지 방법을 이용해 또는 서비스 접속화면이나 서비스 웹사이트 상에 게재함으로써 서비스 중단을 공지합니다. 단, 회사가 통제할 수 없는 사유로 인한 서비스의 중단(회사의 고의, 과실이 없는 설비 장애, 시스템 장애 등)으로 인하여 사전 통지가 불가능한 경우에는 그러하지 아니합니다</p>
				<p>③ 회사는 상기 제1항 제2호, 제3호, 제4호에 명시된 사항에 대해서는 사전 통지 없이 해당 서비스를 일시 중단할 수 있습니다.</p>

				<h4>제 15 조 (고객에 의한 서비스의 중지 또는 해지)</h4>
				<p>① 고객이 서비스 계약을 중지하거나 해지하기 원하는 경우, 서비스 웹사이트에 게시한 절차와 방법을 통해 회사에게 이를 통지해야 합니다. 회사는 정상적인 조건 하에서 고객이 요청한 날짜에 서비스를 중지시키거나 서비스 계약을 종료합니다. 그러나 고객이 계약 해지를 요청한 날 납부해야 할 이용 요금이 남아 있다면, 해당 고객이 이용 요금을 납부 완료한 후에야 해지 절차를 진행할 수 있습니다.</p>
				<p>② 유료 서비스 이용 일시 정지는 지원하지 않습니다.</p>
				<p>③ 고객은 서비스 이용 기간 동안 사용해온 서비스에 저장된 데이터를 서비스 계약 해지 이전에 직접 백업해야 합니다. 회사는 서비스 계약 해지와 동시에 고객의 모든 자원(서버 등) 및 자료를 삭제합니다. 삭제된 자원과 자료는 어떠한 이유로든 복구되지 않습니다.</p>

				<h4>제 16 조 (회사의 서비스 이용계약의 직권 해지)</h4>
				<p>① 다음에 해당하는 경우, 회사는 고객과 추가 협의 없이 고유 재량으로 서비스 계약을 해지할 수 있습니다.</p>
				<p>1. 고객이 서비스 계약에 명시된 기간 내 미납 이용 요금과 관련하여 적절한 조치를 취하지 않는 경우</p>
				<p>2. 서비스 이용 신청서에 기입한 정보가 거짓으로 판명되는 경우</p>
				<p>3. 고객이 회사의 서비스 운영을 고의적으로 방해하여 회사에 손실을 입히는 경우</p>
				<p>4. 고객이 회사의 서비스를 가상 화폐 채굴 용도로 이용하는 경우</p>
				<p>5. 제 26 조(고객의 의무)에서 정한 고객의 의무를 위반하고 있으며, 회사의 해소 요구에 대하여 상당한 기간 동안 적절한 조치를 취하지 않고 있는 경우</p>
				<p>6. 제 13 조(이용의 정지)에 대한 회사의 해소 요구에 대하여 상당한 기간 동안 적절한 조치를 취하지 않고 있는 경우</p>
				<p>7. 고객의 서비스 이용 목적 및 방법이 국내 및 국제법을 위반하여 정부 기관이 적법한 절차를 통해 해당 서비스 종료를 요청하는 경우</p>
				<p>8. '개인정보 유효 기간제'에 따라 1 년간 서비스를 이용하지 않은 '고객'의 개인정보 보호를 위하여 해당 정보의 삭제가 필요한 경우</p>
				<p>② 상기 제 1 항 제 1 호, 제 2 호, 제 4 호 또는 제 5 호의 경우, 회사는 고객에게 명시된 기간 내 문제 해결을 요청할 수 있습니다. 그럼에도 불구하고 고객이 문제 해결에 필요한 조치를 취하지 않는 경우 회사는 서비스 계약을 종료할 수 있습니다.</p>
				<p>③ '서비스 계약' 후 1 년간 이용 내역이 없는 '개별 서비스'의 경우, 고객 통지 후 해당 '개별 서비스'에 대한 계약을 해지 할 수 있습니다. 다만, 이 경우 고객 사전 통지는 제 7 조에 정한 방식으로 진행합니다.</p>

				<h4>제 17 조(중요 정보 알림)</h4>
				<p>① 중요한 정보가 있을 경우 회사는 메일 및 기타 방법을 이용하여 사용자에게 알릴 수 있으며 사용자는 이러한 메일 및 기타 방법으로 전송되는 중요 정보 수신에 동의합니다.</p>

				<h3>제 3 장 이용요금 및 관리</h3>

				<h4>제 18 조 (서비스 이용요금)</h4>
				<p>① 회사는 이용요금 및 그 변경사항을 서비스 웹사이트상에 게시합니다.</p>
				<p>② 서비스는 크게 약정제 서비스와 월단위 서비스로 구분됩니다.</p>
				<p>③ 특별한 사유가 있지 않는 한 서비스 계약 기간 동안 이용요금이 변경되더라도 변경된 이용요금은 서비스 계약 기간 전체에 걸쳐 소급 적용되지 않습니다.</p>
				<p>④ 회사는 서비스 이용 시 활용할 수 있는 크레딧 또는 코인(이하 “크레딧 등”)을 고객 상대로 발행할 수 있습니다.</p>

				<h4>제 19 조 (서비스 이용 요금 산정 및 정산)</h4>
				<p>① 서비스 이용요금은 1달 단위로 산정되어 청구되며 민법 160조 [역에 의한 계산]에 근거하여 계산 됩니다.</p>
				<p>② 서비스 이용요금은 1달 미만인 경우 해당 서비스 1개월 요금을 30일로 나눈 금액으로 일할계산합니다.</p>

				<h4>제 20 조 (환불 및 해지 수수료)</h4>
				<p>① 환불 규칙은 아래와 같습니다.</p>
				<p>1. 환불 금액 계산은 실제 일 수를 기준으로 이용한 기간과 남은 기간을 계산 합니다.</p>
				<p>2. 서비스 업그레이드, 사용자 추가, 기간 추가와 같은 추가 결제의 경우 개별적으로 환불하지 않으며 전체 금액을 대상으로 환불 금액이 계산되어 한 번에 환불 됩니다.</p>
				<p>② 해지 수수료는 다음과 같이 산정됩니다.</p>
				<p>1. 서비스 계약 시작 후 24시간 이내에 해지하는 경우 100% 환불이 이루어집니다. 단, 부당하게 서비스를 이용하기 위한 목적의 반복적인 환불의 경우 100% 환불에 해당되지 않을 수 있으며 계약 해지, 서비스 이용 금지 등의 불이익이 발생할 수 있습니다.</p>
				<p>2. 서비스 계약 시작 후 24시간이 지난 경우 환불 수수료 500원을 공제한 후 남은 기간에 대하여 환불합니다.</p>
				<p>3. 할인을 받아서 결제한 경우 할인전 가격을 기준으로 이용 기간에 따라 차감된 후 남은 금액이 환불 됩니다.</p>
				<p>③ 다음에 해당하는 경우, 고객은 전항의 해지 수수료를 납부하지 않고 서비스 계약을 해지할 수 있습니다.</p>
				<p>1. 회사의 귀책 사유로 인해 누적 서비스 장애가 한 달 동안 72 시간을 초과한 경우</p>
				<p>2. 회사의 귀책 사유로 인해 1 시간 이상 지속되는 서비스 장애가 한 달 동안 5 회 이상 발생한 경우</p>
				<p>④ 반복적으로 환불 처리하며 환불 정책을 악용하여 부당하게 서비스를 이용한 경우 회사에 의한 서비스 이용의 정지 또는 종료 및 기타 제재가 있을 수 있습니다.</p>

				<h4>제 21 조 (이용요금 납부 및 청구)</h4>
				<p>① 고객은 서비스 이용 전에 이용 요금을 납부해야 합니다.</p>
				<p>② 서비스 이용에 대한 이용요금 납입 수단은 결제 화면에서 지원하는 수단 중 하나를 선택합니다.</p>
				<p>③ 고객이 서비스 시작일 전까지 이용요금을 납부하지 않는 경우, 회사는 약관에 명시되고 서비스 웹사이트에 게시된 절차와 방법에 따라 서비스를 해지할 수 있습니다.</p>

				<h4>제 22 조 (이용요금 납입의 책임)</h4>
				<p>고객은 서비스 이용요금을 납입할 책임이 있습니다. 단, 고객이 이용요금 납입 책임자를 지정한 경우 고객과 이용요금 납입 책임자가 이용요금 납입에 대해 연대책임을 집니다. 이 경우 고객은 납입 책임자의 책임 확인서를 제공하여야 합니다.</p>

				<h4>제 23 조 (청구서에 대한 이의제기)</h4>
				<p>① 고객이 청구된 이용요금에 동의하지 않는 경우, 고객은 서면 또는 이메일을 통해 회사에 대해 공식적으로 이의를 제기할 수 있습니다.</p>
				<p>② 회사는 제 1 항의 공식 이의제기 문서를 접수한 이후 7 영업일 이내에 검토하여 고객에게 그 결과를 알립니다. 상기 언급한 이의제기와 관련한 결과를 정해진 기간 내에 고객에게 통지하지 못하는 경우, 회사는 고객에게 지연 사유를 통지합니다.</p>

				<h4>제 24 조 (이용요금의 환불)</h4>
				<p>① 회사는 고객이 납부한 이용요금에 과오납이 있는 경우 과오납된 요금액을 고객의 다음 달 이용 요금에서 상계할 수 있습니다.</p>
				<p>② 제 1 항의 상계 이전에 고객이 과오납금액에 대하여 환불을 요구하는 경우 회사는 고객에게 해당 금액을 반환합니다.</p>

				<h3>제 4 장 계약당사자의 의무</h3>

				<h4>제 25 조 (회사의 의무)</h4>
				<p>① 회사는 안정적이고 지속적인 방식으로 고객이 요청하는 서비스를 제공하기 위해 노력합니다.</p>
				<p>② 서비스의 정상 운영에 지장을 주는 장애가 발생하는 경우, 회사는 실행할 수 있는 최대한 빨리 정비 또는 복구하고 서비스를 안정적으로 운영하는데 최선을 다합니다.</p>
				<p>③ 회사는 고객이 제기한 의견이나 불만을 공정하게 즉시 또는 기간 내에 처리하며, 회사가 정한 절차에 따릅니다.</p>
				<p>④ 서비스를 원활히 운영하기 위하여 회사는 서비스 웹사이트에 게시된 개인정보보호정책에 따라 고객 개인정보를 수집하고 보관할 수 있습니다. 회사는 고객의 동의 없이 고객의 개인정보를 제 3 자에게 제공하지 않습니다. 단, 관련법 및 규정에 따라 조사의 목적 등으로 법원 또는 기타 사법 기관이 발행하는 영장 등을 통해 고객 개인정보 제공을 요청 받을 경우는 예외로 할 수 있습니다.</p>
				<p>⑤ 회사는 이용약관에 벗어난 목적으로 서비스와 관련한 고객의 정보에 접근하거나 데이터를 처리하지 않습니다. 다만 장애 처리, 고객의 정보 보호 등 원활한 서비스 제공을 위하여 접근이 필요한 경우 고객의 정보에 접근, 내용을 파악할 수 있습니다.</p>
				<p>⑥ 회사는 제 5 항에 따라 파악한 고객의 정보에 대하여 원활한 서비스 제공을 위하여 삭제, 변경 등 데이터 처리가 필요한 경우 고객의 동의를 받습니다. 다만, 고객의 동의가 없거나 동의를 하지 않더라도 회사의 서비스 운영 및 다른 고객의 서비스 이용에 방해가 되는 경우 회사는 해당 고객의 서비스 이용을 정지할 수 있으며, 그 절차는 제13조 제 2항 이하에서 정한 바에 따릅니다.</p>

				<h4>제 26 조 (고객의 의무)</h4>
				<p>① 고객은 명시된 지불기일 또는 그 이전에 이용요금을 납부해야 합니다.</p>
				<p>② 고객은 회사의 서비스 운영 또는 다른 고객의 서비스 이용을 방해하거나 제3자의 권리를 침해해서는 안됩니다. 이와 관련하여 회사는 서비스 웹사이트에 고객의 불법행위 등을 회사에 알리거나 신고할 수 있는 메뉴를 설치하여 운영할 수 있습니다.</p>
				<p>③ 고객은 서비스를 통해 운영하는 사이트 또는 게시판 등을 음란 정보, 불법정보, 유해정보, 불법도박정보 등을 유통, 게재, 링크하기 위한 목적으로 사용할 수 없으며, 법률상의 불법적인 행위를 할 수 없습니다. 또한 고객이 서비스를 이용하여 취급하는 서비스 및 정보 등 고객의 정보와 관련한 소유∙관리 등 일체의 책임은 고객 본인에게 있습니다.</p>
				<p>④ 서비스를 이용하여 제 3 자의 개인정보를 처리, 관리, 이용 또는 이에 접속하는 경우, 고객은 관련법 및 규정을 준수하여 해당 개인정보를 관리하고 보호해야 하며, 회사는 이와 같은 행위로 인해 발생하는 제 3 자의 정보 유출 등을 포함하되 이에 한정되지 않는 결과, 손실 또는 손해에 대하여 책임을 지지 않습니다.</p>
				<p>⑤ 고객은 이용 중인 장비가 침입자로부터 안전하게 보호받을 수 있도록 정기적인 보안 업데이트를 해야 합니다. 고객이 별도로 시스템 보안 서비스와 관련한 계약을 회사와 체결한 경우를 제외하고는, 회사는 발생한 보안 사고에 대하여 책임을 지지 않습니다. 회사는 고객의 서비스 이용 관련 보안조치를 위해 보안관제업무를 수행할 수 있으며, 이를 위해 고객 정보에 접근∙ 내용을 파악하여 그 결과를 고객에게 통보하고 보안성 강화조치를 요구할 수 있습니다. 고객은 회사의 보안성 강화 요청에 응해야 합니다.</p>
				<p>⑥ 고객은 고객의 과실로 발생하는 저작권 문제 등에 대해 전적으로 책임을 져야 합니다.</p>
				<p>⑦ 고객이 서비스를 통해 불법 소프트웨어를 발송하여 다른 고객 또는 제 3 자에게 피해를 준 경우, 회사는 이에 대한 책임이 없으며 해당 고객은 회사를 면책 시키고 의무, 손실, 손해배상, 회사를 상대로 제기되는 소송 등으로부터 회사가 피해를 입지 않도록 해야 합니다.</p>
				<p>⑧ 고객은 서비스, 여타 컴퓨터 코드, 파일, 또는 프로그램의 안정적인 운영을 방해하거나 파괴시키는 소프트웨어 바이러스를 포함하는 데이터를 게시 또는 전송해서는 안됩니다.</p>
				<p>⑨ 고객은 준거법, 약관, 회사의 서비스 웹사이트와 지침에 명시되어 있는 가이드라인과 예방조치 등을 완벽하게 숙지하고 체득하여 준수할 의무가 있습니다. 고객은 회사의 업무에 방해되는 행위를 하여서는 안됩니다.</p>
				<p>⑩ 고객은 서비스를 이용하면서 취급하는 데이터를 백업하고 저장할 의무가 있으며 데이터 관리 소홀로 인한 손실이 발생한 경우 이에 대한 책임을 져야 합니다. 단, 회사가 제공하는 별도의 백업 서비스를 신청하고 이용하는 고객의 경우 백업 서비스 조항에 명시된 범위 내에서 회사가 데이터 관리 소홀에 대한 책임을 집니다.</p>
				<p>⑪ 고객은 회사의 동의없이 제 3 자에게 서비스를 재판매 혹은 재임대할 수 없습니다.</p>
				<p>⑫ 회사의 서비스 웹사이트에 포함된 모든 콘텐츠[텍스트, 그래픽, 로고, 버튼 아이콘, 이미지, 오디오 클립, 디지털 다운로드, 데이터 컴필레이션(data compilations) 및 소프트웨어 등]는 회사 또는 그 콘텐츠 제공자의 자산이며, 저작권법 등 관련 법령에 의해 보호됩니다. 고객은 회사와의 별도 합의가 있거나 회사의 명시적인 서면 동의가 있는 경우를 제외하고, 콘텐츠의 전부 또는 일부를 상업적 목적으로 복제, 복사, 사용할 수 없으며, 다운로드 또는 변경하거나 재판매를 하여서는 안됩니다. 또한, 고객의 콘텐츠 사용권한에는 데이터 마이닝, 로봇이나 이와 유사한 데이터 수집 및 추출 도구의 사용이 포함되지 않습니다.</p>

				<h3>제 5 장 손해배상</h3>

				<h4>제 27 조 (회사의 손해배상)</h4>
				<p>① 회사는 회사의 귀책사유로 인하여 "개별 서비스"에 대하여 회사가 통상적인 서비스 수준을 달성 및 유지하지 못한 경우에 배상을 할 수 있습니다. 이 경우 결제 금액을 초과하여 배상하지 않습니다.</p>
				<p>② 고객의 기대이익과 같은 간접적/부수적인 손실에 대하여는 회사가 책임을 부담하지 않습니다.</p>
				<p>③ 본 조항은 회사의 서비스를 유료로 이용하는 고객에게만 적용되며, 무료 서비스 또는 이벤트 등으로 서비스를 무료로 이용하는 고객에게 발생한 손해에 대해서는 회사가 책임지지 않습니다.</p>

				<h4>제 28 조 (고객의 손해배상)</h4>
				<p>① 고객은 다음과 같은 사유로 분쟁이 발생하는 경우 회사, 계열사, 각각에 해당하는 임직원, 대리인, 협력사 및 라이선스 사용허가권자를 면책시키고 방어하며, 이들이 피해를 입지 않도록 하는데 동의합니다.</p>
				<p>1. 고객이 약관, 준거법 및 규정 위반 또는 침해</p>
				<p>2. 회사가 약관 위반으로 의심되는 것을 조사하거나 약관 위반이 발생한 것으로 판단하여 취하는 조치</p>
				<p>② 고객이 회사에 대하여 손해를 배상하게 되는 경우 회사의 법률 비용, 경비 그리고/또는 상기 명시한 청구에서 비롯되는 손해배상액을 포함하되 이에 한정되지 않습니다.</p>
				<p>③ 회사가 고객에 대해 손해배상을 청구하는 경우의 절차에 관해서는 제 27 조의 규정을 준용합니다.</p>

				<h4>제 29 조 (법적 책임 제한)</h4>
				<p>① 다른 규정에도 불구하고, 다음에 해당하는 경우 그것이 예측 가능한 상황이라 할지라도 회사는 계약 상이든 불법 행위(과실 포함) 상이든 이와 관련하여 발생하는 어떠한 손실이나 손해에 대하여 준거법이 허용하는 최대한의 범위 내에서 책임을 지지 않습니다.</p>
				<p>1. 천재지변이나 화재, 전쟁 등 불가항력에 의한 경우</p>
				<p>2. 고객의 고의(미필적 고의 포함), 부작위 또는 과실에 의한 경우</p>
				<p>3. 회사 이외의 서비스 제공업체가 제공한 통신 서비스 등에 대한 장애</p>
				<p>4. 회사가 고의 또는 중과실로 서비스를 중단한 경우를 제외하고, 서비스 진단 등 부득이한 사정으로 인해 사전 공지 후 서비스를 중단한 경우</p>
				<p>5. 회사가 제 9 조에 따라 사전 공지된 정기 점검 실행을 위해 서비스를 중단한 경우</p>
				<p>6. 고객의 이용이 서비스 계약에서 정의하는 서비스 범위를 초과하는 경우</p>
				<p>7. 고객이 서비스에 게시한 정보의 부정확, 부적법함 등으로 인해 고객 자신 또는 제 3 자에게 손해가 발생한 경우</p>
				<p>8. 고객의 시스템 보안 관리 소홀로 인하여 침해가 발생한 경우</p>
				<p>9. 국가적 비상사태, 전국적 네트워크 장애, 또는 이에 상응하는 불가항력으로 인해 서비스가 중단된 경우</p>
				<p>10.서비스를 이용하는 고객의 시스템에서 발생한 사고의 확산을 방지하기 위해 서비스가 중단되는 경우</p>
				<p>11.회사가 관련 법령에 따른 보호조치를 취하였음에도 외부로부터의 불법적 침해로 인해 서비스 장애가 발생한 경우</p>
				<p>12.고객의 정보시스템에서 발생한 사고의 확산을 방지하기 위해 서비스가 중단되는 경우</p>
				<p>13.회사 서비스를 이용하는 고객의 장비, 소프트웨어, 애플리케이션 또는 OS 에서 장애가 발생하는 경우</p>
				<p>14.고객이 무료 서비스를 이용하는 경우</p>
				<p>② 계약 또는 불법행위(과실 포함), 기타에서 비롯되는 모든 손해, 손실 및 소송 사유에 대하여 회사가 책임지는 최대 누적 배상 금액은 (A) 본 조건 하에서 발생한 소송 사유의 요인이 되는 상황의 발생일 직전 6 개월 내 서비스 이용을 위해 고객이 회사에 납부한 총 합계 금액, 그리고 (B) 미화 1 천 달러 중에서 큰 금액입니다. 법적 책임 제한은 어떠한 종류이든(본 약관 상의 본 조항 및 기타 조항 포함) 회사와 계열사 그리고 이들의 승계인 및 양수인의 이익에 부합하기 위함입니다.</p>
				<p>③ 회사는 특별, 직접, 간접, 징벌적, 부수적, 결과적 손해 또는 이익 손실, 저축 상의 손실, 영업 방해, 정보의 손실 등을 포함하되 이에 한정 짓지 않는 사유로 인한 어떠한 손해(계약 또는 불법 행위 또는 기타 서비스와 관련하여 발생한 모든 기타 손해)에 대하여 책임을 지지 않습니다.</p>

				<h4>제 30 조 (준거법 및 사법 관할)</h4>
				<p>① 약관은 법률 상충의 원칙 및 고객 소재지와 상관 없이 대한민국 법률의 적용을 받습니다.</p>
				<p>② 고객과 회사는 서울중앙지방법원 또는 회사와 가까운 법원의 독점적 사법권과 재판관할권에 영구히 동의하며, 이에 대한 사법권, 재판관할권의 불편함에 대한 이의권을 포기합니다. 전항에도 불구하고, 서울중앙지방법원의 판결 및 판시 사항은 고객 또는 회사 소재지의 어느 법원에서나 집행이 가능합니다.</p>

				<h4>제 31 조 (책임 면제 조항)</h4>
				<p>① 준거법에서 허용하는 최대 한도 내에서, 서비스는 “있는 그대로,” “유효한” 범위에서 제공되며, 회사는 조건, 품질, 내구성, 수행능력, 정확성, 신뢰성, 상업성 또는 특별 목적에 대한 적합성, 그리고 침해 방지, 또는 원활한 서비스, 오류 방지, 유해 요소 방지, 보안, 또는 기능이나 데이터의 손해나 손실 유발 방지 등을 포함하되 이에 한정 짓지 않는 내용에 대하여 암시적으로나 명시적으로 일체의 보장을 하지 않습니다.</p>
				<p>② 회사는 준거법에서 허용하는 최대 한도 내에서 서비스가 제공하는 정보의 유효성, 정확성, 신뢰성, 품질, 안정성, 완전성 또는 현재성을 대표 또는 보장하지 않습니다. 일부 관할지에서는 묵시적 보증의 배제나 묵시적 보증의 유효기간 제한을 허용하지 않으므로, 위 배제나 제한이 고객에게 적용되지 않을 수 있습니다.</p>

				<h4>제 32 조 (분리 규정)</h4>
				<p>만약 어떤 조항이 법원 또는 관할 재판소에 의해 무효 또는 집행 불가하다고 판단되더라도, 그 나머지 조항들의 집행 가능성은 이에 영향 받지 않고 여전히 유효하며 강제력을 갖습니다.</p>

				<h4>제 33 조 (권리 불포기 조항)</h4>
				<p>고객의 약관 위반과 관련하여 회사가 권리 실행이나 조치를 취하지 않았다고 하더라도, 이후 결과적으로 또는 유사하게 발생하는 행위에 대해 회사가 약관의 권리 및 해당 권리 실행을 포기하는 것으로 간주되지는 않습니다.</p>

				<h4>제 34 조 (무역 법규 준수)</h4>
				<p>고객이 서비스를 구매 및/또는 이용하기 위한 조건으로서, 고객은 EU Dual Use Export Controls, US Export Administration Regulations, International Traffic in Arms Regulations, 국제 및 국가별 경제 제재 프로그램을 포함하여 해당되는 모든 데이터의 보호, 수입, 재수입, 제재, 안티 보이콧, 수출 통제 법규 및 국가별 수출 통제 제도를 준수하기로 합의합니다. 오해의 소지를 피하기 위하여 부연하자면, 고객(해당되는 경우에는 그 최종 사용자)은 고객의 정보의 전송 및 처리, 앞서 언급한바 있는 회사의 최종 사용자를 대상으로 한 고객의 정보의 활용을 포함하여, 고객이 서비스를 사용하기로 선택하는 방식과 관련된 준수에 전적으로 책임이 있습니다. 고객이나 최종 사용자가 업로드한 모든 고객의 정보는 회사의 전세계에 위치한 서버에서 호스팅 될 수 있으므로, 고객은 관련된 무역 제한 목록에 나열된 정보를 업로드할 수 없으며 최종 사용자가 금지된 정보를 업로드 하지 못하게 할 책임이 있습니다. 고객은 UN 안정보장이사회, 미국 국가 안전보장 회의, 미국 정부(예: 미국 정부기관의 유럽연합 특별 지정 국가 목록 및 기타 해당 국가의 해외 제재 조치 회원국 목록) 및 이에 국한되지 아니한 기관의 제재 대상 목록에 명시되지 않았음을 확인하고 보증합니다.</p>

				<h4>제 35 조 (언어)</h4>
				<p>본 약관 상 회사가 게시하거나 통지하는 모든 의사교환 수단은 국문으로 합니다. 이 경우 회사는 이용자의 편의를 위하여 국문과 함께 영문 번역문을 제공할 수 있습니다. 단, 회사가 본 약관에 대한 영문 번역문을 제공하거나 게시 또는 통지를 함에 있어 영문 번역문을 제공하는 경우, 국문본과 영문 번역문 사이의 여하한 불일치 사항에 대해서는 국문본이 우선합니다. 그럼에도 불구하고 고객은 회사에 대한 의사교환 수단으로써 국문 또는 영문을 선택하여 진행할 수 있습니다.</p>

			</div>
			<!--사이트 이용약관 동의 오른쪽 끝-->
		</div>
		<!--사이트 이용약관 동의 끝-->
		<!--개인정보 수집 동의-->
		<div class="personalAgreeDiv">

			<div class="personalAgreeDivLeft">
				<input type="checkbox" name="" id="" class="agreeCheckBoxs">
			</div>
			<!--사이트 이용약관 동의 오른쪽-->
			<div class="personalAgreeDivRight">
				<!--
                    장 : h3
                    조 : h4
                    조항 : p
                -->
				<h2>
					<span class="EmphasisSpan">(필수)</span>개인정보 수집 및 이용에 대한 안내
				</h2>
				<h3>개인정보의 수집 및 이용 내역</h3>
				<p>팀4프로젝트 서비스(이하 "서비스")는 개인정보를 다음의 목적을 위해 처리합니다. 처리한 개인정보는 목적 이외의 용도로는 사용되지 않으며 이용 목적이 변경될 시에는 사전동의를 구할 예정입니다.</p>
				<p>① 서비스는 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집시에 동의 받은 개인정보 보유, 이용기간 내에서 개인정보를 처리, 보유합니다.</p>
				<p>② 보너스라이프(이하 "회사")는 서비스 회원가입, 회원 식별, 서비스 이용, 보안 유지 등을 위해 아래와 같이 개인정보를 수집ᆞ이용합니다.</p>
				</p>

				<!--테이블 들어갈 자리-->
				<table class="personalAgreeTable">
					<thead>
						<tr>
							<th>수집 목적</th>
							<th>수집 항목</th>
							<th>수집 근거</th>
							<th>보유 기간</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>회원 식별 및 팀4프로젝트 서비스 이용</td>
							<td>(필수) 아이디, 이메일, 비밀번호, 이름, 핸드폰, 나이, 성별, 주소<br> (선택) 회원 사진
							</td>
							<td>정보주체의 동의</td>
							<td>회원탈퇴 시 까지</td>
						</tr>
					</tbody>
				</table>
				<p>③ 원칙적으로 이용자의 개인정보를 회원 탈퇴 시 지체 없이 파기합니다. 단, 법령에서 일정기간 의무를 부과하는 경우에는 해당 기간 동안 보관할 수 있습니다.</p>
				<p>④ 서비스를 이용할 때 자동적으로 여러분이 사용하는 디바이스 및 네트워크의 정보 및 식별번호를 수집할 수 있습니다. 그 외에도 서비스를 이용하는데 필요한 다른 정보를 수집할 수 있으나 회원의 서비스를 이용하는 목적 용도로만 이용되며 그 외의 목적으로는 이용되지 않습니다.</p>
				<h3>동의를 거부할 권리 및 동의 거부에 따른 안내</h3>
				<p>귀하께서는 본 안내에 따른 개인정보 수집에 대하여 거부를 하실 수 있는 권리가 있습니다. 본 개인정보 수집에 대하여 거부하시는 경우, 본인확인 등 신원확인이 되지 않음에 따라 서비스를 사용 하실 수 없습니다.</p>



			</div>
			<!--개인정보 수집 동의 오른쪽 끝-->
		</div>
		<!--개인정보 수집 동의 끝-->

		<!--프로모션 email 수신 동의-->
		<div class="emailAgreeDiv">

			<div class="emailAgreeDivLeft">
				<input type="checkbox" name="" id="" class="agreeCheckBoxs">
			</div>
			<!--프로모션 email 수신 동의 오른쪽-->
			<div class="emailAgreeDivRight">
				<!--
                    장 : h3
                    조 : h4
                    조항 : p
                -->
				<h3>(선택)프로모션 email 수신</h3>
				<p>사이트에서 제공하는 이벤트/혜택 등 다양한 정보를 이메일로 받아보실 수 있습니다. 일부 서비스(별도 회원 체계로 운영하거나 sns계정 가입 이후 이용하는 서비스 등)의 경우, 개별 서비스에 대해 별도 수신 동의를 받을 수 있으며, 이때에도 수신 동의에 대해 별도로 안내하고 동의를 받습니다.</p>

			</div>
			<!--프로모션 email 수신 동의 끝-->
		</div>
		<!--프로모션 email 수신 동의 끝-->

		<!--다음으로 넘어가기 버튼 구역-->
		<div class="btnDiv">
			<button id="nextBtn" class="formBtns">다음</button>
		</div>
	</div>
	<!--회원가입 컨테이너 끝-->
	<script src="resources/js/agree.js"></script>
</body>

</html>