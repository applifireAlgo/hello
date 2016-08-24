Ext.define('Project2.project2.web.projecttwo.controller.appinsight.health.CustomUIController', {
     extend: 'Project2.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.CustomUIController',
     onDgClick: function(me, e, eOpts) {
          var jsonData = {};
          me.setDisabled(true);
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/CreateSampleServiceWS/createSampleService',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    scope.me.setDisabled(false);
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', 'Done');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    scope.me.setDisabled(false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});