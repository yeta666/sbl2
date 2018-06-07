//封装AJAX请求
var AJAX = {
    request: function (type, url, data, handler, errorHandler) {
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
                    if (errorHandler) {
                        errorHandler();
                    }
                }
            },
            error: function (XHR) {
                alert(XHR.status);
            }
        });
    }
};