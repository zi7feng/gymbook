function getPath() {
    var curPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = curPath.indexOf(pathName);
    var localhostPath = curPath.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPath + projectName;
}

$(document).ready(function () {
    searchTable1();
});

$(document).ready(function () {
    searchTable2();
});

$(document).ready(function () {
    initTable3();
});

// 表格1搜索
function searchTable1() {
    var gymName = $("#searchGymName1").val();
    var date = $("#searchDate1").val();
    $('#table1').bootstrapTable('destroy');
    $("#table1").bootstrapTable({
        method: "post",
        url: getPath() + "/admin/findScheduleByNameAndDate",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示客户端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        showRefresh: true,
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
                gymName: gymName,
                date: date
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#searchModal1').modal("hide");
            $('#searchGymName1').val('');
            $('#searchDate1').val('');
            $('#mResult1').addClass('alert-success');
            $('#mResult1').html("Success!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-success');
                $('#mResult1').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#searchModal1').modal("hide");

            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        },
    });
}


// 搜索保存刷新表1
$('#searchSave1').click(function () {
    searchTable1();
});

// 表格2搜索
function searchTable2() {
    var gymName = $("#searchGymName2").val();
    var visitDate = $("#searchVisitDate2").val();
    $('#table2').bootstrapTable('destroy');
    $("#table2").bootstrapTable({
        method: "post",
        url: getPath() + "/admin/findRecordByNameAndDate",
        cache: false,
        pagination: true, //启动分页
        pageList: "All", //记录数可选列表
        search: false,  //是否启用查询
        showColumns: true,  //显示下拉框勾选要显示的列
        sidePagination: "client", //表示服务端请求
        queryParamsType: "limit",
        clickToSelect: true,
        locale: 'en-US',
        showRefresh: true,
        //checkboxHeader: false,
        //singleSelect: true,
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                limit: params.limit,   //页面大小
                offset: params.offset,  //页码
                search: params.search,
                sort: params.sort,
                order: params.order,
                gymName: gymName,
                visitDate: visitDate
            };
            return param;
        },
        onLoadSuccess: function () {  //加载成功时执行
            $('#searchModal2').modal("hide");
            $('#mResult2').addClass('alert-success');
            $('#mResult2').html("Success!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-success');
                $('#mResult2').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#searchModal2').modal("hide");
            $('#mResult2').addClass('alert-danger');
            $('#mResult2').html("Server Failed!");
            setTimeout(function () {
                $('#mResult2').removeClass('alert-danger');
                $('#mResult2').html('');
            }, 2000);
        },
    });
}

// 刷新Record表格
$('#searchSave2').click(function () {
    searchTable2();
});

// 插入Schedule保存
$('#insertSave1').click(function () {
    var gymName = $('#insertGymName').val();
    var unitPrice = $('#insertUnitPrice').val();
    var gymStatus = $('#insertGymStatus').val();
    var date = $('#insertDate').val();

    $.ajax({
        type: "post",
        url: getPath() + "/admin/insertSchedule",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "gymName": gymName,
            "unitPrice": unitPrice,
            "gymStatus": gymStatus,
            "date": date
        }),
        success: function (data, xhr) {
            if (data.flag) {
                $('#insertModal1').modal("hide");
                $('#insertGymName').val('');
                $('#insertUnitPrice').val('');
                $('#insertGymStatus').val('');
                $('#insertDate').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en_US"
                })
            } else {
                $('#insertModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en_US"
                })
            }
        },
        error: function () {
            $('#insertModal1').modal("hide");
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 2000);
        }
    })
});


// 更新Schedule信息
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
        $('#updateUnitPrice').val(rows[0]["unitPrice"]);
        $('#updateGymStatus').val(rows[0]["gymStatus"]);
        $('#updateModal1').modal("toggle");
    }
});

// 更新Schedule保存
$('#updateSave1').click(function () {
    var rows = $('#table1').bootstrapTable('getSelections');
    var updateGymName = rows[0]["gymName"];
    var updateDate = rows[0]["date"]
    var updateUnitPrice = $('#updateUnitPrice').val();
    var updateGymStatus = $('#updateGymStatus').val();


    $.ajax({
        type: "post",
        url: getPath() + "/admin/updateSchedule",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "gymName": updateGymName,
            "unitPrice": updateUnitPrice,
            "gymStatus": updateGymStatus,
            "date": updateDate
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal1').modal("hide");
                $('#updateUnitPrice').val('');
                $('#updateGymStatus').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                $('#updateModal1').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
        },
        error: function () {
            $('#updateModal1').modal("hide");
            $('#mResult1').addClass('alert-danger');
            $('#mResult1').html("Server Failed!");
            setTimeout(function () {
                $('#mResult1').removeClass('alert-danger');
                $('#mResult1').html('');
            }, 200);
        }
    })
});

