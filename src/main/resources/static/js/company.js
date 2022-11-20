function getPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}

$(document).ready(function () {
    initTable();
});

// 加载表格
function initTable() {
    $('#table').bootstrapTable('destroy');
    $("#table").bootstrapTable({
        method: "get",
        url: getPath() + "/company/getAllInfo",
        pagination: true, //启动分页
        pageNumber: 1,
        pageSize: 5,
        pageList: [10, 25, 50, 100], //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "server", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
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
            $('#mResult').addClass('alert-success');
            $('#mResult').html("加载成功!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-success');
                $('#mResult').html('');
            }, 5000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult').addClass('alert-danger');
            $('#mResult').html("由于服务器的原因,加载失败!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-danger');
                $('#mResult').html('');
            }, 2000);
        },
    });
}


// 刷新表格
$('#refreshBtn').click(function () {
    initTable();
});


$('#insertSave').click(function () {
    var insertCompanyId = $('#insertCompanyId').val();
    var insertCompanyName = $('#insertCompanyName').val();
    var insertPrincipalAccount = $('#insertPrincipalAccount').val();
    var insertCompanyAddress = $('#insertCompanyAddress').val();
    var insertCompanyTel = $('#insertCompanyTel').val();
    var insertIntroduction = $('#insertIntroduction').val();
    var insertLegalPerson = $('#insertLegalPerson').val();
    var insertLegalPersonTel = $('#insertLegalPersonTel').val();

    $.ajax({
        type: "post",
        url: getPath() + "/company/insertData",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            // "id": insertId,
            "companyId": insertCompanyId,
            "companyName": insertCompanyName,
            "principalAccount": insertPrincipalAccount,
            "companyAddress": insertCompanyAddress,
            "companyTel": insertCompanyTel,
            "introduction": insertIntroduction,
            "legalPerson": insertLegalPerson,
            "legalPersonTel":insertLegalPersonTel
        }),
        success: function (data) {
            if (data.flag) {
                $('#insertModal').modal("hide");
                $('#insertCompanyId').val('');
                $('#insertCompanyName').val('');
                $('#insertPrincipalAccount').val('');
                $('#insertCompanyAddress').val('');
                $('#insertCompanyTel').val('');
                $('#insertIntroduction').val('');
                $('#insertLegalPerson').val('');
                $('#insertLegalPersonTel').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "成功",
                    message: "添加成功!",
                    locale: "zh_CN"
                })
                initTable();
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "失败",
                    message: "添加失败!",
                    locale: "zh_CN"
                })
                initTable();
            }

        },
        error: function () {
            $('#mResult').addClass('alert-danger');
            $('#mResult').html("由于服务器原因，添加失败!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-danger');
                $('#mResult').html('');
            }, 2000);
        }
    })

});




// 更新信息
$('#updateBtn').click(function () {
    var rows = $('#table').bootstrapTable('getSelections');
    if (rows.length != 1) {
        bootbox.alert({
            centerVertical: true,
            title: "错误",
            message: "操作只能选择一条信息！",
            locale: "zh_CN"
        });
    } else {
        $('#updateCompanyId').val(rows[0].companyId);
        $('#updateCompanyName').val(rows[0].companyName);
        $('#updatePrincipalAccount').val(rows[0].principalAccount);
        $('#updateCompanyAddress').val(rows[0].companyAddress);
        $('#updateCompanyTel').val(rows[0].companyTel);
        $('#updateIntroduction').val(rows[0].introduction);
        $('#updateLegalPerson').val(rows[0].legalPerson);
        $('#updateLegalPersonTel').val(rows[0].legalPersonTel);
        $('#updateModal').modal("toggle");
    }
});


