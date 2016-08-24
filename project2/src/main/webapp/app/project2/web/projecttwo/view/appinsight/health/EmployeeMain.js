Ext.define('Project2.project2.web.projecttwo.view.appinsight.health.EmployeeMain', {
     "xtype": "employeeMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "EmployeeMainController",
     "restURL": "/Employee",
     "defaults": {
          "split": true
     },
     "requires": ["Project2.project2.shared.projecttwo.model.appinsight.health.EmployeeModel", "Project2.project2.web.projecttwo.controller.appinsight.health.EmployeeMainController", "Project2.project2.shared.projecttwo.viewmodel.appinsight.health.EmployeeViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Employee",
               "name": "EmployeeTreeContainer",
               "itemId": "EmployeeTreeContainer",
               "restURL": "/Employee",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "EmployeeTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Employee",
                    "title": "Employee",
                    "name": "Employee",
                    "itemId": "EmployeeForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "empId",
                         "itemId": "empId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "empId",
                         "margin": "5 5 5 5",
                         "fieldLabel": "empId<font color='red'> *<\/font>",
                         "fieldId": "832EB928-225B-4025-A414-0C3010FC16FB",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "empId"
                    }, {
                         "name": "empName",
                         "itemId": "empName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "empName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "empName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "7FA94C29-5103-4030-BA0E-6A72B4E3A31E",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "empName",
                         "columnWidth": 0.5
                    }, {
                         "name": "empSal",
                         "itemId": "empSal",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "empSal",
                         "margin": "5 5 5 5",
                         "fieldLabel": "empSal<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "0EA525A7-9EE7-4164-9D23-42FA10C5612E",
                         "minValue": "-9223372000000000000",
                         "maxValue": "9223372000000000000",
                         "bindable": "empSal",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "338DBAE9-18F1-4365-AAE9-EA45C552BD2E",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 561,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 561,
                              "customId": 349
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 561,
                              "customId": 350,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 561,
                              "customId": 351,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Employee",
                    "title": "Details Grid",
                    "name": "EmployeeGrid",
                    "itemId": "EmployeeGrid",
                    "restURL": "/Employee",
                    "columns": [{
                         "header": "empId",
                         "dataIndex": "empId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "empName",
                         "dataIndex": "empName",
                         "flex": 1
                    }, {
                         "header": "empSal",
                         "dataIndex": "empSal",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Employee",
               "title": "Employee",
               "name": "Employee",
               "itemId": "EmployeeForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "empId",
                    "itemId": "empId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "empId",
                    "margin": "5 5 5 5",
                    "fieldLabel": "empId<font color='red'> *<\/font>",
                    "fieldId": "832EB928-225B-4025-A414-0C3010FC16FB",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "empId"
               }, {
                    "name": "empName",
                    "itemId": "empName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "empName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "empName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "7FA94C29-5103-4030-BA0E-6A72B4E3A31E",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "empName",
                    "columnWidth": 0.5
               }, {
                    "name": "empSal",
                    "itemId": "empSal",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "empSal",
                    "margin": "5 5 5 5",
                    "fieldLabel": "empSal<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "0EA525A7-9EE7-4164-9D23-42FA10C5612E",
                    "minValue": "-9223372000000000000",
                    "maxValue": "9223372000000000000",
                    "bindable": "empSal",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "338DBAE9-18F1-4365-AAE9-EA45C552BD2E",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 561,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 561,
                         "customId": 349
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 561,
                         "customId": 350,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 561,
                         "customId": 351,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});