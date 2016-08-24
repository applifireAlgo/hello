Ext.define('Project2.project2.web.projecttwo.view.appinsight.health.CustomUI', {
     "xtype": "customUIView",
     "items": [{
          "xtype": "button",
          "name": "dg",
          "text": "Button",
          "margin": 5,
          "scale": "medium",
          "columnWidth": "0.30",
          "flex": 1,
          "itemId": "dg_button",
          "listeners": {
               "click": "onDgClick"
          }
     }],
     "layout": {
          "type": "vbox"
     },
     "border": true,
     "autoScroll": true,
     "title": "Form",
     "margin": 5,
     "itemId": "form_ext_5417",
     "dockedItems": [],
     "extend": "Ext.form.Panel",
     "requires": ["Project2.project2.web.projecttwo.controller.appinsight.health.CustomUIController", "Project2.project2.shared.projecttwo.viewmodel.appinsight.health.CustomUIViewModel", "Project2.project2.shared.projecttwo.model.appinsight.health.CustomUIModel"],
     "viewModel": "CustomUIViewModel",
     "controller": "CustomUIController"
});