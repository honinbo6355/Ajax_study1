<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="./Resources/css/bootstrap.min.css" rel="stylesheet">
		<script src="./js/jquery-3.2.1.min.js"></script>
		<title>Insert title here</title>
	</head>
	<body>
		<br>
		<div class="container">
			<div class="form-group row pull-right">
				<div class="col-xs-8">
					<input class="form-control" type="text" size="20" id="searchForm">
				</div>
				<div class="col-xs-2">
					<button class="btn btn-primary" type="button" id="searchBtn">검색</button>
				</div>
			</div>
			<table class="table" style="text-align:center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th style="background-color:#fafafa; text-align:center">이름</th>
						<th style="background-color:#fafafa; text-align:center">나이</th>
						<th style="background-color:#fafafa; text-align:center">국가</th>
						<th style="background-color:#fafafa; text-align:center">포지션</th>
					</tr>
				</thead>
				<tbody id="ajaxTable">
					
				</tbody>
			</table>
		</div>
		<div class="container">
			<table class="table" style="text-align:center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="2" style="background-color:#fafafa; text-align:center">선수 등록 양식</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="vertical-align: middle;">이름</td>
						<td><input type="text" class="form-control" id="registerName" placeholder="이름을 입력하세요"></td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">나이</td>
						<td><input type="text" class="form-control" id="registerAge" placeholder="나이를 입력하세요"></td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">국가</td>
						<td><input type="text" class="form-control" id="registerCountry" placeholder="국가를 입력하세요"></td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">포지션</td>
						<td>
							<input type="radio" name="registerPosition" id="positionChoice1" autocomplete="off" value="골키퍼" checked> <label for="positionChoice1">골키퍼</label>
							<input type="radio" name="registerPosition" id="positionChoice2" autocomplete="off" value="수비수"> <label for="positionChoice2">수비수</label>
							<input type="radio" name="registerPosition" id="positionChoice3" autocomplete="off" value="공격수"> <label for="positionChoice3">공격수</label>
							<input type="radio" name="registerPosition" id="positionChoice4" autocomplete="off" value="미드필더"> <label for="positionChoice4">미드필더</label>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btn btn-info pull-right" id="registerBtn" value="등록"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<script>
			var xhttp = new XMLHttpRequest();
			
			document.getElementById("searchForm").addEventListener("keyup", searchFunction);
			document.getElementById("searchBtn").addEventListener("click", searchFunction);
			document.getElementById("registerBtn").addEventListener("click", registerFunction);
			window.addEventListener("load", searchFunction);
			
			function searchProcess() {
				if(this.readyState == 4 && this.status == 200) {
					var result = this.responseText;
					var jsonData = JSON.parse(result);
					//console.log(jsonData);
					var dataTable = document.getElementById("ajaxTable");
					dataTable.innerHTML = "";
					for(var i=0; i<jsonData.length; i++) {
						var row = dataTable.insertRow(i);
						row.innerHTML += "<td>" + jsonData[i].userName + "</td>";
						row.innerHTML += "<td>" + jsonData[i].userAge + "</td>";
						row.innerHTML += "<td>" + jsonData[i].userCountry + "</td>";
						row.innerHTML += "<td>" + jsonData[i].userPosition + "</td>";
					}
				}	
			}
			
			function searchFunction() {
				xhttp.onreadystatechange = searchProcess;
				xhttp.open("POST", "./UserSearchServlet", true);
				//xhttp.open("POST", "./UserController?userName="+searchText, true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send("userName="+document.getElementById("searchForm").value);
				//xhttp.send();
			}
			
			function registerProcess() {
				if(this.readyState == 4 && this.status == 200) {
					var result = this.responseText;
					if(result != 1) {
						alert("등록에 실패했습니다.");
					} else {
						document.getElementById("searchForm").value = "";
						document.getElementById("registerName").value = "";
						document.getElementById("registerAge").value = "";
						document.getElementById("registerCountry").value = "";
						searchFunction();
					}
				}
			}
			
			function registerFunction() {
				xhttp.onreadystatechange = registerProcess;
				xhttp.open("POST", "./UserRegisterServlet", true);
				xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				xhttp.send("userName="+document.getElementById("registerName").value +
						   "&userAge="+document.getElementById("registerAge").value +
						   "&userCountry="+document.getElementById("registerCountry").value +
						   "&userPosition="+$('input:radio[name="registerPosition"]:checked').val());
			}
			
		</script>
	</body>
</html>
