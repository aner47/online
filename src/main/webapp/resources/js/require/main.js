/**
 * Created by DEV2 on 2017/1/5.
 */
var base = "/online"


require.config({
    baseUrl:base+"/resources/js",
    waitSeconds: 0,
    paths : {
        "css":"require/css",
        "jquery" : "jquery/jquery-1.9.1.min",
        "ajaxform":"jquery/jquery.form",
        "validate":"jquery/jquery.validate",
        "grid" :"plugins/qy/grid/grid",
        "select":"plugins/qy/select/select",
        "ajax":"plugins/qy/ajax/ajax",
        "panel":"plugins/qy/panel/panel",
        "page" :"plugins/qy/grid/page", 
        "layer":"layer/layer",
        "tree":"plugins/qy/tree/tree",
        "jqueryui":"jquery/jquery-ui.min",
        "multselect":"plugins/qy/multselect/multselect",
        "ztree":"jquery/jquery.ztree.all.min",
        "less":"less/less",
        "year":"plugins/qy/year/year",
        "mtree":"plugins/qy/mtree/mtree",
        "upload":"plugins/qy/upload/upload",
        "webuploader":"webuploader/webuploader.min",
        "echarts":"echarts/echarts.min",
        "tab":"plugins/qy/tab/tab",
        "date":"date/jquery.jedate.min",
        "progresbar":"plugins/qy/progresbar/progresbar",
        "radio":"plugins/qy/radio/radio",
        "areautils":"plugins/qy/area-utils",
        "categoryutils":"plugins/qy/industry-utils",
        "hide":"plugins/qy/hide",
        "vue":"vue/vue",
        "localCache":"plugins/qy/localCache",
    	"vuefilters":"plugins/qy/vue-filters"
    },
    shim:{
        "grid":{
            "deps":["jquery","layer","page","css!"+base+"/resources/css/iconfont/iconfont.css",'css!plugins/qy/grid/css/mygrid.css']
        },
        "page":{
            "deps":["panel",'css!plugins/qy/grid/css/page.css']
        },
        "ajax":{
        	"deps":["layer"]
        },
        "layer":{
            "deps":["jquery","css!"+base+"/resources/js/layer/skin/layer.css"]
        },
        "select":{
        	"deps":["jquery","ajax","css!"+base+"/resources/css/iconfont/iconfont.css","css!plugins/qy/select/css/select.css"]
        },
        "panel":{
        	"deps":["layer"]
        },
        "ajaxform":{
        	"deps":["jquery"]
        },
        "validate":{
        	"deps":["jquery"]
        },
        "webuploader":{
            deps:['css!'+base+'/resources/js/webuploader/webuploader.css']
        },
        "tree":{
        	"deps":["jquery","css!"+base+"/resources/css/iconfont/iconfont.css","css!plugins/qy/tree/css/tree.css"]
        },        
        "jqueryui":{
        	"deps":["jquery","css!"+base+"/resources/js/jquery/css/jquery-ui.min.css"]
        },
        "multselect":{
            "deps":['css!plugins/qy/multselect/css/multselect.css']
        },
        "ztree":{
        	"deps":["jquery","css!"+base+"/resources/js/jquery/css/zTreeStyle.css"]
        },
        "year":{
            "deps":["jquery",'css!plugins/qy/year/css/year.css']
        },
        "mtree":{
        	"deps":["ztree"]
        },
        "tab":{
            "deps":["jquery","ajax","css!plugins/qy/tab/css/tab.css"]
        },
        "date":{
        	"deps":["css!"+base+"/resources/js/date/skin/jedate.css"]
        },
        "progresbar":{
        	"deps":["ajax","css!"+base+"/resources/js/plugins/qy/progresbar/css/progresbar.css"]
        },
        "radio":{
        	"deps":["jquery",'css!plugins/qy/radio/css/radio.css']
        },
        "hide":{
        	"deps":["jquery"]
        }
    }

})