$('#updateSave').click(function () {
    var rows = $('#table').bootstrapTable('getSelections');
    var updateId = rows[0].id;
    var updateCompanyId = $('#updateCompanyId').val();
    var updateCompanyName = $('#updateCompanyName').val();
    var updatePrincipalAccount = $('#updatePrincipalAccount').val();
    var updateCompanyAddress = $('#updateCompanyAddress').val();
    var updateCompanyTel = $('#updateCompanyTel').val();
    var updateIntroduction = $('#updateIntroduction').val();
    var updateLegalPerson = $('#updateLegalPerson').val();
    var updateLegalPersonTel = $('#updateLegalPersonTel').val();


    $.ajax({
        type: "post",
        url: getPath() + "/company/updateData",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "id": updateId,
            "companyId": updateCompanyId,
            "companyName": updateCompanyName,
            "principalAccount": updatePrincipalAccount,
            "companyAddress": updateCompanyAddress,
            "companyTel": updateCompanyTel,
            "introduction": updateIntroduction,
            "legalPerson": updateLegalPerson,
            "legalPersonTel": updateLegalPersonTel
        }),
        success: function (data) {
            if (data.flag) {
                $('#insertModal').modal("hide");
                $('#updateCompanyId').val('');
                $('#updateCompanyName').val('');
                $('#updatePrincipalAccount').val('');
                $('#updateCompanyAddress').val('');
                $('#updateCompanyTel').val('');
                $('#updateIntroduction').val('');
                $('#updateLegalPerson').val('');
                $('#updateLegalPersonTel').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "成功",
                    message: "修改成功!",
                    locale: "zh_CN"
                })
                initTable();
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "失败",
                    message: "修改失败!",
                    locale: "zh_CN"
                });
                initTable();
            }

        },
        error: function () {
            $('#updateModal').modal("hide");
            $('#updateCompanyId').val('');
            $('#updateCompanyName').val('');
            $('#updatePrincipalAccount').val('');
            $('#updateCompanyAddress').val('');
            $('#updateCompanyTel').val('');
            $('#updateIntroduction').val('');
            $('#updateLegalPerson').val('');
            $('#updateLegalPersonTel').val('');
            $('#mResult').addClass('alert-danger');
            $('#mResult').html("由于服务器原因，添加失败!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-danger');
                $('#mResult').html('');
            }, 200);
        }
    })
});


// 编号搜索
$('#findByCompanyIdBtn').click(function () {
    var companyId = $("#companyId").val();
    $.ajax({
        type: "post",
        url: getPath() + "/company/findByCompanyId",
        async: false,
        dataType: 'json',
        data: {companyId: companyId},
        success: function (data) {
            console.log(data);
            if (data.total != 0) {
                $('#findBycompanyIdModal').modal("hide");
                $("#companyId").val("");
                $('#table').bootstrapTable('destroy');
                $('#table').bootstrapTable();
                $('#table').bootstrapTable('load', data);
            } else {
                $('#findByCompanyIdModal').modal("hide");
                $("#serialNumber").val("");
                bootbox.alert({
                    centerVertical: true,
                    title: "失败",
                    message: "搜索失败!",
                    locale: "zh_CN"
                })
                initTable();
            }

        },
        error: function () {
            $('#mResult').addClass('alert-danger');
            $('#mResult').html("由于服务器原因，搜索失败!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-danger');
                $('#mResult').html('');
            }, 2000);
        }
    })
});


// 模糊搜索
$('#fuzzSearchBtn').click(function () {
    var keyWord = $('#searchKeyWord').val();
    $.ajax({
        type: "post",
        url: getPath() + "/company/fuzzSearch",
        async: false,
        data: {keyword: keyWord},
        dataType: "json",
        success: function (data) {
            if (data.total != 0) {
                $('#findByKeywordModal').modal("hide");
                $('#searchKeyWord').val("");
                $('#table').bootstrapTable('destroy');
                $('#table').bootstrapTable();
                $('#table').bootstrapTable('load', data);
            } else {
                bootbox.alert({
                    centerVertical: true,
                    title: "失败",
                    message: "搜索失败!",
                    locale: "zh_CN"
                })
                initTable();
            }
        },
        error: function () {
            $('#mResult').addClass('alert-danger');
            $('#mResult').html("由于服务器原因，搜索失败!");
            setTimeout(function () {
                $('#mResult').removeClass('alert-danger');
                $('#mResult').html('');
            }, 2000);
        }
    })
});