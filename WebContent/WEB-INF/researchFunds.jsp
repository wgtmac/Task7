<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-customer-top.jsp" />
<div class="row">
	<div class="col-lg-12">
		<h2 class="page-header">Research Funds</h2>
	</div>
</div>


<script type="text/javascript" src="http://www.google.com/jsapi"></script>



<!-- IE Fix for HTML5 Tags -->

<!--[if lt IE 9]>

<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>

<![endif]-->


<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {
		document.getElementById("chart").style.visibility = 'hidden';
		var source = document.getElementById("chartData").value;
		var dataTable = source.split(",");
		if (source) {
			document.getElementById("chart").style.visibility = 'visible';
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Date');
			data.addColumn('number', 'Price $');
			data.addRows(parseInt(dataTable.length / 2));
			var num = 0;
			for ( var i = 0; i < dataTable.length - 1; i = i + 2) {
				data.setCell(parseInt(num), 0, String(dataTable[i]));
				num = num + 1;
			}
			num = 0;
			for ( var i = 1; i < dataTable.length - 1; i = i + 2) {
				data.setCell(parseInt(num), 1, parseFloat(dataTable[i]));
				num = num + 1;
			}

			var options = {
				title : ''
			};

			var chart = new google.visualization.LineChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);
		}
	}

	function setValues() {
		var radios = document.getElementsByName("radio");
		for ( var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				document.researchFundForm.fundId.value = radios[i].id;
			}
		}
	}
</script>

<script type="text/javascript" ></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Date', 'Google', 'Apple' , 'Facebook'],
          ['1/18/2014',  100,      200,  150],
          ['1/19/2014',  117,      220,  100],
          ['1/20/2014',  111,      210,  130],
          ['1/21/2014',  103,      240,  160]
        ]);

        var options = {
          title: 'Fund Performance',
          vAxis: {title: 'Date',  titleTextStyle: {color: 'red'}}
        };

        var chart = new google.visualization.BarChart(document.getElementById('piechart_div'));

        chart.draw(data, options);
      }
    </script>
 
 
    
 
     


<div class="container1">
	<c:choose>
		<c:when test="${ (empty msg) }">
		</c:when>
		<c:otherwise>
			<h3 style="color: blue">${msg}</h3>
		</c:otherwise>
	</c:choose>

	<c:forEach var="error" items="${errors}">
		<h3 style="color: red">${error}</h3>
	</c:forEach>
	<form name="researchFundForm" id="researchFundForm" method="post"
		action="researchFund.do">
		<input type="hidden" name="fundId" id="fundId" />
		<c:if test="${not empty  funds}">
			<br>
			<br>
			<h3>Select a Fund</h3>
			<table>
				<tr>
					<td><label>Select the fund you want to search:</label>
						<div class="form-group">
							<!-- Add the account stocks below -->
							<select class="form-control" name="fundName">
								<option></option>

								<c:choose>
									<c:when test="${ (empty funds) }"></c:when>
									<c:otherwise>
										<c:forEach var="u" items="${ funds }">
											<option>${ u.getName() }</option>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</select> <label>OR Search It Here:</label> <input name="fund2"
								type="text" class="form-control" value="${form.fund2}">
						</div>
<!-- onClick="setValues()"  -->
						
				</tr>
				<tr>
					<td align="right"><input type="submit" name="button"
						class="button" value="Fund History" /></td>
				</tr>

			</table>
		</c:if>
		<c:if test="${empty  funds}">
			<h4 align="center">No Funds to Display</h4>
		</c:if>
		<div id="chart">
			<!-- <table>
				<tr>
					<td class="line"></td>
				</tr>
			</table> -->
			<br />
			<table class="tableWidthPadding">
				<tr>
					<td>Fund:&nbsp;&nbsp;&nbsp;${fundTitle}</td>
				</tr>
				<tr>
					<td>Description:&nbsp;&nbsp;&nbsp;${description}</td>
				</tr>
			</table>
			<input type="hidden" name="chartData" id="chartData"
				value="${chartData}" />
			<div id="chart_div"></div>
			<br>
			<h3>Cumulative Fund Performance</h3>
			<br>
			<div id="piechart_div" align="center"></div>
		</div>
	</form>
</div>

<jsp:include page="template-buttom.jsp" />