Ext.define('Project2.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Project2.view.appreportui.ReportViewController',
	            'Project2.view.appreportui.datagrid.DataGridPanel',
	            'Project2.view.appreportui.datagrid.DataGridView',
	            'Project2.view.appreportui.querycriteria.QueryCriteriaView',
	            'Project2.view.appreportui.chart.ChartView',
	            'Project2.view.appreportui.datapoint.DataPointView',
	            'Project2.view.googlemaps.map.MapPanel',
	            'Project2.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
