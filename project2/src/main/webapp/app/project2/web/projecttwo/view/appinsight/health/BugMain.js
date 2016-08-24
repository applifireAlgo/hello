Ext.define('Project2.project2.web.projecttwo.view.appinsight.health.BugMain', {
     "xtype": "bugMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "BugMainController",
     "restURL": "/Bug",
     "defaults": {
          "split": true
     },
     "requires": ["Project2.project2.shared.projecttwo.model.appinsight.health.BugModel", "Project2.project2.web.projecttwo.controller.appinsight.health.BugMainController", "Project2.project2.shared.projecttwo.viewmodel.appinsight.health.BugViewModel"],
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
               "displayName": "Bug",
               "name": "BugTreeContainer",
               "itemId": "BugTreeContainer",
               "restURL": "/Bug",
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
                    "itemId": "BugTree",
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
                    "displayName": "Bug",
                    "title": "Bug",
                    "name": "Bug",
                    "itemId": "BugForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "bugId",
                         "itemId": "bugId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "bugId",
                         "margin": "5 5 5 5",
                         "fieldLabel": "bugId<font color='red'> *<\/font>",
                         "fieldId": "9D3B63E9-6A8B-49B5-B8EF-BCFA18AA7FDC",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "bugId"
                    }, {
                         "name": "bugName",
                         "itemId": "bugName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "bugName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "bugName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "4C7F7482-CAE9-4A35-B698-C0FBA134520A",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "bugName",
                         "columnWidth": 0.5
                    }, {
                         "name": "component",
                         "itemId": "component",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "component",
                         "margin": "5 5 5 5",
                         "fieldLabel": "component<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "B2F2E844-D5E0-4AD6-9FF3-AB9ABF2293FF",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "component",
                         "columnWidth": 0.5
                    }, {
                         "name": "priority",
                         "itemId": "priority",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "priority",
                         "margin": "5 5 5 5",
                         "fieldLabel": "priority<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "C7BD0D2A-C152-42D8-A8FC-0E345D4657CC",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "priority",
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
                         "fieldId": "0BB26D82-9BC1-4F5C-92E0-0F5E86BCD0EF",
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
                         "customId": 232,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 232,
                              "customId": 786
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 232,
                              "customId": 787,
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
                              "parentId": 232,
                              "customId": 788,
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
                    "displayName": "Bug",
                    "title": "Details Grid",
                    "name": "BugGrid",
                    "itemId": "BugGrid",
                    "restURL": "/Bug",
                    "columns": [{
                         "header": "bugId",
                         "dataIndex": "bugId",
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
                         "header": "bugName",
                         "dataIndex": "bugName",
                         "flex": 1
                    }, {
                         "header": "component",
                         "dataIndex": "component",
                         "flex": 1
                    }, {
                         "header": "priority",
                         "dataIndex": "priority",
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
               "displayName": "Bug",
               "title": "Bug",
               "name": "Bug",
               "itemId": "BugForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "bugId",
                    "itemId": "bugId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "bugId",
                    "margin": "5 5 5 5",
                    "fieldLabel": "bugId<font color='red'> *<\/font>",
                    "fieldId": "9D3B63E9-6A8B-49B5-B8EF-BCFA18AA7FDC",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "bugId"
               }, {
                    "name": "bugName",
                    "itemId": "bugName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "bugName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "bugName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "4C7F7482-CAE9-4A35-B698-C0FBA134520A",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "bugName",
                    "columnWidth": 0.5
               }, {
                    "name": "component",
                    "itemId": "component",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "component",
                    "margin": "5 5 5 5",
                    "fieldLabel": "component<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "B2F2E844-D5E0-4AD6-9FF3-AB9ABF2293FF",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "component",
                    "columnWidth": 0.5
               }, {
                    "name": "priority",
                    "itemId": "priority",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "priority",
                    "margin": "5 5 5 5",
                    "fieldLabel": "priority<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "C7BD0D2A-C152-42D8-A8FC-0E345D4657CC",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "priority",
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
                    "fieldId": "0BB26D82-9BC1-4F5C-92E0-0F5E86BCD0EF",
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
                    "customId": 232,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 232,
                         "customId": 786
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 232,
                         "customId": 787,
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
                         "parentId": 232,
                         "customId": 788,
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