rating.chart = {
	initPieChart : function(){
	$('#container').highcharts({
	
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '得分总计'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
                ['0~0.5',   45.0],
                ['0.5~1',       26.8],
                {
                    name: '1~1.5',
                    y: 12.8,
                    sliced: true,
                    selected: true
                },
                ['1.5~2',    8.5],
                ['2~2.5',     6.2],
                ['2.5~3',   0.7],
                ['3~3.5',   0.7],
                ['3.5~4',   0.7],
                ['4~4.5',   0.7],
                ['4.5~5',   0.7]
            ]
        }]
    });
	},
	
	initHistogramChart : function(){
		$('#container').highcharts({
        chart: {
            type: 'column',
            margin: [ 50, 50, 100, 80]
        },
        title: {
            text: '投票统计'
        },
        xAxis: {
            categories: [
                '0~0.5',
                '0.5~1',
                '1~1.5',
                '1.5~2',
                '2~2.5',
                '2.5~3',
                '3~3.5',
                '3.5~4',
                '4~4.5',
                '4.5~5'
            ],
            labels: {
                rotation: -45,
                align: 'right',
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '人数'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '人数'
        },
        series: [{
            name: 'Population',
            data: [34.4, 21.8, 20.1, 20, 19.6, 19.5, 19.1, 18.4, 18,
                17.3],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#FFFFFF',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif',
                    textShadow: '0 0 3px black'
                }
            }
        }]
    });
	},
	
	initBarChart:function(){
		$('#container').highcharts({                                           
        chart: {                                                           
            type: 'bar'                                                    
        },                                                                 
        title: {                                                           
            text: '分数'                    
        },                                                                 
        subtitle: {                                                        
            text: ''                                  
        },                                                                 
        xAxis: {                                                           
            categories: ['0~0.5', '0.5~1', '1~1.5', '1.5~2', '2~2.5','2.5~3','3~3.5', '3.5~4', '4~4.5', '4.5~5'],
            title: {                                                       
                text: null                                                 
            }                                                              
        },                                                                 
        yAxis: {                                                           
            min: 0,                                                        
            title: {                                                       
                text: '人',                             
                align: 'high'                                              
            },                                                             
            labels: {                                                      
                overflow: 'justify'                                        
            }                                                              
        },                                                                 
        tooltip: {                                                         
            valueSuffix: ' millions'                                       
        },                                                                 
        plotOptions: {                                                     
            bar: {                                                         
                dataLabels: {                                              
                    enabled: true                                          
                }                                                          
            }                                                              
        },                                                                 
        legend: {                                                          
            layout: 'vertical',                                            
            align: 'right',                                                
            verticalAlign: 'top',                                          
            x: -40,                                                        
            y: 100,                                                        
            floating: true,                                                
            borderWidth: 1,                                                
            backgroundColor: '#FFFFFF',                                    
            shadow: true                                                   
        },                                                                 
        credits: {                                                         
            enabled: false                                                 
        },                                                                 
        series: [{                                                         
            name: 'xxx投票',                                             
            data: [10, 11, 3, 2, 15,10, 11, 3, 2, 15]                                   
        }]                                                                 
    }); 
	}
};

$(function(){
	//rating.chart.initPieChart();
	//rating.chart.initHistogramChart();
	rating.chart.initBarChart();
});