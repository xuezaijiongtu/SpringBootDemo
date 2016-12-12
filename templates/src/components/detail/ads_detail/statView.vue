<template>
	<div class="row">
      <div class="col-sm-6">
          <div class="wall wall-sm">
              <a href="#" class="tile tile-lg ico-1"><span class="des">已上线</span><span class="num">90</span></a>
              <a href="#" class="tile tile-blue ico-2"><span class="des">全部</span><span class="num">105</span></a>
              <a href="#" class="tile tile-yellow ico-3"><span class="des">未上线</span><span class="num">15</span></a>
          </div>
      </div>
        
      <div class="col-sm-6">
          <div class="ad-type">
              <p>正常数据： <span>103232</span></p>
              <p>重传数据： <span>1335</span></p>
              <p>缺失数据： <span>5233</span></p>
          </div>
          
          <div class="charts"><canvas id="chart-area" width="220" height="220"></canvas></div>
      </div>
  </div>
</template>

<style>
.ad-type{float:left; margin-top:15px;}
.ad-type span{color:#fff; font-weight:bold; padding:4px 8px; border-radius:4px;}
.ad-type p:nth-child(1) span{background-color:#1ABC9C;}
.ad-type p:nth-child(2) span{background-color:#3498DB;}
.ad-type p:nth-child(3) span{background-color:#E67E22;}
.charts{float:left; width:220px; height:220px; margin:15px 0 0 20px;}
</style>
<script>
	import '../../../assets/plugins/charts/chart.min.js';

	window.onload = function(){
		var doughnutData = {
		    labels: [
		        "Red",
		        "Blue",
		        "Yellow"
		    ],
		    datasets: [
		        {
		            data: [300, 50, 100],
		            backgroundColor: [
		                "#FF6384",
		                "#36A2EB",
		                "#FFCE56"
		            ],
		            hoverBackgroundColor: [
		                "#FF6384",
		                "#36A2EB",
		                "#FFCE56"
		            ]
		        }]
		};
		var ctx = document.getElementById("chart-area").getContext("2d");
		window.myDoughnut =  new Chart(ctx, {
            data: doughnutData,
            type: "doughnut",
            options: {
              responsive : true
            }
        });
	};
</script>