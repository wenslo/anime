/**
 * 继承Easyui下的验证方法
 * 扩展validatebox,添加两次验证密码功能
 */
$.extend($.fn.validatebox.defaults.rules,{
    eqPassword:{
        validator:function(value,param){
            return value == $(param[0]).val();
        },
        message:'密码不一致！'
    }
});