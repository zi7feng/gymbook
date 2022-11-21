function getPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}

$(document).ready(function () {
    initTable1();
});

$(document).ready(function () {
    initTable2();
});

// 加载Super Admin表格
function initTable1() {
    $('#table1').bootstrapTable('destroy');
    $("#table1").bootstrapTable({
        method: "get",
        url: getPath() + "/superAdmin/listSu",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示客户端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#mResult1').addClass('alert-success');
            $('#mResult1').html("Success!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-success');
                $('#mResult1').html('');
            }, 5000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        },
    });
}


// 刷新Super Admin表格
$('#refreshBtn1').click(function () {
    initTable1();
});

// 加载Admin表格
function initTable2() {
    $('#table2').bootstrapTable('destroy');
    $("#table2").bootstrapTable({
        method: "get",
        url: getPath() + "/superAdmin/listAdmin",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#mResult2').addClass('alert-success');
            $('#mResult2').html("Success!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-success');
                $('#mResult2').html('');
            }, 5000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 2000);
        },
    });
}

// 刷新Admin表格
$('#refreshBtn2').click(function () {
    initTable2();
});

//Super Admin表格插入保存
$('#insertSave1').click(function () {
    var insertSuUserName = $('#insertSuUserName').val();
    var insertSuUserPwd = $('#insertSuUserPwd').val();

    $.ajax({
        type: "post",
        url: getPath() + "/superAdmin/insertAccount",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            // "id": insertId,
            "suId": 0,
            "suUserName": insertSuUserName,
            "suUserPwd": insertSuUserPwd
        }),
        success: function (data) {
            if (data.flag) {
                $('#insertModal1').modal("hide");
                $('#insertSuUserName').val('');
                $('#insertSuUserPwd').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en_US"
                })
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en_US"
                })
            }
            initTable1();
        },
        error: function () {
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        }
    })
});


// 更新Super Admin信息输入
$('#updateBtn1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        //$('#updateSuId').val(rows[0]["suId"]);
        //$('#updateSuUserName').val(rows[0]["suUserName"]);
        $('#updateSuUserPwd').val(rows[0]["suUserPwd"]);
        $('#updateModal1').modal("toggle");
    }
});

// 更新Super Admin保存
$('#updateSave1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    var updateSuId = rows[0]["suId"];
    var updateSuUserName = rows[0]["suUserName"]
    var updateSuUserPwd = $('#updateSuUserPwd').val();


    $.ajax({
        type: "post",
        url: getPath() + "/superAdmin/updatePassword",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "suId": updateSuId,
            "suUserName": updateSuUserName,
            "suUserPwd": updateSuUserPwd
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal1').modal("hide");
                // $('#updateSuId').val('');
                // $('#updateSuUserName').val('');
                $('#updateSuUserPwd').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
            initTable1();
        },
        error: function () {
            $('#updateModal1').modal("hide");
            $('#updateSuUserPwd').val('');
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 200);
        }
    })
});

// 删除Super Admin信息
$('#deleteBtn1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        //$('#updateSuId').val(rows[0]["suId"]);
        //$('#updateSuUserName').val(rows[0]["suUserName"]);
        //$('#updateActivatedStatus').val(rows[0]["activatedStatus"]);
        $('#deleteModal1').modal("toggle");
    }
})

// 删除Super Admin保存
$('#deleteSave1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    var deleteSuId = rows[0]["suId"];

    $.ajax({
        type: "post",
        url: getPath() + "/superAdmin/deleteAccount",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "suId": deleteSuId,
        }),
        success: function (data) {
            if (data.flag) {
                $('#deleteModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
            initTable1();
        },
        error: function () {
            $('#deleteModal1').modal("hide");
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 200);
        }
    })
});

// 更新Admin信息输入
$('#updateBtn2').click(function () {
    var rows = $('#table2').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        //$('#updateSuId').val(rows[0]["suId"]);
        //$('#updateSuUserName').val(rows[0]["suUserName"]);
        $('#updateActivatedStatus').val(rows[0]["activatedStatus"]);
        $('#updateModal2').modal("toggle");
    }
});

// 更新Admin信息保存
$('#updateSave2').click(function () {
    var rows = $('#table2').bootstrapTable('getSelections');
    var updateAdId = rows[0]["adId"];
    var updateAdminName = rows[0]["adminName"];
    var updateAdminPwd = rows[0]["adminPwd"];
    var updateActivatedStatus = $('#updateActivatedStatus').val();


    $.ajax({
        type: "post",
        url: getPath() + "/superAdmin/activateAccount",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "adId": updateAdId,
            "activatedStatus": updateActivatedStatus
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal2').modal("hide");
                // $('#updateSuId').val('');
                // $('#updateSuUserName').val('');
                $('#updateActivatedStatus').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
            initTable2();
        },
        error: function () {
            $('#updateModal2').modal("hide");
            $('#updateActivatedStatus').val('');
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 200);
        }
    })
});