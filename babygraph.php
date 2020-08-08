<?php
$link = mysqli_connect("localhost", "bazzi", "ekdms2400!", "bazzi");
mysqli_set_charset($link,'SET NAMES utf8');
// The Chart table contains two fields: weekly_task and percentage
// This example will display a pie chart. If you need other charts such as a Bar chart, you will need to modify the code a little to make it work with bar chart and other charts
$sth = "SELECT * FROM babyGraph";

/*
---------------------------
example data: Table (Chart)
--------------------------
weekly_task     percentage
Sleep           30
Watching Movie  40
work            44
*/

//flag is not needed
$flag = true;
$table = array();
$table['cols'] = array(

    // Labels for your chart, these represent the column titles
    // Note that one column is in "string" format and another one is in "number" format as pie chart only required "numbers" for calculating percentage and string will be used for column title
    array('label' => 'babyDate', 'type' => 'string'),
    array('label' => 'babyCM', 'type' => 'number'),
    array('label' => 'babyKG', 'type' => 'number')

);

$result=mysqli_query($link,$sth);
$rows = array();
while($r = mysqli_fetch_array($result)) {
    $temp = array();
    // the following line will be used to slice the Pie chart
    $temp[] = array('v' => (string) $r['babyDate']); 

    // Values of each slice
    $temp[] = array('v' => (float) $r['babyCM']); 
    $temp[] = array('v' => (float) $r['babyKG']); 
    $rows[] = array('c' => $temp);
}

$table['rows'] = $rows;
$jsonTable = json_encode($table);
//echo $jsonTable;
?>

<html>
  <head>
    <!--Load the Ajax API-->
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script type="text/javascript">

    // Load the Visualization API and the piechart package.
    google.load('visualization', '1', {'packages':['corechart']});

    // Set a callback to run when the Google Visualization API is loaded.
    google.setOnLoadCallback(drawChart);

    function drawChart() {

      // Create our data table out of JSON data loaded from server.
      var data = new google.visualization.DataTable(<?=$jsonTable?>);
      var options = {
          fontSize: 40,
          vAxis: {
          ticks: [10,20,30,40,50,60,70,80],
      },
          legend: {position: 'bottom'},
          colors: ['#FBC1F7', '#DCBDFF'],
          
          animation: { //차트가 뿌려질때 실행될 애니메이션 효과
                 startup: true,
                 duration: 1000,
                 easing: 'linear' },
               annotations: {
                   textStyle: {
                     fontSize: 30,
                     bold: true,
                     italic: true,
                     color: '#000000',
                     opacity: 0.8
                   }
              }
        };
        
      // Instantiate and draw our chart, passing in some options.
      // Do not forget to check your div ID
      var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    window.addEventListener('resize', function() { chart.draw(data, barChartOption); }, false);

    }
    </script>
  </head>

  <body>
    <!--this is the div that will hold the pie chart-->
    <div id="chart_div" style="width: 1420px; height: 1200px"></div>
  </body>
</html>