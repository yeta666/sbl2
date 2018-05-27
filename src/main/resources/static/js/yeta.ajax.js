//封装AJAX请求
var AJAX = {
    request: function (type, url, data, handler) {
        $.ajax({
            type: type,
            url: url,
            data: data,
            dataType: "JSON",
            success: function (data) {
                if (data.success) {
                    handler(data.data);
                } else {
                    alert(data.message);
                }
            },
            error: function (XHR) {
                alert(XHR.status);
            }
        });
    }
};