// // 删除
// $('#deleteBtn1').click(function () {
//     var rows = $('#table1').bootstrapTable('getSelections');
//     if (rows.length !== 1) {
//         bootbox.alert({
//             centerVertical: true,
//             title: "Error",
//             message: "Allow selecting only 1 row! ",
//             locale: "en_US"
//         });
//     } else {
//         //$('#updateSuId').val(rows[0]["suId"]);
//         //$('#updateSuUserName').val(rows[0]["suUserName"]);
//         //$('#updateActivatedStatus').val(rows[0]["activatedStatus"]);
//         $('#deleteModal1').modal("toggle");
//     }
// })

// // 删除保存
// $('#deleteSave1').click(function () {
//     var rows = $('#table1').bootstrapTable('getSelections');
//     var deleteSuId = rows[0]["suId"];
//
//     $.ajax({
//         type: "post",
//         url: getPath() + "/superAdmin/deleteAccount",
//         async: false,
//         dataType: 'json',
//         contentType: 'application/json',
//         data: JSON.stringify({
//             "suId": deleteSuId,
//         }),
//         success: function (data) {
//             if (data.flag) {
//                 $('#deleteModal1').modal("hide");
//                 bootbox.alert({
//                     centerVertical: true,
//                     title: "Success",
//                     message: "Success!",
//                     locale: "en-US"
//                 })
//             } else {
//                 $('#deleteModal1').modal("hide");
//                 bootbox.alert({
//                     centerVertical: true,
//                     title: "Failed",
//                     message: "Failed!",
//                     locale: "en-US"
//                 });
//             }
//         },
//         error: function () {
//             $('#deleteModal1').modal("hide");
//             $('#mResult1').addClass('alert-danger');
//             $('#mResult1').html("Server Failed!");
//             setTimeout(function () {
//                 $('#mResult1').removeClass('alert-danger');
//                 $('#mResult1').html('');
//             }, 200);
//         }
//     })
// });

// 加载Admin表格3
function initTable3() {
    $('#table3').bootstrapTable('destroy');
    $("#table3").bootstrapTable({
        method: "get",
        url: getPath() + "/currentAdmin",
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
            $('#mResult3').addClass('alert-success');
            $('#mResult3').html("Success!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-success');
                $('#mResult3').html('');
            }, 2000);
        },
        onLoadError: function () {  //加载失败时执行
            $('#mResult3').addClass('alert-danger');
            $('#mResult3').html("Server Failed!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-danger');
                $('#mResult3').html('');
            }, 2000);
        },
    });
}

// 刷新Admin表格3
$('#refreshBtn3').click(function () {
    initTable3();
});

// 更新AdminMyself信息
$('#updateBtn3').click(function () {
    var rows = $('#table3').bootstrapTable('getSelections');
    if (rows.length !== 1) {
        bootbox.alert({
            centerVertical: true,
            title: "Error",
            message: "Allow selecting only 1 row! ",
            locale: "en_US"
        });
    } else {
        $('#updateAdminPwd').val(rows[0]["adminPwd"]);
        $('#updateAdminPhone').val(rows[0]["adminPhone"]);
        $('#updateAdminEmail').val(rows[0]["adminEmail"]);
        $('#updateModal3').modal("toggle");
    }
});

// 更新Schedule保存
$('#updateSave3').click(function () {
    var rows = $('#table3').bootstrapTable('getSelections');
    var updateAdId = rows[0]["adId"];
    var updateAdminPwd = $('#updateAdminPwd').val();
    var updateAdminPhone = $('#updateAdminPhone').val();
    var updateAdminEmail = $('#updateAdminEmail').val();


    $.ajax({
        type: "post",
        url: getPath() + "/admin/updateAdminMyself",
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            "adId": updateAdId,
            "adminPwd": updateAdminPwd,
            "adminPhone": updateAdminPhone,
            "adminEmail": updateAdminEmail
        }),
        success: function (data) {
            if (data.flag) {
                $('#updateModal3').modal("hide");
                $('#updateAdminPwd').val('');
                $('#updateAdminPhone').val('');
                $('#updateAdminEmail').val('');
                bootbox.alert({
                    centerVertical: true,
                    title: "Success",
                    message: "Success!",
                    locale: "en-US"
                })
            } else {
                $('#updateModal3').modal("hide");
                bootbox.alert({
                    centerVertical: true,
                    title: "Failed",
                    message: "Failed!",
                    locale: "en-US"
                });
            }
        },
        error: function () {
            $('#updateModal3').modal("hide");
            $('#mResult3').addClass('alert-danger');
            $('#mResult3').html("Server Failed!");
            setTimeout(function () {
                $('#mResult3').removeClass('alert-danger');
                $('#mResult3').html('');
            }, 200);
        }
    })
    initTable3();
});

