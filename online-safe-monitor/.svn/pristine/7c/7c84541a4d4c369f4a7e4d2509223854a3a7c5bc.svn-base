
function LabelObject(){
	
};

LabelObject.prototype = {
	label : ''
};


function BasicAutoJqPlot() {

};

BasicAutoJqPlot.prototype = {
	/**
	 * 图表展示所需要的基础数组（数组的数组）
	 * javascript对象
	 * 格式：[[数据数组],[X轴刻度数组],[图例数组]]
	 */
	basicArray : null,
	/**
	 * 引擎数组:0、柱状图；1、折线图；2、饼状图
	 */
	plotEngineArray: [
	     //0、bar_series
		{
			renderer:$.jqplot.BarRenderer,
			shadowAngle: 135,
		    renderer : $.jqplot.BarRenderer,
		    rendererOptions : {
		    	highlightMouseDown: true,
		        barPadding : 0, // 设置同一分类两个柱状条之间的距离（px）
		        // barMargin: 30, //设置不同分类的两个柱状条之间的距离（px）（同一个横坐标表点上）
		        // barDirection: 'vertical', //设置柱状图显示的方向：垂直显示和水平显示
		        // ，默认垂直显示 vertical or horizontal.
		        barWidth : 13
		    // 设置柱状图中每个柱状条的宽度
		    // shadowOffset: 2, // 同grid相同属性设置
		    // shadowDepth: 5, // 同grid相同属性设置
		    // shadowAlpha: 0.8, // 同grid相同属性设置
		    },
		    pointLabels : {
		        show : true,
		    }
		},
		{
			//折线图
			renderer : $.jqplot.canvasTextRenderer,
			markerOptions: {
				show: true, // 是否在图中显示数据点   
				style: 'filledSquare', // 各个数据点在图中显示的方式，默认是"filledCircle"(实心圆点),其他几种方式circle，diamond, square, filledCircle，filledDiamond or filledSquare.   
				lineWidth: 1, // 数据点各个的边的宽度（如果设置过大，各个边会重复，会显示的类似于实心点）   
				size: 10,// 数据点的大小   
				//color: '#666666',	// 数据点的颜色   
				shadow: true,// 是否为数据点显示阴影区（增加立体效果）   
				shadowAngle: 45,// 阴影区角度，x轴顺时针方向   
				shadowOffset: 1, // 参考grid中相同参数   
				shadowDepth: 3,// 参考grid中相同参数   
				shadowAlpha: 0.07// 参考grid中相同参数
			}
		}
	                  ],
	/**
	 * 图标类型，值对应引擎数组的下标。默认柱状图。
	 */
	plotType:0,
	
	/**
	 * 图标显示区域DIV的id
	 */
	divId:'',
	
	/**
	 * X轴的名称
	 */
	xlabel: '',
	
	/**
	 * Y轴的名称
	 */
	ylabel: '',
	
	/**
	 * 这个图表的名称
	 */
	title : '',
	
	basicAutoShow: function() {
		$('#'+this.divId).empty();//展示图表前先清空显示区域
		if( this.divId == '' || !$('#'+this.divId) || this.basicArray[0] == '' ){
			return ;//若显示区域不在， 或没有数据时，则返回。
		}
		
		var labelArr = new Array();
		var labelArrTemp = this.basicArray[2];
		for( var i = 0; i < labelArrTemp.length; i++ ){
			var lab = new LabelObject();
			lab.label = labelArrTemp[i];
			labelArr.push(lab);
		}
	    $.jqplot(this.divId, this.basicArray[0], {
	    	title: this.title,//这个图表的名称
	    	//captureRightClick: true,//捕捉右击
		    stackSeries : true,//柱叠放用true
		    seriesDefaults : this.plotEngineArray[this.plotType],
		    series : labelArr,//X轴刻度
		    legend : {
		        show : true,
		        placement : 'outsideGrid'
		    },
		    axes : {
			    xaxis : {
			    	min : 0,
			        ticks : this.basicArray[1],
			        tickRenderer : $.jqplot.CanvasAxisTickRenderer,
			        labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
			        renderer : $.jqplot.CategoryAxisRenderer,//刻度为字符串时，加上这个。
			        tickOptions : {
				        angle : -60
			        },
			        label: this.xlabel
			    },
			    yaxis : {
			    	min : 0,
			    	tickRenderer:$.jqplot.CanvasAxisTickRenderer,
			    	labelRenderer: $.jqplot.CanvasAxisLabelRenderer,
			    	labelOptions:{
			    		fontFamily:'Helvetica',
			    		fontSize: '14pt'
			    	},
			    	label: this.ylabel
			    }
		    }
		});
	}
};

